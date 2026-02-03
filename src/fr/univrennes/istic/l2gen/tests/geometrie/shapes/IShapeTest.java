package fr.univrennes.istic.l2gen.tests.geometrie.shapes;

import org.junit.Test;

import fr.univrennes.istic.l2gen.geometrie.IShape;

public interface IShapeTest<T extends IShape> {

    T create();

    @Test
    void testCenter();

    @Test
    void testMove();

    @Test
    void testResize();

    @Test
    void testDescription();

    @Test
    public default void testCopy() {
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
