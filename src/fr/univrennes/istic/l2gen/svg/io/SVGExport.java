package fr.univrennes.istic.l2gen.svg.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.interfaces.ISVGAttribute;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPoint;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointX;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointY;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLAttribute;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;

public final class SVGExport {
    public static final String DEFAULT_DATA_TYPE_ATTR = "jclass-data";

    private static final Map<Class<?>, List<Field>> fieldCache = new HashMap<>();
    private static final Map<Class<?>, Field[]> pointFieldsCache = new HashMap<>();

    private static String getObjectPointValue(Object point) {
        if (point == null) {
            return null;
        }

        Class<?> pointClass = point.getClass();

        if (pointClass.getAnnotation(SVGPoint.class) == null) {
            return null;
        }

        Field[] pointFields = pointFieldsCache.get(pointClass);
        if (pointFields == null) {
            Field xField = null;
            Field yField = null;

            for (Field field : pointClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(SVGPointX.class)) {
                    field.setAccessible(true);
                    xField = field;
                } else if (field.isAnnotationPresent(SVGPointY.class)) {
                    field.setAccessible(true);
                    yField = field;
                }
            }

            if (xField != null && yField != null) {
                pointFields = new Field[] { xField, yField };
                pointFieldsCache.put(pointClass, pointFields);
            } else {
                return null;
            }
        }

        try {
            String xValue = pointFields[0].get(point).toString();
            String yValue = pointFields[1].get(point).toString();
            return xValue + "," + yValue;
        } catch (Exception e) {
            return null;
        }
    }

    private static String getObjectPoints(List<?> pointsList) {
        if (pointsList.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Object point : pointsList) {
            String value = getObjectPointValue(point);
            if (value != null) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(value);
            }
        }
        return sb.toString();
    }

    public static XMLTag convert(ISVGShape shape) {
        Class<?> shapeClass = shape.getClass();
        SVGTag tagName = shapeClass.getAnnotation(SVGTag.class);
        if (tagName == null) {
            throw new IllegalArgumentException(
                    "Class " + shapeClass.getSimpleName() + " must be annotated with @SVGTag");
        }

        XMLTag tag = new XMLTag(tagName.value());
        tag.addAttribute(new XMLAttribute(DEFAULT_DATA_TYPE_ATTR, shapeClass.getName()));

        List<Field> fields = fieldCache.get(shapeClass);
        if (fields == null) {
            fields = new ArrayList<>();
            Class<?> currentClass = shapeClass;
            while (currentClass != null) {
                for (Field field : currentClass.getDeclaredFields()) {
                    if (field.getAnnotation(SVGField.class) != null) {
                        field.setAccessible(true);
                        fields.add(field);
                    }
                }
                currentClass = currentClass.getSuperclass();
            }
            fieldCache.put(shapeClass, fields);
        }

        for (Field shapeField : fields) {
            SVGField field = shapeField.getAnnotation(SVGField.class);

            Object value;
            try {
                value = shapeField.get(shape);
                if (value == null) {
                    continue;
                }
            } catch (Exception e) {
                continue;
            }

            String attrName = field.value().length > 0 ? field.value()[0] : shapeField.getName();
            if (value instanceof List<?> listObj) {
                if (listObj.isEmpty()) {
                    continue;
                }

                Type[] genericTypes = ((ParameterizedType) shapeField.getGenericType()).getActualTypeArguments();
                if (genericTypes.length == 0) {
                    continue;
                }
                Class<?> listValueType = (Class<?>) ((ParameterizedType) shapeField.getGenericType())
                        .getActualTypeArguments()[0];

                if (listValueType.getAnnotation(SVGPoint.class) != null) {
                    String pointsValue = getObjectPoints(listObj);
                    if (!pointsValue.isEmpty()) {
                        tag.addAttribute(new XMLAttribute(attrName, pointsValue));
                    }
                } else if (ISVGShape.class.isAssignableFrom(listValueType)) {
                    for (Object childShape : listObj) {
                        if (childShape instanceof ISVGShape svgShape) {
                            tag.appendChild(convert(svgShape));
                        }
                    }
                }

            } else if (value.getClass().getAnnotation(SVGPoint.class) != null && field.value().length == 2) {
                String pointValue = getObjectPointValue(value);
                if (pointValue != null) {
                    int commaIndex = pointValue.indexOf(',');
                    if (commaIndex != -1) {
                        tag.addAttribute(new XMLAttribute(field.value()[0], pointValue.substring(0, commaIndex)));
                        tag.addAttribute(new XMLAttribute(field.value()[1], pointValue.substring(commaIndex + 1)));
                    }
                }
            } else if (value instanceof ISVGAttribute svgAttr) {
                if (svgAttr.hasContent()) {
                    tag.setAttribute(attrName, svgAttr.getContent());
                }
            } else if (value instanceof Optional<?> optValue) {
                if (optValue.isPresent()) {
                    tag.setAttribute(attrName, optValue.get().toString());
                }
            } else if (value instanceof ISVGShape svgShape) {
                tag.appendChild(convert(svgShape));
            } else {
                tag.addAttribute(new XMLAttribute(attrName, value.toString()));
            }
        }

        return tag;
    }

    public static boolean export(ISVGShape root, String filename) {
        XMLTag svg = new XMLTag("svg");
        svg.addAttribute(new XMLAttribute("xmlns", "http://www.w3.org/2000/svg"));
        svg.addAttribute(new XMLAttribute("version", "1.1"));
        svg.addAttribute(new XMLAttribute("width", "1000"));
        svg.addAttribute(new XMLAttribute("height", "1000"));

        XMLTag background = new XMLTag("rect");
        background.addAttribute(new XMLAttribute("width", "100%"));
        background.addAttribute(new XMLAttribute("height", "100%"));
        background.addAttribute(new XMLAttribute("fill", "white"));
        svg.appendChild(background);

        svg.appendChild(convert(root));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(svg.toString());
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}