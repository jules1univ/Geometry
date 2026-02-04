package fr.univrennes.istic.l2gen.geometrie.base;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.IShape;
import fr.univrennes.istic.l2gen.geometrie.Point;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("polygon")
public final class Triangle implements IShape {

    @SVGField("points")
    private final List<Point> vertices;

    public Triangle(double x1, double y1,
            double x2, double y2,
            double x3, double y3) {
        this(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
    }

    public Triangle(Point p1, Point p2, Point p3) {
        this.vertices = new ArrayList<>(3);
        this.vertices.add(new Point(p1.getX(), p1.getY()));
        this.vertices.add(new Point(p2.getX(), p2.getY()));
        this.vertices.add(new Point(p3.getX(), p3.getY()));
    }

    public List<Point> getVertices() {
        return List.copyOf(vertices);
    }

    @Override
    public Point getCenter() {
        double sumX = 0.0;
        double sumY = 0.0;
        for (Point p : vertices) {
            sumX += p.getX();
            sumY += p.getY();
        }
        return new Point(sumX / 3.0, sumY / 3.0);
    }

    @Override
    public double getWidth() {
        double minX = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        for (Point p : vertices) {
            minX = Math.min(minX, p.getX());
            maxX = Math.max(maxX, p.getX());
        }
        return maxX - minX;
    }

    @Override
    public double getHeight() {
        double minY = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        for (Point p : vertices) {
            minY = Math.min(minY, p.getY());
            maxY = Math.max(maxY, p.getY());
        }
        return maxY - minY;
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder(" ".repeat(Math.max(0, indent)));
        sb.append("Triangle");
        for (Point p : vertices) {
            sb.append(" ").append(p.getX()).append(",").append(p.getY());
        }
        return sb.toString();
    }

    @Override
    public void move(double dx, double dy) {
        for (Point p : vertices) {
            p.add(dx, dy);
        }
    }

    @Override
    public void resize(double sx, double sy) {
        Point center = getCenter();
        for (int i = 0; i < vertices.size(); i++) {
            vertices.set(i, scalePoint(vertices.get(i), center, sx, sy));
        }
    }

    @Override
    public void rotate(double deg) {
        Point center = getCenter();
        double rad = Math.toRadians(deg);
        for (int i = 0; i < vertices.size(); i++) {
            vertices.set(i, rotatePoint(vertices.get(i), center, rad));
        }
    }

    private Point scalePoint(Point p, Point c, double sx, double sy) {
        return new Point(
                c.getX() + (p.getX() - c.getX()) * sx,
                c.getY() + (p.getY() - c.getY()) * sy);
    }

    private Point rotatePoint(Point point, Point center, double rad) {
        double cosTheta = Math.cos(rad);
        double sinTheta = Math.sin(rad);
        double translatedX = point.getX() - center.getX();
        double translatedY = point.getY() - center.getY();

        double rotatedX = translatedX * cosTheta - translatedY * sinTheta;
        double rotatedY = translatedX * sinTheta + translatedY * cosTheta;

        return new Point(
                rotatedX + center.getX(),
                rotatedY + center.getY());
    }

    @Override
    public IShape copy() {
        return new Triangle(vertices.get(0), vertices.get(1), vertices.get(2));
    }
}
