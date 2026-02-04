package fr.univrennes.istic.l2gen.tests.geometrie;

import org.jspecify.annotations.NonNull;
import org.junit.Test;

import fr.univrennes.istic.l2gen.geometrie.IShape;

public interface IShapeTest<T extends IShape> {

    // TODO: ajouter des tests pour la rotation & pour les autres formes

    /**
     * Créé un objet qui hérite de IShape pour l'utliser a travers les test
     * **Important** cette fonction n'est pas un test il ne faut pas ajouter une
     * annotation `@Test`
     * 
     * @return T extends IShape
     */
    @NonNull
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
