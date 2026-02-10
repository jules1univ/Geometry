package fr.univrennes.istic.l2gen.geometry;

import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPoint;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointX;
import fr.univrennes.istic.l2gen.svg.interfaces.point.SVGPointY;

@SVGPoint
public final class Point implements IShape {

    @SVGPointX
    private double x;

    @SVGPointY
    private double y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

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
    public SVGStyle getStyle() {
        throw new UnsupportedOperationException("A Point does not support styles");
    }

    @Override
    public SVGTransform getTransform() {
        throw new UnsupportedOperationException("A Point does not support transformations");
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
        // Ne rien faire car un point reste un point après rotation
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
