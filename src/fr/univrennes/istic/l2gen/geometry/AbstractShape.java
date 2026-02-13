package fr.univrennes.istic.l2gen.geometry;

import fr.univrennes.istic.l2gen.svg.attributes.style.SVGStyle;
import fr.univrennes.istic.l2gen.svg.attributes.transform.SVGTransform;
import fr.univrennes.istic.l2gen.svg.interfaces.field.SVGField;

public abstract class AbstractShape implements IShape {

    @SVGField
    protected final SVGStyle style = new SVGStyle();

    @SVGField
    protected final SVGTransform transform = new SVGTransform();

    @Override
    public void rotate(double deg) {
        this.transform.rotate(deg, this.getCenter().getX(), this.getCenter().getY());
    }

    @Override
    public final SVGStyle getStyle() {
        return this.style;
    }

    @Override
    public final SVGTransform getTransform() {
        return this.transform;
    }
}
