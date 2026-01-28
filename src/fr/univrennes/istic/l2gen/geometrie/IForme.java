package fr.univrennes.istic.l2gen.geometrie;

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