package fr.univrennes.istic.l2gen.geometrie;

public class Rectangle implements IForme {

    private Point center;
    private Point size;

    public Rectangle(double x, double y, double width, double height) {
        this.center = new Point(x, y);
        this.size = new Point(width, height);
    }

    public Rectangle(Point position, double width, double height) {
        this.center = new Point(position.x(), position.y());
        this.size = new Point(width, height);
    }

    public double hauteur() {
        return this.size.y();
    }

    public double largeur() {
        return this.size.x();
    }

    public Point centre() {
        return center;
    }

    @Override
    public String description(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(indentation));
        sb.append("Rectangle");
        sb.append(" Centre=");
        sb.append(this.center.toString());
        sb.append(" L=");
        sb.append(this.size.x());
        sb.append(" H=");
        sb.append(this.size.y());
        return sb.toString();
    }

    @Override
    public void deplacer(double dx, double dy) {
        this.center.plus(dx, dy);
    }

    @Override
    public void redimensionner(double px, double py) {
        this.size.mult(px, py);
    }

    @Override
    public IForme dupliquer() {
        return new Rectangle(this.center.dupliquer(), this.size.x(), this.size.y());
    }

    @Override
    public String enSVG() {
        StringBuilder sb = new StringBuilder();
        sb.append("<rect ");
        sb.append("x=\"").append((this.center.x() - this.size.x() / 2)).append("\" ");
        sb.append("y=\"").append((this.center.y() - this.size.y() / 2)).append("\" ");
        sb.append("width=\"").append(this.size.x()).append("\" ");
        sb.append("height=\"").append(this.size.y()).append("\" ");
        sb.append("fill=\"white\" stroke=\"black\" ");
        sb.append("/>");
        return sb.toString();
    }

}
