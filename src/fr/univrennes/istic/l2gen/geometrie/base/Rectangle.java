package fr.univrennes.istic.l2gen.geometrie.base;

import fr.univrennes.istic.l2gen.geometrie.IShape;
import fr.univrennes.istic.l2gen.geometrie.Point;
import fr.univrennes.istic.l2gen.svg.attributes.SVGTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("rect")
public final class Rectangle implements IShape {

    @SVGField({ "x", "y" })
    private Point position;

    @SVGField({ "width", "height" })
    private Point size;

    @SVGField({ "rx", "ry" })
    private Point radius;

    @SVGField
    private SVGTransform transform = new SVGTransform();

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
        double newWidth = this.size.getX() * px;
        double newHeight = this.size.getY() * py;

        double dx = (this.size.getX() - newWidth) / 2;
        double dy = (this.size.getY() - newHeight) / 2;

        this.position.add(dx, dy);

        this.size.setX(newWidth);
        this.size.setY(newHeight);
    }

    @Override
    public void rotate(double deg) {
        this.transform.rotate(deg);
    }

    public void radius(double rx, double ry) {
        if (rx < 0 || ry < 0 || rx > this.size.getX() / 2 || ry > this.size.getY() / 2) {
            throw new IllegalArgumentException(
                    "Invalid border radius values, must be between 0 and half of width/height");
        }
        this.radius = new Point(rx, ry);
    }

    @Override
    public IShape copy() {
        return new Rectangle((Point) this.position.copy(), this.size.getX(), this.size.getY());
    }

}
