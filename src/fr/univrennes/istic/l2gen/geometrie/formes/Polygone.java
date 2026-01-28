package fr.univrennes.istic.l2gen.geometrie.formes;

import java.util.ArrayList;

import fr.univrennes.istic.l2gen.geometrie.Point;

public class Polygone implements IForme {
    private ArrayList<Point> sommet;

    public Polygone(double... coord) {
        sommet = new ArrayList<Point>();
        for (int i = 0; i < coord.length; i += 2) {
            sommet.add(new Point(coord[i], coord[i + 1]));
        }
    }

    @Override
    public Point centre() {
        double totalX = 0.;
        double totalY = 0.;
        int nbSommet = 0;
        for (Point elem : sommet) {
            totalX += elem.x();
            totalY += elem.y();
            nbSommet++;
        }
        return new Point(totalX / nbSommet, totalY / nbSommet);
    }

    @Override
    public String description(int indentation) {
        String str = "Polygone";
        String esp = "";

        for (int i = 0; i < indentation; i++) {
            esp += "  ";
        }
        for (int i = 0; i < this.sommet.size(); i++) {
            str += esp + this.sommet.get(i).x() + "," + this.sommet.get(i).y();
        }
        return str;
    }

    @Override
    public double hauteur() {
        Point max = null;
        Point min = null;

        if (this.sommet.isEmpty()) {
            return -1.0;
        } else {
            min = this.sommet.get(0);
            max = this.sommet.get(0);
        }

        for (int i = 0; i < this.sommet.size(); i++) {
            if (this.sommet.get(i).x() < min.x())
                min = this.sommet.get(i);
            if (this.sommet.get(i).x() > max.x())
                max = this.sommet.get(i);
        }
        return max.x() - min.x();
    }

    @Override
    public double largeur() {
        Point max = null;
        Point min = null;

        if (this.sommet.isEmpty()) {
            return -1.0;
        } else {
            min = this.sommet.get(0);
            max = this.sommet.get(0);
        }

        for (int i = 0; i < this.sommet.size(); i++) {
            if (this.sommet.get(i).y() < min.y())
                min = this.sommet.get(i);
            if (this.sommet.get(i).y() > max.y())
                max = this.sommet.get(i);
        }
        return max.y() - min.y();

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
    public IForme dupliquer() {
        Polygone copie = new Polygone();

        for (int i = 0; i < this.sommet.size(); i++) {
            copie.ajouterSommet(this.sommet.get(i));
        }
        return copie;
    }

    @Override
    public void redimensionner(double x, double y) {
        for (Point elem : sommet) {
            if (elem.x() < centre().x()) {
                elem.plus(x * (-1), 0.);
            } else {
                elem.plus(x, 0.);
            }
            if (elem.y() < centre().x()) {
                elem.plus(0., y * (-1));
            } else {
                elem.plus(0., y);
            }
        }
    }

    @Override
    public void deplacer(double x, double y) {
        for (Point elem : sommet) {
            elem.plus(x, y);
        }
    }

    @Override
    public String enSVG() {
        throw new UnsupportedOperationException("Unimplemented method 'enSVG'");
    }

}
