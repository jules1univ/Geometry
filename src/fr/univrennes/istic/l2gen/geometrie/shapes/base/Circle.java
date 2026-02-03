package fr.univrennes.istic.l2gen.geometrie.shapes.base;

import fr.univrennes.istic.l2gen.geometrie.shapes.IShape;
import fr.univrennes.istic.l2gen.geometrie.shapes.Point;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("circle")
public final class Circle implements IShape {

    @SVGField("r")
    private double radius;

    @SVGField({ "x", "y" })
    private Point center;

    public Circle(double x, double y, double radius) {
        this.radius = radius;
        this.center = new Point(x, y);
    }

    public Circle(Point centre, double radius) {
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
    public IShape copy() {
        return new Circle(this.center, this.radius);
    }

    @Override
    public void resize(double px, double py) {
        this.radius = this.radius * px;
    }

    @Override
    public void rotate(double deg) {
        // Ne rien faire car le cercle est invariant par rotation
    }
}
