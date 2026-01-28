package fr.univrennes.istic.l2gen.geometrie.model.formes;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.model.Point;

public final class Ligne implements IShape {

    private final List<Point> sommets;

    private Ligne() {
        this.sommets = new ArrayList<>();
    }

    public Ligne(double... coords) {
        if (coords.length % 2 != 0) {
            throw new IllegalArgumentException("Nombre de coordonnées invalide");
        }
        this.sommets = new ArrayList<>();
        for (int i = 0; i < coords.length; i += 2) {
            this.sommets.add(new Point(coords[i], coords[i + 1]));
        }
    }

    public void ajouterSommet(Point pt) {
        this.sommets.add(new Point(pt.getX(), pt.getY()));
    }

    public void ajouterSommet(double x, double y) {
        this.sommets.add(new Point(x, y));
    }

    @Override
    public Point getCenter() {
        double sumX = 0, sumY = 0;
        for (Point p : sommets) {
            sumX += p.getX();
            sumY += p.getY();
        }
        int n = sommets.size();
        return n > 0 ? new Point(sumX / n, sumY / n) : new Point(0, 0);
    }

    @Override
    public double getWidth() {
        if (sommets.isEmpty())
            return 0;
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
            return 0;
        double minY = sommets.get(0).getY();
        double maxY = minY;
        for (Point p : sommets) {
            minY = Math.min(minY, p.getY());
            maxY = Math.max(maxY, p.getY());
        }
        return maxY - minY;
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder(" ".repeat(Math.max(0, indent)));
        sb.append("Ligne ");
        for (Point p : sommets) {
            sb.append(p.getX()).append(",").append(p.getY()).append(" ");
        }
        return sb.toString();
    }

    public List<Point> getSommets() {
        List<Point> copy = new ArrayList<>();
        for (Point p : sommets) {
            copy.add(new Point(p.getX(), p.getY()));
        }
        return copy;
    }

    @Override
    public void move(double dx, double dy) {
        for (Point p : sommets) {
            p.add(dx, dy);
        }
    }

    @Override
    public void resize(double sx, double sy) {
        Point center = getCenter();
        for (int i = 0; i < sommets.size(); i++) {
            Point p = sommets.get(i);
            Point scaled = new Point(
                    center.getX() + (p.getX() - center.getX()) * sx,
                    center.getY() + (p.getY() - center.getY()) * sy);
            sommets.set(i, scaled);
        }
    }

    @Override
    public IShape copy() {
        Ligne copie = new Ligne();
        for (Point p : sommets) {
            copie.ajouterSommet(p);
        }
        return copie;
    }

    @Override
    public String toSVG() {
        StringBuilder sb = new StringBuilder("<polyline points=\"");
        for (Point p : sommets) {
            sb.append(p.getX()).append(" ").append(p.getY()).append(" ");
        }
        sb.append("\" fill=\"white\" stroke=\"black\"/>");
        return sb.toString();
    }
}
