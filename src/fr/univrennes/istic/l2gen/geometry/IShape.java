package fr.univrennes.istic.l2gen.geometry;

import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;

public interface IShape extends ISVGShape {

    double getWidth();

    double getHeight();

    Point getCenter();

    String getDescription(int indent);

    void move(double dx, double dy);

    void resize(double px, double py);

    void rotate(double deg);

    IShape copy();

}