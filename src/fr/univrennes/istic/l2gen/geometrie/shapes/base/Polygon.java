package fr.univrennes.istic.l2gen.geometrie.shapes.base;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.shapes.IShape;
import fr.univrennes.istic.l2gen.geometrie.shapes.Point;
import fr.univrennes.istic.l2gen.geometrie.xml.model.XMLTag;

public final class Polygon implements IShape {

    private final List<Point> sommets;

    public Polygon() {
        this.sommets = new ArrayList<>();
    }

    public Polygon(double... coords) {

        this.sommets = new ArrayList<>();
        if (coords.length % 2 != 0) {
            throw new IllegalArgumentException("Nombre de coordonnées invalide");
        }
        for (int i = 0; i < coords.length; i += 2) {
            this.sommets.add(new Point(coords[i], coords[i + 1]));
        }
    }

    @Override
    public Point getCenter() {
        if (sommets.isEmpty()) {
            return new Point(0, 0);
        }

        double totalX = 0;
        double totalY = 0;

        for (Point p : sommets) {
            totalX += p.getX();
            totalY += p.getY();
        }

        return new Point(totalX / sommets.size(), totalY / sommets.size());
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder(" ".repeat(Math.max(0, indent)));
        sb.append("Polygone ");
        for (Point p : sommets) {
            sb.append(p.getX()).append(",").append(p.getY()).append(" ");
        }
        return sb.toString();
    }

    @Override
    public double getWidth() {
        if (sommets.isEmpty())
            return -1;

        double minX = sommets.get(0).getX();
        double maxX = minX;

        for (Point p : sommets) {
            minX = Math.min(minX, p.getX());
            maxX = Math.max(maxX, p.getX());
        }
        return maxX - minX;
    }

    @Override
    public double getHeight() {
        if (sommets.isEmpty())
            return -1;

        double minY = sommets.get(0).getY();
        double maxY = minY;

        for (Point p : sommets) {
            minY = Math.min(minY, p.getY());
            maxY = Math.max(maxY, p.getY());
        }
        return maxY - minY;
    }

    public void ajouterSommet(Point p) {
        sommets.add(new Point(p.getX(), p.getY()));
    }

    public void ajouterSommet(double x, double y) {
        sommets.add(new Point(x, y));
    }

    public List<Point> getVertex() {
        return new ArrayList<>(sommets);
    }

    @Override
    public IShape copy() {
        Polygon copie = new Polygon();
        copie.sommets.addAll(this.sommets);
        return copie;
    }

    @Override
    public void resize(double dx, double dy) {
        Point center = getCenter();

        for (Point p : sommets) {
            double newX = p.getX() + (p.getX() < center.getX() ? -dx : dx);
            double newY = p.getY() + (p.getY() < center.getY() ? -dy : dy);
            p.set(newX, newY);
        }
    }

    @Override
    public void move(double dx, double dy) {
        for (Point p : sommets) {
            p.add(dx, dy);
        }
    }

    @Override
    public XMLTag toSVG() {
        XMLTag polygon = new XMLTag("polygon");
        polygon.setAttribute("points", sommets.stream()
                .map(p -> p.getX() + "," + p.getY())
                .reduce((p1, p2) -> p1 + " " + p2)
                .orElse(""));
        polygon.setAttribute("fill", "white");
        polygon.setAttribute("stroke", "black");
        return polygon;
    }
}
