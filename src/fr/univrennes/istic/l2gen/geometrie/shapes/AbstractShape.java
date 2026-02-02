package fr.univrennes.istic.l2gen.geometrie.shapes;

import fr.univrennes.istic.l2gen.geometrie.infrastructure.xml.model.XMLTag;

public abstract class AbstractShape extends XMLTag {

    public AbstractShape(String name) {
        super(name);
    }

    public abstract double getWidth();

    public abstract double getHeight();

    public abstract Point getCenter();

    public abstract void move(double dx, double dy);

    public abstract void resize(double px, double py);

    public abstract String getDescription(int indent);

    public abstract XMLTag toSVG();

    public abstract AbstractShape copy();

    @Override
    public void addChild(XMLTag child) {
        throw new UnsupportedOperationException("Cannot add child to Shape");
    }
}