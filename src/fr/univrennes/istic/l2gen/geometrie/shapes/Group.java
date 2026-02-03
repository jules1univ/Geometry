package fr.univrennes.istic.l2gen.geometrie.shapes;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;
import fr.univrennes.istic.l2gen.svg.interfaces.fields.SVGFieldGroup;

@SVGTag("g")
public final class Group<T extends IShape> implements IShape {

    @SVGFieldGroup
    private List<T> shapes = new ArrayList<>();

    public Group() {
    }

    public Group(List<T> shapes) {
        this.shapes = shapes;
    }

    public void add(T child) {
        this.shapes.add(child);
    }

    @Override
    public Point getCenter() {
        double sumX = 0;
        double sumY = 0;
        for (T shape : shapes) {
            sumX += shape.getCenter().getX();
            sumY += shape.getCenter().getY();
        }
        return new Point(sumX / shapes.size(), sumY / shapes.size());
    }

    @Override
    public double getHeight() {
        double maxY = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;

        for (T shape : shapes) {
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

        for (T shape : shapes) {
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
    public String getDescription(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(indentation));
        sb.append("Groupe\n");
        for (T shape : shapes) {
            sb.append(shape.getDescription(indentation + 1));
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void move(double dx, double dy) {
        for (T shape : shapes) {
            shape.move(dx, dy);
        }
    }

    @Override
    public void resize(double px, double py) {
        for (T shape : shapes) {
            shape.resize(px, py);
        }
    }

    @Override
    public void rotate(double deg) {
        for (T shape : shapes) {
            shape.rotate(deg);
        }
    }

    @Override
    public Group<T> copy() {
        return new Group<>(new ArrayList<>(this.shapes));
    }
}