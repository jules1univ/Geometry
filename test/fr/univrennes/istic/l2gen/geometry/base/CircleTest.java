package fr.univrennes.istic.l2gen.geometry.base;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.univrennes.istic.l2gen.geometry.AbstractShapeTest;

public class CircleTest extends AbstractShapeTest<Circle> {

    /**
     * @return Circle
     */
    @Override
    public Circle create() {
        Circle c1 = new Circle(0, 0, 1);
        return c1;
    }

    @Test
    @Override
    public void testCenter() {
        Circle c2 = new Circle(1, 1, 1);
        assertTrue(c2.getCenter().getX() == 1);
        assertTrue(c2.getCenter().getY() == 1);
    }

    @Test
    @Override
    public void testMove() {
        Circle c3 = new Circle(5, 5, 1);
        c3.move(1, 1);
        assertTrue(c3.getCenter().getX() == 1);
        assertTrue(c3.getCenter().getY() == 1);
    }

    @Test
    @Override
    public void testResize() {
        Circle c4 = new Circle(2, 2, 5);
        c4.resize(2, 0);
        assertTrue(c4.getHeight() == 20);
    }

    @Test
    @Override
    public void testDescription() {
        Circle c5 = new Circle(1, 2, 3);
        assertTrue("Circle C=1,2 R=3.0".compareTo(c5.getDescription(0)) == 0);
    }
}
