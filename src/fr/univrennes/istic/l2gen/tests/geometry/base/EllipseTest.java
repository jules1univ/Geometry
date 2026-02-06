package fr.univrennes.istic.l2gen.tests.geometry.base;

import org.junit.Test;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.geometry.base.Ellipse;
import fr.univrennes.istic.l2gen.tests.geometry.AbstractShapeTest;

public class EllipseTest extends AbstractShapeTest<Ellipse> {

    @Override
    public Ellipse create() {
        return new Ellipse(10.0, 20.0, 30.0, 40.0);
    }

    @Test
    @Override
    public void testCenter() {
        assert create().getCenter().equals(new Point(10.0, 20.0));
    }

    @Test
    @Override
    public void testMove() {
        Ellipse ellipse = create();
        ellipse.move(10.0, 10.0);
        assert ellipse.getCenter().equals(new Point(20.0, 30.0));
    }

    @Test
    @Override
    public void testResize() {
        Ellipse ellipse = create();
        ellipse.resize(2.0, 2.0);
        assert ellipse.getWidth() == 60.0 * 2;
        assert ellipse.getHeight() == 80.0 * 2;
    }

    @Test
    @Override
    public void testDescription() {
        assert create().getDescription(0).contains("RX=30.0");
    }

}
