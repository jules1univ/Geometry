package fr.univrennes.istic.l2gen.geometrie.model.formes;

import fr.univrennes.istic.l2gen.geometrie.model.Point;

public class Cercle implements IForme {
    private double rayon;
    private Point centre;

    public Cercle(double x, double y, double rayon) {
        this.rayon = rayon;
        this.centre = new Point(x, y);
    }

    public Cercle(Point centre, double rayon) {
        this.centre = centre;
        this.rayon = rayon;
    }

    @Override
    public Point centre() {
        return this.centre;
    }

    @Override
    public String description(int indentation) {
        return "Centre centre = " + this.centre.x() + "," + this.centre.y() + " r=" + this.rayon;
    }

    @Override
    public double hauteur() {
        return 2 * this.rayon;
    }

    @Override
    public double largeur() {
        return 2 * this.rayon;
    }

    @Override
    public void deplacer(double x, double y) {
        this.centre = new Point(x, y);
    }

    @Override
    public IForme dupliquer() {
        return new Cercle(this.centre, this.rayon);
    }

    @Override
    public void redimensionner(double px, double py) {
        this.rayon = this.rayon * px;
    }

    @Override
    public String enSVG() {
        return "<circle cx=\"" + this.centre.x() + "\" cy=\"" + this.centre.y() + "\" r=\"" + this.rayon + "\" fill=\"white\" stroke=\"black\"/>";
    }
}
