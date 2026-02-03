package fr.univrennes.istic.l2gen.geometrie.shapes.base;

import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.shapes.IShape;
import fr.univrennes.istic.l2gen.geometrie.shapes.Point;
import fr.univrennes.istic.l2gen.svg.interfaces.fields.SVGField;

public final class Triangle implements IShape {

    private Point vertex1;
    private Point vertex2;
    private Point vertex3;

    public Triangle(double x1, double y1,
            double x2, double y2,
            double x3, double y3) {
        this(
                new Point(x1, y1),
                new Point(x2, y2),
                new Point(x3, y3));
    }

    public Triangle(Point p1, Point p2, Point p3) {
        this.vertex1 = new Point(p1.getX(), p1.getY());
        this.vertex2 = new Point(p2.getX(), p2.getY());
        this.vertex3 = new Point(p3.getX(), p3.getY());
    }

    @SVGField(name = "points", points = true)
    public List<Point> getVertices() {
        return List.of(vertex1, vertex2, vertex3);
    }

    @Override
    public Point getCenter() {
        double x = (vertex1.getX() + vertex2.getX() + vertex3.getX()) / 3.0;
        double y = (vertex1.getY() + vertex2.getY() + vertex3.getY()) / 3.0;
        return new Point(x, y);
    }

    @Override
    public double getWidth() {
        double minX = Math.min(vertex1.getX(), Math.min(vertex2.getX(), vertex3.getX()));
        double maxX = Math.max(vertex1.getX(), Math.max(vertex2.getX(), vertex3.getX()));
        return maxX - minX;
    }

    @Override
    public double getHeight() {
        double minY = Math.min(vertex1.getY(), Math.min(vertex2.getY(), vertex3.getY()));
        double maxY = Math.max(vertex1.getY(), Math.max(vertex2.getY(), vertex3.getY()));
        return maxY - minY;
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder(" ".repeat(Math.max(0, indent)));
        sb.append("Triangle ");
        sb.append(vertex1.getX()).append(",").append(vertex1.getY()).append(" ");
        sb.append(vertex2.getX()).append(",").append(vertex2.getY()).append(" ");
        sb.append(vertex3.getX()).append(",").append(vertex3.getY());
        return sb.toString();
    }

    @Override
    public void move(double dx, double dy) {
        vertex1.add(dx, dy);
        vertex2.add(dx, dy);
        vertex3.add(dx, dy);
    }

    @Override
    public void resize(double sx, double sy) {
        Point center = getCenter();

        vertex1 = scalePoint(vertex1, center, sx, sy);
        vertex2 = scalePoint(vertex2, center, sx, sy);
        vertex3 = scalePoint(vertex3, center, sx, sy);
    }

    @Override
    public void rotate(double deg) {
        Point center = getCenter();
        double rad = Math.toRadians(deg);

        vertex1 = rotatePoint(vertex1, center, rad);
        vertex2 = rotatePoint(vertex2, center, rad);
        vertex3 = rotatePoint(vertex3, center, rad);
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
        return new Triangle(vertex1, vertex2, vertex3);
    }
}
