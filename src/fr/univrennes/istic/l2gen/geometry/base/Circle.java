package fr.univrennes.istic.l2gen.geometry.base;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.field.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.tag.SVGTag;

/**
 * Représente un cercle implémentant l'interface IShape.
 * Un cercle est défini par un centre et un rayon.
 */
@SVGTag("circle")
public final class Circle implements IShape {

    @SVGField("r")
    private double radius;

    @SVGField({ "cx", "cy" })
    private Point center;

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    /**
     * Constructeur par défaut. Crée un cercle avec un centre à l'origine et un
     * rayon nul.
     */
    public Circle() {
        this.radius = 0;
        this.center = new Point(0, 0);
    }

    /**
     * Constructeur avec coordonnées du centre et rayon.
     * 
     * @param x      la coordonnée x du centre
     * @param y      la coordonnée y du centre
     * @param radius le rayon du cercle
     */
    public Circle(double x, double y, double radius) {
        this.radius = radius;
        this.center = new Point(x, y);
    }

    /**
     * Constructeur avec point centre et rayon.
     * 
     * @param centre le centre du cercle
     * @param radius le rayon du cercle
     */
    public Circle(Point centre, double radius) {
        this.center = centre;
        this.radius = radius;
    }

    /**
     * Retourne la hauteur du cercle, équivalente au diamètre.
     * 
     * @return la hauteur (diamètre)
     */
    @Override
    public double getHeight() {
        return 2 * this.radius;
    }

    /**
     * Retourne la largeur du cercle, équivalente au diamètre.
     * 
     * @return la largeur (diamètre)
     */
    @Override
    public double getWidth() {
        return 2 * this.radius;
    }

    /**
     * Retourne le centre du cercle.
     * 
     * @return le point centre
     */
    @Override
    public Point getCenter() {
        return this.center;
    }

    /**
     * Retourne le style SVG du cercle.
     * 
     * @return le style SVG
     */
    @Override
    public SVGStyle getStyle() {
        return this.style;
    }

    /**
     * Retourne la transformation SVG du cercle.
     * 
     * @return la transformation SVG
     */
    @Override
    public SVGTransform getTransform() {
        return this.transform;
    }

    /**
     * Retourne une description textuelle du cercle avec la position et le rayon.
     * 
     * @param indentation le nombre d'espaces pour l'indentation
     * @return une string décrivant le cercle
     */
    @Override
    public String getDescription(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(Math.max(0, indentation)));
        sb.append("Circle ");
        sb.append("C=");
        sb.append(this.center.toString());
        sb.append(" R=");
        sb.append(this.radius);
        return sb.toString();
    }

    /**
     * Déplace le centre du cercle à une nouvelle position.
     * 
     * @param x la nouvelle coordonnée x
     * @param y la nouvelle coordonnée y
     */
    @Override
    public void move(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * on ignore le deuxième argument, la valeur n'a pas d'impact, cependant il est
     * nécessaire d'en avoir une pour appeler la méthode
     * 
     * 
     * multiplie le rayon du cercle par la valeur en entrée
     * 
     * @param py mettez 0 ou -1 par exemple
     */
    @Override
    public void resize(double px, double py) {
        this.radius = this.radius * px;
    }

    /**
     * Effectue une rotation du cercle.
     * Cette opération n'a aucun effet car un cercle est invariant par rotation.
     * 
     * @param deg l'angle de rotation en degrés
     */
    @Override
    public void rotate(double deg) {
        // Ne rien faire car le cercle est invariant par rotation
    }

    @Override
    public IShape copy() {
        return new Circle(this.center, this.radius);
    }

}
