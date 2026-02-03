package fr.univrennes.istic.l2gen.geometrie.base;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.IShape;
import fr.univrennes.istic.l2gen.geometrie.Point;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("polyline")
public final class Line implements IShape {

    @SVGField("points")
    private final List<Point> vertices;

    private Line() {
        this.vertices = new ArrayList<>();
    }

    public Line(double... coords) {
        if (coords.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid number of coordinates");
        }
        this.vertices = new ArrayList<>();
        for (int i = 0; i < coords.length; i += 2) {
            this.vertices.add(new Point(coords[i], coords[i + 1]));
        }
    }

    public void addVertex(Point pt) {
        this.vertices.add(new Point(pt.getX(), pt.getY()));
    }

    public void addVertex(double x, double y) {
        this.vertices.add(new Point(x, y));
    }

    public void setVertex(int index, Point pt) {
        this.vertices.set(index, new Point(pt.getX(), pt.getY()));
    }

    @Override
    public Point getCenter() {
        double sumX = 0, sumY = 0;
        for (Point p : vertices) {
            sumX += p.getX();
            sumY += p.getY();
        }
        int n = vertices.size();
        return n > 0 ? new Point(sumX / n, sumY / n) : new Point(0, 0);
    }

    @Override
    public double getWidth() {
        if (vertices.isEmpty())
            return 0;
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
            return 0;
        double minY = vertices.get(0).getY();
        double maxY = minY;
        for (Point p : vertices) {
            minY = Math.min(minY, p.getY());
            maxY = Math.max(maxY, p.getY());
        }
        return maxY - minY;
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder(" ".repeat(Math.max(0, indent)));
        sb.append("Ligne ");
        for (Point p : vertices) {
            sb.append(p.getX()).append(",").append(p.getY()).append(" ");
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
            Point p = vertices.get(i);
            Point scaled = new Point(
                    center.getX() + (p.getX() - center.getX()) * sx,
                    center.getY() + (p.getY() - center.getY()) * sy);
            vertices.set(i, scaled);
        }
    }

    @Override
    public void rotate(double deg) {
        Point center = getCenter();
        double rad = Math.toRadians(deg);

        double cosTheta = Math.cos(rad);
        double sinTheta = Math.sin(rad);

        for (int i = 0; i < vertices.size(); i++) {
            Point p = vertices.get(i);
            double translatedX = p.getX() - center.getX();
            double translatedY = p.getY() - center.getY();

            double rotatedX = translatedX * cosTheta - translatedY * sinTheta;
            double rotatedY = translatedX * sinTheta + translatedY * cosTheta;

            Point rotatedPoint = new Point(
                    rotatedX + center.getX(),
                    rotatedY + center.getY());
            vertices.set(i, rotatedPoint);
        }
    }

    @Override
    public IShape copy() {
        Line copie = new Line();
        copie.vertices.addAll(this.vertices);
        return copie;
    }
}
