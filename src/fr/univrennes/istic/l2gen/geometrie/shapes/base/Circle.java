package fr.univrennes.istic.l2gen.geometrie.shapes.base;

import fr.univrennes.istic.l2gen.geometrie.infrastructure.xml.model.XMLTag;
import fr.univrennes.istic.l2gen.geometrie.shapes.AbstractShape;
import fr.univrennes.istic.l2gen.geometrie.shapes.Point;

public final class Circle extends AbstractShape {
    private double radius;
    private Point center;

    public Circle(double x, double y, double radius) {
        super("circle");
        this.radius = radius;
        this.center = new Point(x, y);
    }

    public Circle(Point centre, double radius) {
        super("circle");
        this.center = centre;
        this.radius = radius;
    }

    @Override
    public Point getCenter() {
        return this.center;
    }

    @Override
    public String getDescription(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, indentation)));
        sb.append("Circle ");
        sb.append("Centre=");
        sb.append(this.center.toString());
        sb.append(" r=");
        sb.append(this.radius);
        return sb.toString();
    }

    @Override
    public double getHeight() {
        return 2 * this.radius;
    }

    @Override
    public double getWidth() {
        return 2 * this.radius;
    }

    @Override
    public void move(double x, double y) {
        this.center = new Point(x, y);
    }

    @Override
    public AbstractShape copy() {
        return new Circle(this.center, this.radius);
    }

    @Override
    public void resize(double px, double py) {
        this.radius = this.radius * px;
    }

    @Override
    public XMLTag toSVG() {

        this.setAttribute("cx", this.center.getX());
        this.setAttribute("cy", this.center.getY());
        this.setAttribute("r", this.radius);
        this.setAttribute("fill", "white");
        this.setAttribute("stroke", "black");

        return this;
    }
}
