package fr.univrennes.istic.l2gen.geometry;

import fr.univrennes.istic.l2gen.svg.attributes.path.SVGPath;
import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("path")
public class Path implements IShape {

    @SVGField("d")
    private SVGPath path;

    @SVGField({ "x", "y" })
    private Point position = new Point(0, 0);

    @SVGField
    private SVGTransform transform = new SVGTransform();

    @SVGField
    private SVGStyle style = new SVGStyle();

    public Path() {
        this.path = new SVGPath();
    }

    public Path(SVGPath path) {
        if (path == null) {
            throw new IllegalArgumentException("SVGPath cannot be null");
        }
        this.path = path;
    }

    public SVGPath draw() {
        return path;
    }

    @Override
    public double getWidth() {
        return this.path.getBoundingBox().getWidth();
    }

    @Override
    public double getHeight() {
        return this.path.getBoundingBox().getHeight();
    }

    @Override
    public Point getCenter() {
        return new Point(this.path.getBoundingBox().getCenterX(), this.path.getBoundingBox().getCenterY());
    }

    @Override
    public SVGStyle getStyle() {
        return style;
    }

    @Override
    public SVGTransform getTransform() {
        return transform;
    }

    @Override
    public String getDescription(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, indent)));
        sb.append("Path");
        sb.append(" D=");
        sb.append(path.getContent());
        return sb.toString();
    }

    @Override
    public void move(double dx, double dy) {
        this.position.add(dx, dy);
    }

    @Override
    public void resize(double scaleX, double scaleY) {
        this.transform.scale(scaleX, scaleY);
    }

    @Override
    public void rotate(double degrees) {
        this.transform.rotate(degrees, this.getCenter().getX(), this.getCenter().getY());
    }

    @Override
    public IShape copy() {
        return new Path(this.path);
    }

}