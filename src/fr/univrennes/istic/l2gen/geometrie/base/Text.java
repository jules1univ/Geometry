package fr.univrennes.istic.l2gen.geometrie.base;

import fr.univrennes.istic.l2gen.geometrie.IShape;
import fr.univrennes.istic.l2gen.geometrie.Point;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGStyle;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("text")
@SVGStyle("text-anchor:middle;")
public final class Text implements IShape {

    @SVGField
    private final String text;

    @SVGField({ "x", "y" })
    private Point center;

    // private Transform transform;

    public Text(double x, double y, String text) {
        this.center = new Point(x, y);
        this.text = text;
    }

    @Override
    public String getDescription(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, indentation)));
        sb.append("Text ");
        sb.append(text);
        sb.append(" x=");
        sb.append(this.center.getX());
        sb.append(" y=");
        sb.append(this.center.getY());
        return sb.toString();
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
    public void move(double dx, double dy) {
        this.center.add(dx, dy);
    }

    @Override
    public IShape copy() {
        return new Text(this.center.getX(), this.center.getY(), this.text);
    }

    @Override
    public void resize(double px, double py) {
        // Ne rien faire car le texte n'est pas redimensionnable
    }

    @Override
    public void rotate(double deg) {
        // Ne rien faire car le texte reste du texte après rotation
    }
}
