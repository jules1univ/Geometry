package fr.univrennes.istic.l2gen.geometrie.model;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.model.formes.IShape;

public final class Groupe<Shape extends IShape> implements IShape {
    private List<Shape> shapes = new ArrayList<>();

    public Groupe(@SuppressWarnings("unchecked") Shape... formes) {
        for (Shape shape : formes) {
            this.shapes.add(shape);
        }
    }

    public Groupe(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public void add(Shape shape) {
        this.shapes.add(shape);
    }

    public Point getCenter() {
        double sumX = 0;
        double sumY = 0;
        for (IShape shape : shapes) {
            sumX += shape.getCenter().getX();
            sumY += shape.getCenter().getY();
        }
        return new Point(sumX / shapes.size(), sumY / shapes.size());
    }

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

    public void move(double dx, double dy) {
        for (IShape shape : shapes) {
            shape.move(dx, dy);
        }
    }

    public void resize(double px, double py) {
        for (IShape shape : shapes) {
            shape.resize(px, py);
        }
    }

    public Groupe<Shape> copy() {
        return new Groupe<>(new ArrayList<>(this.shapes));
    }

    @Override
    public String getDescription(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(indentation));
        sb.append("Groupe\n");
        for (IShape shape : shapes) {
            sb.append(shape.getDescription(indentation + 1));
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toSVG() {
        StringBuilder sb = new StringBuilder();
        sb.append("<g>\n");
        for (IShape shape : shapes) {
            sb.append(shape.toSVG());
            sb.append("\n");
        }
        sb.append("</g>");
        return sb.toString();
    }
}