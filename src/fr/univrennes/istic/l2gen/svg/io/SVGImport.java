package fr.univrennes.istic.l2gen.svg.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
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
import fr.univrennes.istic.l2gen.svg.interfaces.content.SVGContent;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPoint;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointX;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointY;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;
import fr.univrennes.istic.l2gen.svg.xml.parser.XMLParser;

public final class SVGImport {
    private static final List<Class<? extends ISVGShape>> shapes = new ArrayList<>();

    private static Class<? extends ISVGShape> point = null;
    private static Field pointFieldX = null;
    private static Field pointFieldY = null;

    private static final Map<Class<?>, Constructor<?>> constructorCache = new HashMap<>();
    private static final Map<Class<?>, List<Field>> fieldCache = new HashMap<>();

    public static <T extends ISVGShape> void register(Class<T> shape) {
        Constructor<?> defaultConstructor = null;
        for (Constructor<?> constructor : shape.getConstructors()) {
            if (constructor.getParameterCount() == 0) {
                defaultConstructor = constructor;
                break;
            }
        }

        if (defaultConstructor == null) {
            throw new IllegalArgumentException(
                    "Shape class \"" + shape.getSimpleName() + "\" must have a default constructor");
        }

        defaultConstructor.setAccessible(true);
        constructorCache.put(shape, defaultConstructor);

        if (shape.getAnnotation(SVGPoint.class) != null) {
            point = shape;
            cachePointFields(shape);
            return;
        }

        if (shape.getAnnotation(SVGTag.class) == null) {
            throw new IllegalArgumentException(
                    "Shape class \"" + shape.getSimpleName() + "\" must be annotated with @SVGTag");
        }

        List<Field> fields = new ArrayList<>();
        Class<?> currentClass = shape;
        while (currentClass != null) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (field.getAnnotation(SVGField.class) != null
                        || field.getAnnotation(SVGContent.class) != null) {
                    field.setAccessible(true);
                    fields.add(field);
                }
            }
            currentClass = currentClass.getSuperclass();
        }

        fieldCache.put(shape, fields);
        shapes.add(shape);
    }

    private static void cachePointFields(Class<? extends ISVGShape> pointClass) {
        for (Field field : pointClass.getDeclaredFields()) {
            if (field.getAnnotation(SVGPointX.class) != null) {
                field.setAccessible(true);
                pointFieldX = field;
            } else if (field.getAnnotation(SVGPointY.class) != null) {
                field.setAccessible(true);
                pointFieldY = field;
            }
        }
    }

    private static ISVGShape createPoint(SVGField pointField, XMLTag tag) {
        if (pointField.value().length < 2) {
            return null;
        }
        String rawPoint = tag.getAttribute(pointField.value()[0]).getValue() + "," +
                tag.getAttribute(pointField.value()[1]).getValue();
        return createPoint(rawPoint);
    }

    private static ISVGShape createPoint(String rawPoint) {
        if (point == null || pointFieldX == null || pointFieldY == null) {
            return null;
        }

        try {
            ISVGShape pointShape = (ISVGShape) constructorCache.get(point).newInstance();
            String[] coords = rawPoint.split(",", 2);

            pointFieldX.set(pointShape, Double.parseDouble(coords[0]));
            pointFieldY.set(pointShape, Double.parseDouble(coords[1]));

            return pointShape;
        } catch (Exception e) {
            return null;
        }
    }

    private static List<ISVGShape> createPointList(String rawPoints) {
        List<ISVGShape> points = new ArrayList<>();
        String[] rawPointsArray = rawPoints.split(" ");
        for (String rawPoint : rawPointsArray) {
            if (rawPoint.isEmpty()) {
                continue;
            }
            ISVGShape pointShape = createPoint(rawPoint);
            if (pointShape != null) {
                points.add(pointShape);
            }
        }
        return points;
    }

    public static ISVGShape convert(XMLTag tag) {
        for (Class<? extends ISVGShape> shapeClass : shapes) {
            String tagName = shapeClass.getAnnotation(SVGTag.class).value();
            if (!tagName.equals(tag.getTagName())) {
                continue;
            }

            String className = shapeClass.getName();
            if (!tag.hasAttribute(SVGExport.DEFAULT_CLASS_TYPE_ATTR)
                    || !tag.getAttribute(SVGExport.DEFAULT_CLASS_TYPE_ATTR).getValue().equals(className)) {
                continue;
            }

            try {
                ISVGShape shape = (ISVGShape) constructorCache.get(shapeClass).newInstance();

                List<Field> fields = fieldCache.get(shapeClass);
                if (fields == null) {
                    fields = new ArrayList<>();
                    Class<?> currentClass = shapeClass;
                    while (currentClass != null) {
                        for (Field field : currentClass.getDeclaredFields()) {
                            if (field.getAnnotation(SVGField.class) != null
                                    || field.getAnnotation(SVGContent.class) != null) {
                                field.setAccessible(true);
                                fields.add(field);
                            }
                        }
                        currentClass = currentClass.getSuperclass();
                    }
                    fieldCache.put(shapeClass, fields);
                }

                for (Field shapeField : fields) {
                    SVGContent content = shapeField.getAnnotation(SVGContent.class);
                    if (content != null) {
                        shapeField.set(shape, tag.getTextContent().orElse(""));
                        continue;
                    }

                    SVGField attr = shapeField.getAnnotation(SVGField.class);

                    String attrName = attr.value().length > 0 ? attr.value()[0] : shapeField.getName();

                    Class<?> fieldType = shapeField.getType();
                    if (tag.hasAttribute(attrName)) {
                        String attrValue = tag.getAttribute(attrName).getValue();
                        if (attrValue == null) {
                            continue;
                        }

                        if (fieldType == String.class) {
                            shapeField.set(shape, attrValue);
                        } else if (fieldType == Integer.class || fieldType == int.class) {
                            shapeField.set(shape, Integer.parseInt(attrValue));
                        } else if (fieldType == Double.class || fieldType == double.class) {
                            shapeField.set(shape, Double.parseDouble(attrValue));
                        } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                            shapeField.set(shape, Boolean.parseBoolean(attrValue));
                        } else if (fieldType == Optional.class) {
                            Type[] genericTypes = ((ParameterizedType) shapeField.getGenericType())
                                    .getActualTypeArguments();
                            if (genericTypes.length == 0) {
                                continue;
                            }

                            Class<?> optionalValueType = (Class<?>) genericTypes[0];
                            if (optionalValueType == String.class) {
                                shapeField.set(shape, Optional.of(attrValue));
                            } else if (optionalValueType == Integer.class || optionalValueType == int.class) {
                                shapeField.set(shape, Optional.of(Integer.parseInt(attrValue)));
                            } else if (optionalValueType == Double.class || optionalValueType == double.class) {
                                shapeField.set(shape, Optional.of(Double.parseDouble(attrValue)));
                            } else if (optionalValueType == Boolean.class || optionalValueType == boolean.class) {
                                shapeField.set(shape, Optional.of(Boolean.parseBoolean(attrValue)));
                            }
                        } else if (point != null && fieldType == point) {
                            shapeField.set(shape, createPoint(attr, tag));
                        } else if (point != null && fieldType == List.class) {
                            shapeField.set(shape, createPointList(attrValue));
                        } else if (ISVGAttribute.class.isAssignableFrom(fieldType)) {
                            Constructor<?> stringConstructor = null;
                            for (Constructor<?> constructor : fieldType.getConstructors()) {
                                if (constructor.getParameterCount() == 1
                                        && constructor.getParameterTypes()[0] == String.class) {
                                    stringConstructor = constructor;
                                    break;
                                }
                            }

                            if (stringConstructor != null) {
                                shapeField.set(shape, stringConstructor.newInstance(attrValue));
                            }
                        }
                    } else if (fieldType == List.class) {
                        List<ISVGShape> childrenShapes = new ArrayList<>();
                        for (XMLTag childTag : tag.getChildren()) {
                            if (!childTag.hasAttribute(SVGExport.DEFAULT_LIST_TYPE_ATTR)) {
                                continue;
                            }
                            if (!childTag.getAttribute(SVGExport.DEFAULT_LIST_TYPE_ATTR).getValue()
                                    .equals(shapeField.getName())) {
                                continue;
                            }

                            ISVGShape childShape = convert(childTag);
                            if (childShape != null) {
                                childrenShapes.add(childShape);
                            }
                        }
                        shapeField.set(shape, childrenShapes);
                    } else if (fieldType.getAnnotation(SVGTag.class) != null) {
                        for (XMLTag childTag : tag.getChildren()) {
                            if (!childTag.hasAttribute(SVGExport.DEFAULT_CLASS_TYPE_ATTR)) {
                                continue;
                            }

                            if (childTag.getAttribute(SVGExport.DEFAULT_CLASS_TYPE_ATTR).getValue()
                                    .equals(fieldType.getName())) {
                                ISVGShape childShape = convert(childTag);
                                if (childShape != null) {
                                    shapeField.set(shape, childShape);
                                }
                                break;
                            }
                        }
                    }
                }
                return shape;
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    public static List<ISVGShape> load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            XMLParser parser = new XMLParser(br);

            XMLTag root = parser.parse();
            if (root == null || !root.getTagName().equals("svg")) {
                return null;
            }

            List<ISVGShape> shapes = new ArrayList<>();
            for (XMLTag child : root.getChildren()) {
                ISVGShape shape = convert(child);
                if (shape != null) {
                    shapes.add(shape);
                }
            }
            return shapes;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}