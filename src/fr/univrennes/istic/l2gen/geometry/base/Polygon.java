package fr.univrennes.istic.l2gen.geometry.base;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("polygon")
public final class Polygon implements IShape {

    @SVGField("points")
    private final List<Point> vertices;

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    public Polygon() {
        this.vertices = new ArrayList<>();
    }

    public Polygon(double... coords) {
        this.vertices = new ArrayList<>();
        if (coords.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid number of coordinates");
        }
        for (int i = 0; i < coords.length; i += 2) {
            this.vertices.add(new Point(coords[i], coords[i + 1]));
        }
    }

    public void addVertex(Point p) {
        vertices.add(new Point(p.getX(), p.getY()));
    }

    public void addVertex(double x, double y) {
        vertices.add(new Point(x, y));
    }

    @Override
    public double getWidth() {
        if (vertices.isEmpty())
            return -1;

        double minX = vertices.get(0).getX();
        double maxX = minX;

        for (Point p : vertices) {
            minX = Math.min(minX, p.getX());
            maxX = Math.max(maxX, p.getX());
        }
        return maxX - minX;
    }

    @Override
    public double getHeight() {
        if (vertices.isEmpty())
            return -1;

        double minY = vertices.get(0).getY();
        double maxY = minY;

        for (Point p : vertices) {
            minY = Math.min(minY, p.getY());
            maxY = Math.max(maxY, p.getY());
        }
        return maxY - minY;
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
    public Point getCenter() {
        if (vertices.isEmpty()) {
            return new Point(0, 0);
        }

        double totalX = 0;
        double totalY = 0;

        for (Point p : vertices) {
            totalX += p.getX();
            totalY += p.getY();
        }

        return new Point(totalX / vertices.size(), totalY / vertices.size());
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder(" ".repeat(Math.max(0, indent)));
        sb.append("Polygon ");
        sb.append("POINTS=");
        for (Point p : vertices) {
            sb.append(p.getX()).append(",").append(p.getY()).append(" ");
        }
        return sb.toString();
    }

    @Override
    public void resize(double dx, double dy) {
        Point center = getCenter();

        for (Point p : vertices) {
            double newX = p.getX() + (p.getX() < center.getX() ? -dx : dx);
            double newY = p.getY() + (p.getY() < center.getY() ? -dy : dy);
            p.set(newX, newY);
        }
    }

    @Override
    public void move(double dx, double dy) {
        for (Point p : vertices) {
            p.add(dx, dy);
        }
    }

    @Override
    public void rotate(double deg) {
        Point center = getCenter();
        double radians = Math.toRadians(deg);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);

        for (Point p : vertices) {
            double translatedX = p.getX() - center.getX();
            double translatedY = p.getY() - center.getY();

            double rotatedX = translatedX * cos - translatedY * sin;
            double rotatedY = translatedX * sin + translatedY * cos;

            p.set(rotatedX + center.getX(), rotatedY + center.getY());
        }
    }

    @Override
    public IShape copy() {
        Polygon copy = new Polygon();
        copy.vertices.addAll(this.vertices);
        return copy;
    }

}
