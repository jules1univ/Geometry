package fr.univrennes.istic.l2gen.geometry.base;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGContent;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("text")
public final class Text implements IShape {

    @SVGContent
    private final String text;

    @SVGField({ "x", "y" })
    private Point center;

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    public Text() {
        this.center = new Point(0, 0);
        this.text = "";
    }

    public Text(double x, double y, String text) {
        this.center = new Point(x, y);
        this.text = text;
    }

    @Override
    public double getWidth() {
        return this.text.length();
    }

    @Override
    public double getHeight() {
        return 1;
    }

    @Override
    public Point getCenter() {
        return this.center;
    }

    @Override
    public SVGStyle getStyle() {
        return this.style;
    }

    public SVGTransform getTransform() {
        return this.transform;
    }

    @Override
    public String getDescription(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, indentation)));
        sb.append("Text ");
        sb.append("VALUE=");
        sb.append(text);
        sb.append(" X=");
        sb.append(this.center.getX());
        sb.append(" Y=");
        sb.append(this.center.getY());
        return sb.toString();
    }

    @Override
    public void move(double dx, double dy) {
        this.center.add(dx, dy);
    }

    @Override
    public void resize(double px, double py) {
        this.transform.scale(px, py);
    }

    @Override
    public void rotate(double deg) {
        this.transform.rotate(deg, this.getCenter().getX(), this.getCenter().getY());
    }

    @Override
    public IShape copy() {
        return new Text(this.center.getX(), this.center.getY(), this.text);
    }

}
