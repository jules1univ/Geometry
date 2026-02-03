package fr.univrennes.istic.l2gen.geometrie.shapes;

import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPoint;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointX;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointY;

@SVGPoint
public final class Point implements IShape {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @SVGPointX()
    public double getX() {
        return x;
    }

    @SVGPointY()
    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
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
    public void rotate(double deg) {
        double rad = Math.toRadians(deg);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        double newX = x * cos - y * sin;
        double newY = x * sin + y * cos;
        this.x = newX;
        this.y = newY;
    }

    @Override
    public IShape copy() {
        return new Point(this.x, this.y);
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
}
