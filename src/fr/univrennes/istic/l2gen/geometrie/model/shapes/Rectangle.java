package fr.univrennes.istic.l2gen.geometrie.model.shapes;

import fr.univrennes.istic.l2gen.geometrie.model.Point;

public final class Rectangle implements IShape {

    private Point center;
    private Point size;

    public Rectangle(double x, double y, double width, double height) {
        this.center = new Point(x, y);
        this.size = new Point(width, height);
    }

    public Rectangle(Point position, double width, double height) {
        this.center = new Point(position.getX(), position.getY());
        this.size = new Point(width, height);
    }

    public double getHeight() {
        return this.size.getY();
    }

    public double getWidth() {
        return this.size.getX();
    }

    public Point getCenter() {
        return center;
    }

    @Override
    public String getDescription(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(indentation));
        sb.append("Rectangle");
        sb.append(" Centre=");
        sb.append(this.center.toString());
        sb.append(" L=");
        sb.append(this.size.getX());
        sb.append(" H=");
        sb.append(this.size.getY());
        return sb.toString();
    }

    @Override
    public void move(double dx, double dy) {
        this.center.add(dx, dy);
    }

    @Override
    public void resize(double px, double py) {
        this.size.mult(px, py);
    }

    @Override
    public IShape copy() {
        return new Rectangle((Point)this.center.copy(), this.size.getX(), this.size.getY());
    }

    @Override
    public String toSVG() {
        StringBuilder sb = new StringBuilder();
        sb.append("<rect ");
        sb.append("x=\"").append((this.center.getX() - this.size.getX() / 2)).append("\" ");
        sb.append("y=\"").append((this.center.getY() - this.size.getY() / 2)).append("\" ");
        sb.append("width=\"").append(this.size.getX()).append("\" ");
        sb.append("height=\"").append(this.size.getY()).append("\" ");
        sb.append("fill=\"white\" stroke=\"black\" ");
        sb.append("/>");
        return sb.toString();
    }

}
