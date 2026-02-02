package fr.univrennes.istic.l2gen.geometrie.shapes.base;

import fr.univrennes.istic.l2gen.geometrie.infrastructure.xml.model.XMLTag;
import fr.univrennes.istic.l2gen.geometrie.shapes.AbstractShape;
import fr.univrennes.istic.l2gen.geometrie.shapes.Point;

public final class Text extends AbstractShape {
    private final String text;
    private Point center;

    public Text(double x, double y, String text) {
        super("text");
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
    public AbstractShape copy() {
        return new Text(this.center.getX(), this.center.getY(), this.text);
    }

    @Override
    public void resize(double px, double py) {
        // Ne rien faire car le texte n'est pas redimensionnable
    }

    @Override
    public XMLTag toSVG() {

        this.setAttribute("x", this.center.getX());
        this.setAttribute("y", this.center.getY());
        this.setAttribute("font-family", "Arial");
        this.setAttribute("font-size", "64");
        this.setAttribute("fill", "black");
        this.setAttribute("text-anchor", "middle");
        this.setTextContent(this.text);

        return this;
    }

}
