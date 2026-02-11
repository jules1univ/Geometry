package fr.univrennes.istic.l2gen.geometry;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("g")
public final class Group implements IShape {

    @SVGField
    private List<IShape> shapes = new ArrayList<>();

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    public Group() {
    }

    public Group(List<IShape> shapes) {
        this.shapes = shapes;
    }

    public void add(IShape child) {
        this.shapes.add(child);
    }

    @Override
    public Point getCenter() {
        if (shapes.isEmpty()) {
            return new Point(0, 0);
        }
        double sumX = 0;
        double sumY = 0;
        for (IShape shape : shapes) {
            sumX += shape.getCenter().getX();
            sumY += shape.getCenter().getY();
        }
        return new Point(sumX / shapes.size(), sumY / shapes.size());
    }

    @Override
    public double getHeight() {
        double maxY = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;

        for (IShape shape : shapes) {
            double topY = shape.getCenter().getY() - shape.getHeight() / 2;
            double bottomY = shape.getCenter().getY() + shape.getHeight() / 2;
            if (topY < minY) {
                minY = topY;
            }
            if (bottomY > maxY) {
                maxY = bottomY;
            }
        }
        return maxY - minY;
    }

    @Override
    public double getWidth() {
        double maxX = Double.NEGATIVE_INFINITY;
        double minX = Double.POSITIVE_INFINITY;

        for (IShape shape : shapes) {
            double leftX = shape.getCenter().getX() - shape.getWidth() / 2;
            double rightX = shape.getCenter().getX() + shape.getWidth() / 2;
            if (leftX < minX) {
                minX = leftX;
            }
            if (rightX > maxX) {
                maxX = rightX;
            }
        }
        return maxX - minX;
    }

    @Override
    public SVGStyle getStyle() {
        return this.style;
    }

    @Override
    public SVGTransform getTransform() {
        return this.transform;
    }

    @Override
    public String getDescription(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(indentation));
        sb.append("Group\n");
        for (IShape shape : shapes) {
            sb.append(shape.getDescription(indentation + 1));
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public void move(double dx, double dy) {
        for (IShape shape : shapes) {
            shape.move(dx, dy);
        }
    }

    @Override
    public void resize(double px, double py) {
        for (IShape shape : shapes) {
            shape.resize(px, py);
        }
    }

    @Override
    public void rotate(double deg) {
        for (IShape shape : shapes) {
            shape.rotate(deg);
        }
    }

    @Override
    public Group copy() {
        return new Group(new ArrayList<>(this.shapes));
    }
}