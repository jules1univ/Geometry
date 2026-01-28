package fr.univrennes.istic.l2gen.geometrie.model.formes;

import fr.univrennes.istic.l2gen.geometrie.model.Point;

public interface IForme {

    String description(int indentation);

    double largeur();

    double hauteur();

    Point centre();

    void deplacer(double dx, double dy);

    IForme dupliquer();

    void redimensionner(double px, double py);

    String enSVG();
}