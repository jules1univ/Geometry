package fr.univrennes.istic.l2gen.geometrie.shapes;

import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;

public interface IShape {

    double getWidth();

    double getHeight();

    Point getCenter();

    String getDescription(int indent);

    void move(double dx, double dy);

    void resize(double px, double py);

    void rotate(double deg);

    XMLTag toSVG();

    IShape copy();

}