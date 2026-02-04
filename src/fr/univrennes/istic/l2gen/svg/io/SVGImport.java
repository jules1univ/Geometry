package fr.univrennes.istic.l2gen.svg.io;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPoint;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointX;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;
import fr.univrennes.istic.l2gen.svg.xml.parser.XMLParser;

public final class SVGImport {
    private static final List<Class<? extends ISVGShape>> shapes = new ArrayList<>();
    private static Class<? extends ISVGShape> point = null;

    public static <T extends ISVGShape> boolean register(Class<T> shape) {
        boolean hasDefaultConstructor = false;
        for (Constructor<?> constructor : shape.getConstructors()) {
            if (constructor.getParameterCount() == 0) {
                hasDefaultConstructor = true;
                break;
            }
        }

        if (!hasDefaultConstructor) {
            return false;
        }

        if (shape.getAnnotation(SVGPoint.class) != null) {
            point = shape;
            return true;
        }

        if (shape.getAnnotation(SVGTag.class) == null) {
            return false;
        }
        shapes.add(shape);
        return true;
    }

    private static ISVGShape createPoint(String rawPoint) {
        if (point == null) {
            return null;
        }

        try {
            ISVGShape pointShape = point.getDeclaredConstructor().newInstance();
            String[] coords = rawPoint.split(",");

            boolean foundX = false;
            boolean foundY = false;
            for (Field pointField : pointShape.getClass().getDeclaredFields()) {
                SVGPointX fieldX = pointField.getAnnotation(SVGPointX.class);
                if (fieldX != null) {
                    pointField.setAccessible(true);
                    pointField.set(pointShape, Double.parseDouble(coords[0]));
                    foundX = true;
                    continue;
                }

                SVGPointX fieldY = pointField.getAnnotation(SVGPointX.class);
                if (fieldY != null) {
                    pointField.setAccessible(true);
                    pointField.set(pointShape, Double.parseDouble(coords[1]));
                    foundY = true;
                    continue;
                }

                if (foundX && foundY) {
                    return pointShape;
                }
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private static List<ISVGShape> createPointList(String rawPoints) {
        List<ISVGShape> points = new ArrayList<>();
        String[] rawPointsArray = rawPoints.split(" ");
        for (String rawPoint : rawPointsArray) {
            ISVGShape pointShape = createPoint(rawPoint);
            if (pointShape != null) {
                points.add(pointShape);
            }
        }
        return points;
    }

    private static ISVGShape convertXMLToShape(XMLTag tag) {
        for (Class<? extends ISVGShape> shapeClass : shapes) {
            String tagName = shapeClass.getAnnotation(SVGTag.class).value();
            if (!tagName.equals(tag.getTagName())) {
                continue;
            }

            try {
                ISVGShape shape = shapeClass.getDeclaredConstructor().newInstance();
                for (Field shapeField : shape.getClass().getDeclaredFields()) {
                    SVGField attr = shapeField.getAnnotation(SVGField.class);
                    if (attr == null) {
                        continue;
                    }

                    String attrName = attr.value().length > 0 ? attr.value()[0] : shapeField.getName();
                    if (tag.hasAttribute(tagName)) {
                        String attrValue = tag.getAttribute(attrName).getValue();
                        if (attrValue == null) {
                            continue;
                        }

                        shapeField.setAccessible(true);
                        if (shapeField.getType().equals(String.class)) {
                            shapeField.set(shape, attrValue);
                        } else if (shapeField.getType().equals(Integer.class)) {
                            shapeField.set(shape, Integer.parseInt(attrValue));
                        } else if (shapeField.getType().equals(Double.class)) {
                            shapeField.set(shape, Double.parseDouble(attrValue));
                        } else if (shapeField.getType().equals(Boolean.class)) {
                            shapeField.set(shape, Boolean.parseBoolean(attrValue));
                        } else if (point != null) {
                            if (shapeField.getType().isAssignableFrom(point)) {
                                shapeField.set(shape, createPoint(attrValue));
                            } else if (shapeField.getType().isAssignableFrom(List.class)) {
                                shapeField.set(shape, createPointList(attrValue));
                            }
                        }
                    } else if (shapeField.getType().isAssignableFrom(List.class)) {
                        List<ISVGShape> childrenShapes = new ArrayList<>();
                        for (XMLTag childTag : tag.getChildren()) {
                            ISVGShape childShape = convertXMLToShape(childTag);
                            if (childShape != null) {
                                childrenShapes.add(childShape);
                            }
                        }
                        shapeField.setAccessible(true);
                        shapeField.set(shape, childrenShapes);

                    }
                }
                return shape;
            } catch (Exception e) {
                return null;
            }

        }

        return null;
    }

    public static ISVGShape load(String filename) {
        try (FileReader fr = new FileReader(filename)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = fr.read()) != -1) {
                sb.append((char) c);
            }
            String source = sb.toString();

            XMLParser parser = new XMLParser(source);

            XMLTag root = parser.parse();
            if (root == null || !root.getTagName().equals("svg")) {
                return null;
            }

            XMLTag mainShape = root.getFirstChildByName("g");
            if (mainShape == null) {
                return null;
            }
            return convertXMLToShape(mainShape);
        } catch (Exception e) {
            return null;
        }
    }
}
