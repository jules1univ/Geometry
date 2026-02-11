package fr.univrennes.istic.l2gen.geometry;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

/**
 * Représente un groupe de formes géométriques.
 * Permet de gérer collectivement un ensemble de formes avec des transformations
 * et des styles applicables à tous les éléments.
 * 
 * le type des formes contenues dans le groupe (doit implémenter
 * IShape)
 */
@SVGTag("g")
public final class Group implements IShape {

    @SVGField
    private List<IShape> shapes = new ArrayList<>();

    @SVGField
    private SVGStyle style = new SVGStyle();

    @SVGField
    private SVGTransform transform = new SVGTransform();

    /**
     * Constructeur par défaut. Crée un groupe vide.
     */
    public Group() {
    }

    public Group(List<IShape> shapes) {
        this.shapes = shapes;
    }

    public void add(IShape child) {
        this.shapes.add(child);
    }

    /**
     * Retourne le centre du groupe (moyenne des centres de toutes les formes).
     * 
     * @return le point centre du groupe
     */
    @Override
    public Point getCenter() {
        if (shapes.isEmpty()) {
            return new Point(0, 0);
        }
        double sumX = 0;
        double sumY = 0;
        for (IShape shape : shapes) {
            sumX += shape.getCenter().getX();
            sumY += shape.getCenter().getY();
        }
        return new Point(sumX / shapes.size(), sumY / shapes.size());
    }

    /**
     * Retourne la hauteur totale du groupe (différence entre le point le plus bas
     * et le plus haut).
     * 
     * @return la hauteur du groupe
     */
    @Override
    public double getHeight() {
        double maxY = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;

        for (IShape shape : shapes) {
            double topY = shape.getCenter().getY() - shape.getHeight() / 2;
            double bottomY = shape.getCenter().getY() + shape.getHeight() / 2;
            if (topY < minY) {
                minY = topY;
            }
            if (bottomY > maxY) {
                maxY = bottomY;
            }
        }
        return maxY - minY;
    }

    /**
     * Retourne la largeur totale du groupe (différence entre le point le plus
     * droite et le plus gauche).
     * 
     * @return la largeur du groupe
     */
    @Override
    public double getWidth() {
        double maxX = Double.NEGATIVE_INFINITY;
        double minX = Double.POSITIVE_INFINITY;

        for (IShape shape : shapes) {
            double leftX = shape.getCenter().getX() - shape.getWidth() / 2;
            double rightX = shape.getCenter().getX() + shape.getWidth() / 2;
            if (leftX < minX) {
                minX = leftX;
            }
            if (rightX > maxX) {
                maxX = rightX;
            }
        }
        return maxX - minX;
    }

    /**
     * Retourne le style SVG du groupe.
     * 
     * @return le style SVG du groupe
     */
    @Override
    public SVGStyle getStyle() {
        return this.style;
    }

    /**
     * Retourne la transformation SVG du groupe.
     * 
     * @return la transformation SVG du groupe
     */
    @Override
    public SVGTransform getTransform() {
        return this.transform;
    }

    /**
     * Retourne une description hiérarchique du groupe et de ses formes.
     * 
     * @param indentation le nombre d'espaces pour l'indentation
     * @return une description textuelle du groupe
     */
    @Override
    public String getDescription(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(indentation));
        sb.append("Group\n");
        for (IShape shape : shapes) {
            sb.append(shape.getDescription(indentation + 1));
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Déplace toutes les formes du groupe d'une certaine distance.
     * 
     * @param dx le déplacement selon l'axe x
     * @param dy le déplacement selon l'axe y
     */
    @Override
    public void move(double dx, double dy) {
        for (IShape shape : shapes) {
            shape.move(dx, dy);
        }
    }

    /**
     * Redimensionne toutes les formes du groupe en appliquant des facteurs
     * d'échelle.
     * 
     * @param px le facteur d'échelle selon l'axe x
     * @param py le facteur d'échelle selon l'axe y
     */
    @Override
    public void resize(double px, double py) {
        for (IShape shape : shapes) {
            shape.resize(px, py);
        }
    }

    /**
     * Effectue une rotation de toutes les formes du groupe.
     * 
     * @param deg l'angle de rotation en degrés
     */
    @Override
    public void rotate(double deg) {
        for (IShape shape : shapes) {
            shape.rotate(deg);
        }
    }

    /**
     * Crée une copie du groupe avec une nouvelle liste des formes.
     * 
     * @return une nouvelle instance de Group avec une copie de la liste des formes
     */
    @Override
    public Group copy() {
        return new Group(new ArrayList<>(this.shapes));
    }
}