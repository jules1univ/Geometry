package fr.univrennes.istic.l2gen.geometrie.shapes;

import fr.univrennes.istic.l2gen.geometrie.xml.model.XMLTag;

public interface IShape {

    double getWidth();

    double getHeight();

    Point getCenter();

    void move(double dx, double dy);

    void resize(double px, double py);

    String getDescription(int indent);

    XMLTag toSVG();

    IShape copy();

}