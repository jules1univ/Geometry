package fr.univrennes.istic.l2gen.geometrie.tests.shapes;

import org.junit.Test;

import fr.univrennes.istic.l2gen.geometrie.shapes.AbstractShape;

public abstract class AbstractShapeTest {

    public abstract AbstractShape create();

    @Test
    public abstract void testCenter();

    @Test
    public abstract void testMove();

    @Test
    public abstract void testResize();

    @Test
    public abstract void testDescription();

    @Test
    public abstract void testSVG();

    @Test
    public final void testCopy() {
        AbstractShape shape = create();
        AbstractShape copy = shape.copy();

        assert shape.getWidth() == copy.getWidth();
        assert shape.getHeight() == copy.getHeight();
        assert shape.getCenter().equals(copy.getCenter());

        assert shape != copy;
    }

}
