package fr.univrennes.istic.l2gen.geometrie.model.formes;

import java.util.ArrayList;

import fr.univrennes.istic.l2gen.geometrie.model.Point;

public final class Polygone implements IShape {
    private ArrayList<Point> sommet;

    public Polygone(double... coord) {
        sommet = new ArrayList<Point>();
        for (int i = 0; i < coord.length; i += 2) {
            sommet.add(new Point(coord[i], coord[i + 1]));
        }
    }

    @Override
    public Point getCenter() {
        double totalX = 0.;
        double totalY = 0.;
        int nbSommet = 0;
        for (Point elem : sommet) {
            totalX += elem.getX();
            totalY += elem.getY();
            nbSommet++;
        }
        return new Point(totalX / nbSommet, totalY / nbSommet);
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, indent)));
        sb.append("Polygone ");
        for (Point p : sommet) {
            sb.append( p.getX()).append(",").append(p.getY()).append(" ");
        }
        return sb.toString();
    }

    @Override
    public double getHeight() {
        Point max = null;
        Point min = null;

        if (this.sommet.isEmpty()) {
            return -1.0;
        } else {
            min = this.sommet.get(0);
            max = this.sommet.get(0);
        }

        for (int i = 0; i < this.sommet.size(); i++) {
            if (this.sommet.get(i).getX() < min.getX())
                min = this.sommet.get(i);
            if (this.sommet.get(i).getX() > max.getX())
                max = this.sommet.get(i);
        }
        return max.getX() - min.getX();
    }

    @Override
    public double getWidth() {
        Point max = null;
        Point min = null;

        if (this.sommet.isEmpty()) {
            return -1.0;
        } else {
            min = this.sommet.get(0);
            max = this.sommet.get(0);
        }

        for (int i = 0; i < this.sommet.size(); i++) {
            if (this.sommet.get(i).getY() < min.getY())
                min = this.sommet.get(i);
            if (this.sommet.get(i).getY() > max.getY())
                max = this.sommet.get(i);
        }
        return max.getY() - min.getY();

    }

    public void ajouterSommet(Point p) {
        this.sommet.add(p);
    }

    public void ajouterSommet(double x, double y) {
        this.sommet.add(new Point(x, y));
    }

    public ArrayList<Point> getSommets() {
        return this.sommet;
    }

    @Override
    public IShape copy() {
        Polygone copie = new Polygone();

        for (int i = 0; i < this.sommet.size(); i++) {
            copie.ajouterSommet(this.sommet.get(i));
        }
        return copie;
    }

    @Override
    public void resize(double x, double y) {
        for (Point elem : sommet) {
            if (elem.getX() < getCenter().getX()) {
                elem.add(x * (-1), 0.);
            } else {
                elem.add(x, 0.);
            }
            if (elem.getY() < getCenter().getX()) {
                elem.add(0., y * (-1));
            } else {
                elem.add(0., y);
            }
        }
    }

    @Override
    public void move(double x, double y) {
        for (Point elem : sommet) {
            elem.add(x, y);
        }
    }

    @Override
    public String toSVG() {
    String str = "<polygon points=\"";

    for ( int i = 0; i < this.sommet.size(); i++ ) {
    str += this.sommet.get(i).getX() + " " + this.sommet.get(i).getY() + " ";
    }
    str += "\n\"fill=\"white\" stroke=\"black\"/>";

    return str;
    }

}
