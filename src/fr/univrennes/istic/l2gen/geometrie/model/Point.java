package fr.univrennes.istic.l2gen.geometrie.model;

import fr.univrennes.istic.l2gen.geometrie.model.formes.IShape;

public final class Point implements IShape {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point add(double dx, double dy) {
        this.x += dx;
        this.y += dy;
        return this;

    }

    public Point add(Point p) {
        this.x += p.x;
        this.y += p.y;
        return this;
    }

    public Point mult(double dx, double dy) {
        this.x *= dx;
        this.y *= dy;
        return this;

    }

    public Point mult(Point p) {
        this.x *= p.x;
        this.y *= p.y;
        return this;

    }

    @Override
    public String toString() {
        return (int) x + "," + (int) y;
    }

    @Override
    public boolean equals(Object e) {
        if (e instanceof Point) {
            return this.x == ((Point) e).getX() && this.y == ((Point) e).getY();
        }
        return false;
    }

    @Override
    public double getWidth() {
        return 1;
    }

    @Override
    public double getHeight() {
        return 1;
    }

    @Override
    public Point getCenter() {
        return this;
    }

    @Override
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void resize(double px, double py) {
        this.x *= px;
        this.y *= py;
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent * 2; i++) {
            sb.append(" ");
        }
        sb.append("Point ");
        sb.append(this.toString());
        return sb.toString();
    }

    @Override
    public String toSVG() {
        StringBuilder sb = new StringBuilder();
        sb.append("<circle cx=\"").append(this.x).append("\" cy=\"").append(this.y)
                .append("\" r=\"2\" fill=\"black\" />");
        return sb.toString();
    }

    @Override
    public IShape copy() {
        return new Point(this.x, this.y);
    }
}
