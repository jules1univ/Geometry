package fr.univrennes.istic.l2gen.geometrie.shapes.base;

import fr.univrennes.istic.l2gen.geometrie.infrastructure.xml.model.XMLTag;
import fr.univrennes.istic.l2gen.geometrie.shapes.AbstractShape;
import fr.univrennes.istic.l2gen.geometrie.shapes.Point;

public final class Triangle extends AbstractShape {

    private Point point1;
    private Point point2;
    private Point point3;

    public Triangle(double x1, double y1,
            double x2, double y2,
            double x3, double y3) {
        this(
                new Point(x1, y1),
                new Point(x2, y2),
                new Point(x3, y3));
    }

    public Triangle(Point p1, Point p2, Point p3) {
        super("polypgon");
        this.point1 = new Point(p1.getX(), p1.getY());
        this.point2 = new Point(p2.getX(), p2.getY());
        this.point3 = new Point(p3.getX(), p3.getY());
    }

    @Override
    public Point getCenter() {
        double x = (point1.getX() + point2.getX() + point3.getX()) / 3.0;
        double y = (point1.getY() + point2.getY() + point3.getY()) / 3.0;
        return new Point(x, y);
    }

    @Override
    public double getWidth() {
        double minX = Math.min(point1.getX(), Math.min(point2.getX(), point3.getX()));
        double maxX = Math.max(point1.getX(), Math.max(point2.getX(), point3.getX()));
        return maxX - minX;
    }

    @Override
    public double getHeight() {
        double minY = Math.min(point1.getY(), Math.min(point2.getY(), point3.getY()));
        double maxY = Math.max(point1.getY(), Math.max(point2.getY(), point3.getY()));
        return maxY - minY;
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder(" ".repeat(Math.max(0, indent)));
        sb.append("Triangle ");
        sb.append(point1.getX()).append(",").append(point1.getY()).append(" ");
        sb.append(point2.getX()).append(",").append(point2.getY()).append(" ");
        sb.append(point3.getX()).append(",").append(point3.getY());
        return sb.toString();
    }

    @Override
    public void move(double dx, double dy) {
        point1.add(dx, dy);
        point2.add(dx, dy);
        point3.add(dx, dy);
    }

    @Override
    public AbstractShape copy() {
        return new Triangle(point1, point2, point3);
    }

    @Override
    public void resize(double sx, double sy) {
        Point center = getCenter();

        point1 = scalePoint(point1, center, sx, sy);
        point2 = scalePoint(point2, center, sx, sy);
        point3 = scalePoint(point3, center, sx, sy);
    }

    private Point scalePoint(Point p, Point c, double sx, double sy) {
        return new Point(
                c.getX() + (p.getX() - c.getX()) * sx,
                c.getY() + (p.getY() - c.getY()) * sy);
    }

    @Override
    public XMLTag toSVG() {
        this.setAttribute("points",
                point1.getX() + "," + point1.getY() + " " +
                        point2.getX() + "," + point2.getY() + " " +
                        point3.getX() + "," + point3.getY());
        this.setAttribute("fill", "white");
        this.setAttribute("stroke", "black");
        return this;
    }
}
