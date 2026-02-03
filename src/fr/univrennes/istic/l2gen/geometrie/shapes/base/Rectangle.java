package fr.univrennes.istic.l2gen.geometrie.shapes.base;

import fr.univrennes.istic.l2gen.geometrie.shapes.IShape;
import fr.univrennes.istic.l2gen.geometrie.shapes.Point;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGStyle;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("rect")
@SVGStyle("fill:white;stroke:black;stroke-width:1")
public final class Rectangle implements IShape {

    @SVGField({ "x", "y" })
    private Point position;

    @SVGField({ "width", "height" })
    private Point size;

    public Rectangle(Point position, double width, double height) {
        this(position.getX(), position.getY(), width, height);
    }

    public Rectangle(double x, double y, double width, double height) {
        this.position = new Point(x - width / 2, y - height / 2);
        this.size = new Point(width, height);
    }

    public double getHeight() {
        return this.size.getY();
    }

    public double getWidth() {
        return this.size.getX();
    }

    @Override
    public Point getCenter() {
        return new Point(this.position.getX() + this.size.getX() / 2,
                this.position.getY() + this.size.getY() / 2);
    }

    @Override
    public String getDescription(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(indentation));
        sb.append("Rectangle");
        sb.append(" X=");
        sb.append(this.getCenter().getX());
        sb.append(" Y=");
        sb.append(this.getCenter().getY());
        sb.append(" L=");
        sb.append(this.size.getX());
        sb.append(" H=");
        sb.append(this.size.getY());
        return sb.toString();
    }

    @Override
    public void move(double dx, double dy) {
        this.position.add(dx, dy);
    }

    @Override
    public void resize(double px, double py) {
        this.size.mult(px, py);
    }

    @Override
    public void rotate(double deg) {
        // No-op: rectangle remains rectangle after rotation (no orientation stored)
    }

    @Override
    public IShape copy() {
        return new Rectangle((Point) this.position.copy(), this.size.getX(), this.size.getY());
    }

}
