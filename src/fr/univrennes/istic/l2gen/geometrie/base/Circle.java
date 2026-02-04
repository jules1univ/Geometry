package fr.univrennes.istic.l2gen.geometrie.base;

import fr.univrennes.istic.l2gen.geometrie.IShape;
import fr.univrennes.istic.l2gen.geometrie.Point;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("circle")
public final class Circle implements IShape {

    @SVGField("r")
    private double radius;

    @SVGField({ "cx", "cy" })
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
        sb.append("  ".repeat(Math.max(0, indentation)));
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

    /**
     * on ignore le deuxième argument, la valeur n'a pas d'impact, cependant il est
     * nécessaire d'en avoir une pour appeler la méthode
     * 
     * @utilité
     *          multiplie le rayon du cercle par la valeur en entrée
     * 
     * @param py mettez 0 ou -1 par exemple
     */
    @Override
    public void resize(double px, double py) {
        this.radius = this.radius * px;
    }

    @Override
    public void rotate(double deg) {
        // Ne rien faire car le cercle est invariant par rotation
    }
}
