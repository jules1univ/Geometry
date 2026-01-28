package fr.univrennes.istic.l2gen.geometrie.model.shapes;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.model.Point;

public final class Polygon implements IShape {

    private final List<Point> sommets;

    public Polygon() {
        this.sommets = new ArrayList<>();
    }

    public Polygon(double... coord) {
        this();
        if (coord.length % 2 != 0) {
            throw new IllegalArgumentException("Nombre de coordonnées invalide");
        }
        for (int i = 0; i < coord.length; i += 2) {
            sommets.add(new Point(coord[i], coord[i + 1]));
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
        if (sommets.isEmpty()) return -1;

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
        if (sommets.isEmpty()) return -1;

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

    public List<Point> getSommets() {
        return new ArrayList<>(sommets);
    }

    @Override
    public IShape copy() {
        Polygon copie = new Polygon();
        for (Point p : sommets) {
            copie.ajouterSommet(p);
        }
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
    public String toSVG() {
        StringBuilder sb = new StringBuilder("<polygon points=\"");

        for (Point p : sommets) {
            sb.append(p.getX()).append(" ").append(p.getY()).append(" ");
        }

        sb.append("\" fill=\"white\" stroke=\"black\"/>");
        return sb.toString();
    }
}
