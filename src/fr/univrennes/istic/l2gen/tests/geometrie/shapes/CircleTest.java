package fr.univrennes.istic.l2gen.tests.geometrie.shapes;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.univrennes.istic.l2gen.geometrie.base.Circle;

public class CircleTest implements IShapeTest<Circle> {

    @Override
    public Circle create() {
        Circle c1 = new Circle(0, 0, 1);
        return c1;

        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Test
    public void testCenter() {
        Circle c2 = new Circle(1, 1, 1);
        assertTrue(c2.getCenter().getX() == 1);
        assertTrue(c2.getCenter().getY() == 1);
    }

    @Test
    public void testMove() {
        Circle c3 = new Circle(5, 5, 1);
        c3.move(1, 1);
        assertTrue(c3.getCenter().getX() == 1);
        assertTrue(c3.getCenter().getY() == 1);
    }

    @Test
    public void testResize() {
        Circle c4 = new Circle(2, 2, 5);
        c4.resize(2, 0);
        assertTrue(c4.getHeight() == 20);
    }

    @Test
    public void testDescription() {
        Circle c5 = new Circle(1, 2, 3);

        assertTrue("      Circle Centre=1,2 r=3.0".compareTo(c5.getDescription(3)) == 0);
    }

}
