package fr.univrennes.istic.l2gen.geometrie.shapes;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.infrastructure.xml.model.XMLTag;

public final class Group<T extends AbstractShape> extends AbstractShape {
    private List<T> shapes = new ArrayList<>();

    public Group() {
        super("g");
    }

    public Group(List<T> shapes) {
        super("g");
        this.shapes = shapes;
    }

    public Point getCenter() {
        double sumX = 0;
        double sumY = 0;
        for (T shape : shapes) {
            sumX += shape.getCenter().getX();
            sumY += shape.getCenter().getY();
        }
        return new Point(sumX / shapes.size(), sumY / shapes.size());
    }

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

    public void move(double dx, double dy) {
        for (T shape : shapes) {
            shape.move(dx, dy);
        }
    }

    public void resize(double px, double py) {
        for (T shape : shapes) {
            shape.resize(px, py);
        }
    }

    public Group<T> copy() {
        return new Group<>(new ArrayList<>(this.shapes));
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

    @SuppressWarnings("unchecked")
    @Override
    public void addChild(XMLTag child) {
        if (child instanceof AbstractShape shape) {
            this.shapes.add((T) shape);
            this.children.add(child);
            return;
        }

        throw new IllegalArgumentException("Child is not a Shape");
    }

    @Override
    public XMLTag toSVG() {
        return this;
    }
}