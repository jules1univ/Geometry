package fr.univrennes.istic.l2gen.geometrie.shapes.base;

import fr.univrennes.istic.l2gen.geometrie.shapes.IShape;
import fr.univrennes.istic.l2gen.geometrie.shapes.Point;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;

public final class Text implements IShape {
    private final String text;
    private Point center;

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

    @Override
    public XMLTag toSVG() {
        XMLTag textTag = new XMLTag("text");
        textTag.setAttribute("x", this.center.getX());
        textTag.setAttribute("y", this.center.getY());
        textTag.setAttribute("font-family", "Arial");
        textTag.setAttribute("font-size", "64");
        textTag.setAttribute("fill", "black");
        textTag.setAttribute("text-anchor", "middle");
        textTag.setTextContent(this.text);
        return textTag;
    }

}
