package fr.univrennes.istic.l2gen.geometry.base;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.svg.attributes.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.SVGTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("line")
public class Line implements IShape {

    @SVGField({ "x1", "y1" })
    private Point start;

    @SVGField({ "x2", "y2" })
    private Point end;

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    public Line() {
        this.start = new Point(0, 0);
        this.end = new Point(0, 0);
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    public Point getStart() {
        return this.start;
    }

    public Point getEnd() {
        return this.end;
    }

    @Override
    public double getWidth() {
        return Math.abs(this.end.getX() - this.start.getX());
    }

    @Override
    public double getHeight() {
        return Math.abs(this.end.getY() - this.start.getY());
    }

    @Override
    public Point getCenter() {
        return new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
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
        sb.append("Line ");
        sb.append(" X1=").append(this.start.getX());
        sb.append(" Y1=").append(this.start.getY());
        sb.append(" X2=").append(this.end.getX());
        sb.append(" Y2=").append(this.end.getY());
        return sb.toString();
    }

    @Override
    public void move(double dx, double dy) {
        this.start = new Point(this.start.getX() + dx, this.start.getY() + dy);
        this.end = new Point(this.end.getX() + dx, this.end.getY() + dy);
    }

    @Override
    public void resize(double px, double py) {
        this.start = new Point(this.start.getX() * px, this.start.getY() * py);
        this.end = new Point(this.end.getX() * px, this.end.getY() * py);
    }

    @Override
    public void rotate(double deg) {
        this.transform.rotate(deg, this.getCenter().getX(), this.getCenter().getY());
    }

    @Override
    public IShape copy() {
        return new Line(this.start.getX(), this.start.getY(), this.end.getX(), this.end.getY());
    }

}
