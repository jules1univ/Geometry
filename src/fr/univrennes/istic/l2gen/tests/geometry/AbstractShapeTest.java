package fr.univrennes.istic.l2gen.tests.geometry;

import org.junit.Ignore;
import org.junit.Test;

import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.io.SVGExport;
import fr.univrennes.istic.l2gen.svg.io.SVGImport;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;

@Ignore("Abstract Shape Test")
public abstract class AbstractShapeTest<T extends IShape> {

    /**
     * Créé un objet qui hérite de IShape pour l'utliser a travers les test
     * **Important** cette fonction n'est pas un test il ne faut pas ajouter une
     * annotation `@Test`
     * 
     * @return T extends IShape
     */
    public abstract T create();

    public abstract void testCenter();

    public abstract void testMove();

    public abstract void testResize();

    public abstract void testDescription();

    @Test
    public final void testSVG() {
        T shape = create();
        assert shape != null;

        SVGImport.register(Point.class);
        SVGImport.register(shape.getClass());

        XMLTag outTag = SVGExport.convert(shape);
        ISVGShape outSvgShape = SVGImport.convert(outTag);

        assert outSvgShape instanceof IShape;
        IShape outShape = (IShape) outSvgShape;

        assert shape.getWidth() == outShape.getWidth();
        assert shape.getHeight() == outShape.getHeight();
        assert shape.getCenter().equals(outShape.getCenter());
        assert shape.getDescription(0).equals(outShape.getDescription(0));
    }

    @Test
    public final void testCopy() {
        T shape = create();
        assert shape != null;

        IShape copy = shape.copy();
        assert copy != null;

        assert shape.getWidth() == copy.getWidth();
        assert shape.getHeight() == copy.getHeight();
        assert shape.getCenter().equals(copy.getCenter());

        assert shape != copy;
    }

}
