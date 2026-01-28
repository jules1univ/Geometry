package fr.univrennes.istic.l2gen.geometrie;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.formes.IForme;

public class Groupe<T extends IForme> implements IForme {
    private List<T> shapes = new ArrayList<>();

    public Groupe(@SuppressWarnings("unchecked") T... formes) {
        for (T shape : formes) {
            this.shapes.add(shape);
        }
    }

    public Groupe(List<T> shapes) {
        this.shapes = shapes;
    }

    public void ajouter(T shape) {
        this.shapes.add(shape);
    }

    public Point centre() {
        double sumX = 0;
        double sumY = 0;
        for (IForme shape : shapes) {
            sumX += shape.centre().x();
            sumY += shape.centre().y();
        }
        return new Point(sumX / shapes.size(), sumY / shapes.size());
    }

    public double hauteur() {
        double maxY = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        for (IForme shape : shapes) {
            double topY = shape.centre().y() - shape.hauteur() / 2;
            double bottomY = shape.centre().y() + shape.hauteur() / 2;
            if (topY < minY) {
                minY = topY;
            }
            if (bottomY > maxY) {
                maxY = bottomY;
            }
        }
        return maxY - minY;
    }

    public double largeur() {
        double maxX = Double.NEGATIVE_INFINITY;
        double minX = Double.POSITIVE_INFINITY;
        for (IForme shape : shapes) {
            double leftX = shape.centre().x() - shape.largeur() / 2;
            double rightX = shape.centre().x() + shape.largeur() / 2;
            if (leftX < minX) {
                minX = leftX;
            }
            if (rightX > maxX) {
                maxX = rightX;
            }
        }
        return maxX - minX;
    }

    public void deplacer(double dx, double dy) {
        for (IForme shape : shapes) {
            shape.deplacer(dx, dy);
        }
    }

    public void redimensionner(double px, double py) {
        for (IForme shape : shapes) {
            shape.redimensionner(px, py);
        }
    }

    public Groupe<T> dupliquer() {
        return new Groupe<>(new ArrayList<>(this.shapes));
    }

    @Override
    public String description(int indentation) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(indentation));
        sb.append("Groupe\n");
        for (IForme shape : shapes) {
            sb.append(shape.description(indentation + 1));
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String enSVG() {
        StringBuilder sb = new StringBuilder();
        sb.append("<g>\n");
        for (IForme shape : shapes) {
            sb.append(shape.enSVG());
            sb.append("\n");
        }
        sb.append("</g>");
        return sb.toString();
    }
}