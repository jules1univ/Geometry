package fr.univrennes.istic.l2gen.geometry.base;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("ellipse")
public final class Ellipse implements IShape {

    @SVGField({ "cx", "cy" })
    private Point center;

    @SVGField({ "rx", "ry" })
    private Point radius;

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    public Ellipse() {
        this.center = new Point(0, 0);
        this.radius = new Point(0, 0);
    }

    public Ellipse(double cx, double cy, double rx, double ry) {
        this.center = new Point(cx, cy);
        this.radius = new Point(rx, ry);
    }

    public Ellipse(Point center, Point radius) {
        this(center.getX(), center.getY(), radius.getX(), radius.getY());
    }

    @Override
    public double getWidth() {
        return this.radius.getX() * 2;
    }

    @Override
    public double getHeight() {
        return this.radius.getY() * 2;
    }

    @Override
    public Point getCenter() {
        return this.center;
    }

    @Override
    public SVGStyle getStyle() {
        return this.style;
    }

    @Override
    public SVGTransform getTransform() {
        return this.transform;
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(indent));
        sb.append("Ellipse");
        sb.append(" X=");
        sb.append(this.center.getX());
        sb.append(" Y=");
        sb.append(this.center.getY());
        sb.append(" RX=");
        sb.append(this.radius.getX());
        sb.append(" RY=");
        sb.append(this.radius.getY());
        return sb.toString();
    }

    @Override
    public void move(double dx, double dy) {
        this.center.add(dx, dy);
    }

    @Override
    public void resize(double px, double py) {
        this.radius.mult(px, py);
    }

    @Override
    public void rotate(double deg) {
        this.transform.rotate(deg, this.getCenter().getX(), this.getCenter().getY());
    }

    @Override
    public IShape copy() {
        return new Ellipse(this.center.getX(), this.center.getY(), this.radius.getX(), this.radius.getY());
    }

}
