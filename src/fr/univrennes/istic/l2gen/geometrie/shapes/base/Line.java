package fr.univrennes.istic.l2gen.geometrie.shapes.base;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.shapes.IShape;
import fr.univrennes.istic.l2gen.geometrie.shapes.Point;
import fr.univrennes.istic.l2gen.geometrie.xml.model.XMLTag;

public final class Line implements IShape {

    private final List<Point> vertex;

    private Line() {
        this.vertex = new ArrayList<>();
    }

    public Line(double... coords) {
        if (coords.length % 2 != 0) {
            throw new IllegalArgumentException("Nombre de coordonnées invalide");
        }
        this.vertex = new ArrayList<>();
        for (int i = 0; i < coords.length; i += 2) {
            this.vertex.add(new Point(coords[i], coords[i + 1]));
        }
    }

    public void ajouterSommet(Point pt) {
        this.vertex.add(new Point(pt.getX(), pt.getY()));
    }

    public void ajouterSommet(double x, double y) {
        this.vertex.add(new Point(x, y));
    }

    @Override
    public Point getCenter() {
        double sumX = 0, sumY = 0;
        for (Point p : vertex) {
            sumX += p.getX();
            sumY += p.getY();
        }
        int n = vertex.size();
        return n > 0 ? new Point(sumX / n, sumY / n) : new Point(0, 0);
    }

    @Override
    public double getWidth() {
        if (vertex.isEmpty())
            return 0;
        double minX = vertex.get(0).getX();
        double maxX = minX;
        for (Point p : vertex) {
            minX = Math.min(minX, p.getX());
            maxX = Math.max(maxX, p.getX());
        }
        return maxX - minX;
    }

    @Override
    public double getHeight() {
        if (vertex.isEmpty())
            return 0;
        double minY = vertex.get(0).getY();
        double maxY = minY;
        for (Point p : vertex) {
            minY = Math.min(minY, p.getY());
            maxY = Math.max(maxY, p.getY());
        }
        return maxY - minY;
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder(" ".repeat(Math.max(0, indent)));
        sb.append("Ligne ");
        for (Point p : vertex) {
            sb.append(p.getX()).append(",").append(p.getY()).append(" ");
        }
        return sb.toString();
    }

    public List<Point> getVertex() {
        List<Point> copy = new ArrayList<>();
        for (Point p : vertex) {
            copy.add(new Point(p.getX(), p.getY()));
        }
        return copy;
    }

    @Override
    public void move(double dx, double dy) {
        for (Point p : vertex) {
            p.add(dx, dy);
        }
    }

    @Override
    public void resize(double sx, double sy) {
        Point center = getCenter();
        for (int i = 0; i < vertex.size(); i++) {
            Point p = vertex.get(i);
            Point scaled = new Point(
                    center.getX() + (p.getX() - center.getX()) * sx,
                    center.getY() + (p.getY() - center.getY()) * sy);
            vertex.set(i, scaled);
        }
    }

    @Override
    public IShape copy() {
        Line copie = new Line();
        copie.vertex.addAll(this.vertex);
        return copie;
    }

    @Override
    public XMLTag toSVG() {
        XMLTag polyline = new XMLTag("polyline");
        polyline.setAttribute("points", vertex.stream()
                .map(p -> p.getX() + "," + p.getY())
                .reduce((p1, p2) -> p1 + " " + p2)
                .orElse(""));
        polyline.setAttribute("fill", "white");
        polyline.setAttribute("stroke", "black");
        return polyline;
    }
}
