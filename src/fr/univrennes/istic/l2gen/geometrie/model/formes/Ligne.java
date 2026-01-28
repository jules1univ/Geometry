package fr.univrennes.istic.l2gen.geometrie.model.formes;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.model.Point;

public final class Ligne implements IShape {

    private List<Point> sommets;

    public Ligne(double... n) {
        this.sommets = new ArrayList<Point>();
        for (int i = 0; i < n.length; i += 2) {
            this.sommets.add(new Point(n[i], n[i + 1]));
        }
    }

    public void ajouterSommet(Point pt) {
        this.sommets.add(pt);
    }

    public void ajouterSommet(double x, double y) {
        Point pt = new Point(x, y);
        this.sommets.add(pt);
    }

    @Override
    public Point getCenter() {
        double x = 0;
        double y = 0;
        for (int i = 0; i < this.sommets.size(); i++) {
            x += this.sommets.get(i).getX();
            y += this.sommets.get(i).getY();
        }
        x /= this.sommets.size();
        y /= this.sommets.size();
        return new Point(x, y);
    }

    @Override
    public double getHeight() {
        double min = this.sommets.get(0).getY();
        double max = this.sommets.get(0).getY();
        for (int i = 1; i < this.sommets.size(); i++) {
            if (this.sommets.get(i).getY() < min) {
                min = this.sommets.get(i).getY();
            }
            if (this.sommets.get(i).getY() > max) {
                max = this.sommets.get(i).getY();
            }
        }
        return max - min;
    }

    @Override
    public double getWidth() {
        double min = this.sommets.get(0).getX();
        double max = this.sommets.get(0).getX();
        for (int i = 1; i < this.sommets.size(); i++) {
            if (this.sommets.get(i).getX() < min) {
                min = this.sommets.get(i).getX();
            }
            if (this.sommets.get(i).getX() > max) {
                max = this.sommets.get(i).getX();
            }
        }
        return max - min;
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, indent)));
        sb.append("Ligne ");
        for (Point p : sommets) {
            sb.append((int) p.getX()).append(",").append((int) p.getY()).append(" ");
        }
        return sb.toString();
    }

    public List<Point> getSommets() {
        return this.sommets;
    }

    @Override
    public void move(double x, double y) {
        for (Point p : this.sommets) {
            p = p.add(x, y);
        }
    }

    @Override
    public IShape copy() {
        double[] points = new double[this.sommets.size() * 2];
        int index = 0;
        for (Point p : this.sommets) {
            points[index++] = p.getX();
            points[index++] = p.getY();
        }
        Ligne clone = new Ligne(points);
        return clone;
    }

    public void resize(double x, double y) {
        for (Point p : this.sommets) {
            p = p.add(x, y);
        }
    }

    @Override
    public String toSVG() {
        String svg = "<polyline points=\"";
        for (int i = 0; i < sommets.size(); i++) {
            svg = svg + sommets.get(i).getX() + " " + sommets.get(i).getY() + " ";
        }
        svg = svg + "\" \n" + "fill=\"white\" stroke=\"black\"/>";
        return svg;
    }

}
