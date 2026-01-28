package fr.univrennes.istic.l2gen.geometrie;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }


    public void plus(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public void plus(Point p) {
        this.x += p.x;
        this.y += p.y;
    }

    public void mult(double dx, double dy) {
        this.x *= dx;
        this.y *= dy;
    }


    public void mult(Point p) {
        this.x *= p.x;
        this.y *= p.y;
    }

    public void deplacer(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public Point dupliquer() {
        return new Point(this.x, this.y);
    }

    public void redimensionner(double px, double py) {
        this.x *= px;
        this.y *= py;
    }

    @Override
    public String toString() {
        return (int) x + "," + (int) y;
    }
}
