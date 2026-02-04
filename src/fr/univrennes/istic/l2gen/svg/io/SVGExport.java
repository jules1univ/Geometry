package fr.univrennes.istic.l2gen.svg.io;

import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.List;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;
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

        for (Field field : pointClass.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.isAnnotationPresent(SVGPointX.class)) {
                    xValue = field.get(point).toString();
                }
                if (field.isAnnotationPresent(SVGPointY.class)) {
                    yValue = field.get(point).toString();
                }
            } catch (Exception e) {
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

    private static XMLTag convertShapeToXML(ISVGShape shape) {
        Class<?> shapeClass = shape.getClass();
        SVGTag tagName = shapeClass.getAnnotation(SVGTag.class);
        if (tagName == null) {
            throw new IllegalArgumentException(
                    "Class " + shapeClass.getSimpleName() + " must be annotated with @SVGTag");
        }

        XMLTag tag = new XMLTag(tagName.value());

        for (Field shapeField : shapeClass.getDeclaredFields()) {
            shapeField.setAccessible(true);
            SVGField field = shapeField.getAnnotation(SVGField.class);
            if (field == null) {
                continue;
            }

            Object value = null;
            try {
                value = shapeField.get(shape);
                if (value == null) {
                    continue;
                }
            } catch (Exception e) {
                continue;
            }

            String attrName = field.value().length > 0 ? field.value()[0] : shapeField.getName();
            if (value instanceof List listObj) {
                if (listObj.isEmpty()) {
                    continue;
                }

                if (listObj.get(0).getClass().getAnnotation(SVGPoint.class) != null) {
                    tag.addAttribute(new XMLAttribute(attrName, getObjectPoints(listObj)));
                } else {
                    for (Object childShape : listObj) {
                        if (childShape instanceof ISVGShape svgShape) {
                            tag.addChild(convertShapeToXML(svgShape));
                        }
                    }
                }
            } else if (value.getClass().getAnnotation(SVGPoint.class) != null && field.value().length == 2) {
                String pointValue = getObjectPointValue(value);
                if (pointValue != null) {
                    String[] parts = pointValue.split(",");
                    if (parts.length == 2) {
                        tag.addAttribute(new XMLAttribute(field.value()[0], parts[0]));
                        tag.addAttribute(new XMLAttribute(field.value()[1], parts[1]));
                    }
                }
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
        svg.addChild(background);

        svg.addChild(convertShapeToXML(root));

        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(svg.toString());
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
