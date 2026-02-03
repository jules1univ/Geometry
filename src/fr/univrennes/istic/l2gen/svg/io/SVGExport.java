package fr.univrennes.istic.l2gen.svg.io;

import java.io.FileWriter;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGStyle;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;
import fr.univrennes.istic.l2gen.svg.interfaces.fields.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.fields.SVGFieldGroup;
import fr.univrennes.istic.l2gen.svg.interfaces.fields.SVGFieldPoint;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPoint;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointX;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointY;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLAttribute;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;

public final class SVGExport {
    // TODO: passer un object Style/SVGStyle pour customizer le rendu du SVG

    private static String getObjectPointValue(Object point) {

        if (point == null)
            return null;

        Class<?> pointClass = point.getClass();

        if (pointClass.getAnnotation(SVGPoint.class) == null) {
            return null;
        }

        String xValue = null;
        String yValue = null;

        for (Method method : pointClass.getDeclaredMethods()) {
            method.setAccessible(true);

            if (method.isAnnotationPresent(SVGPointX.class)) {
                try {
                    xValue = method.invoke(point).toString();
                } catch (Exception ignored) {
                }
            }

            if (method.isAnnotationPresent(SVGPointY.class)) {
                try {
                    yValue = method.invoke(point).toString();
                } catch (Exception ignored) {
                }
            }
        }

        for (Field field : pointClass.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.isAnnotationPresent(SVGPointX.class)) {
                    xValue = field.get(point).toString();
                }
                if (field.isAnnotationPresent(SVGPointY.class)) {
                    yValue = field.get(point).toString();
                }
            } catch (Exception ignored) {
            }
        }

        if (xValue != null && yValue != null) {
            return xValue + "," + yValue;
        }

        return null;
    }

    private static String getObjectPoints(List<?> pointsList) {
        StringBuilder sb = new StringBuilder();
        for (Object point : pointsList) {
            String value = getObjectPointValue(point);
            if (value != null) {
                sb.append(value).append(" ");
            }
        }
        return sb.toString().trim();
    }

    private static void addFromClassElements(Object obj, AccessibleObject[] classElements,
            Function<AccessibleObject, Object> elementValue,
            XMLTag tag) {
        for (AccessibleObject element : classElements) {
            SVGField field = element.getAnnotation(SVGField.class);
            if (field != null) {
                Object value = elementValue.apply(element);
                if (value == null) {
                    continue;
                }

                if (field.points() && value instanceof List<?> list) {
                    tag.addAttribute(new XMLAttribute(field.name(), getObjectPoints(list)));
                } else {
                    tag.addAttribute(new XMLAttribute(field.name(), value.toString()));
                }
            }

            SVGFieldPoint point = element.getAnnotation(SVGFieldPoint.class);
            if (point != null) {
                Object value = elementValue.apply(element);
                if (value == null) {
                    continue;
                }

                String pointValue = getObjectPointValue(value);
                if (pointValue == null)
                    continue;

                String[] parts = pointValue.split(",");
                if (parts.length != 2)
                    continue;

                tag.addAttribute(new XMLAttribute(point.x(), parts[0]));
                tag.addAttribute(new XMLAttribute(point.y(), parts[1]));

            }

            SVGFieldGroup group = element.getAnnotation(SVGFieldGroup.class);
            if (group != null) {
                Object value = elementValue.apply(element);
                if (value == null) {
                    continue;
                }
                if (value instanceof List listObj) {
                    for (Object shape : listObj) {
                        if (shape instanceof ISVGShape svgShape) {
                            tag.addChild(convertShapeToXML(svgShape));
                        }
                    }
                }
            }
        }
    }

    private static void addAttributesFromFields(ISVGShape shape, XMLTag tag) {
        addFromClassElements(shape, shape.getClass().getDeclaredFields(), element -> {
            try {
                Field field = (Field) element;
                field.setAccessible(true);
                return field.get(shape);
            } catch (Exception e) {
                return null;
            }
        }, tag);
    }

    private static void addAttributesFromMethods(ISVGShape shape, XMLTag tag) {
        addFromClassElements(shape, shape.getClass().getDeclaredMethods(), element -> {
            try {
                Method method = (Method) element;
                method.setAccessible(true);
                return method.invoke(shape);
            } catch (Exception e) {
                return null;
            }
        }, tag);
    }

    private static XMLTag convertShapeToXML(ISVGShape shape) {
        Class<?> shapeClass = shape.getClass();
        SVGTag tagName = shapeClass.getAnnotation(SVGTag.class);
        if (tagName == null) {
            throw new IllegalArgumentException(
                    "Class " + shapeClass.getSimpleName() + " must be annotated with @SVGTag");
        }

        XMLTag tag = new XMLTag(tagName.value());

        addAttributesFromFields(shape, tag);
        addAttributesFromMethods(shape, tag);

        SVGStyle style = shapeClass.getAnnotation(SVGStyle.class);
        if (style != null) {
            tag.addAttribute(new XMLAttribute("style", style.value()));
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
        svg.addChild(background);

        svg.addChild(convertShapeToXML(root));

        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(svg.toXMLString());
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
