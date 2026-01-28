package fr.univrennes.istic.l2gen.geometrie.model.shapes;

import fr.univrennes.istic.l2gen.geometrie.model.Point;

public interface IShape {

    double getWidth();

    double getHeight();

    Point getCenter();


    void move(double dx, double dy);

    void resize(double px, double py);

    String getDescription(int indent);

    String toSVG();

    IShape copy();
}