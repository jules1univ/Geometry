package fr.univrennes.istic.l2gen.tests.geometry;

import org.junit.Test;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.geometry.base.Polygon;

public final class PolygonTest extends AbstractShapeTest<Polygon> {

    @Override
    public Polygon create() {
        return new Polygon(1.0, 1000.0, 5000.0, 7560.0, 3254.0, 99999.0);
    }

    @Test
    @Override
    public void testCenter() {

        Polygon poly = new Polygon(1.0, 5.0, 7.0, 60.0);
        Polygon polyVide = new Polygon();

        assert polyVide.getCenter().equals(new Point(0.0, 0.0));
        assert poly.getCenter().equals(new Point(8.0 / 2, 65.0 / 2));
    }

    @Test
    @Override
    public void testMove() {
        Polygon poly = new Polygon(1.0, 2.0);
        poly.move(1.0, 1.0);
        assert poly.equals(new Polygon(1.0, 2.0, 2.0, 3.0));
        poly.move(-1.0, -1.0);
        assert poly.equals(new Polygon(1.0, 2.0));

        poly = new Polygon(1.0, 2.0, 5.0, 10.0);
        poly.move(1.0, 1.0);
        assert poly.equals(new Polygon(2.0, 3.0, 6.0, 11.0));
        poly.move(-1.0, -1.0);
        assert poly.equals(new Polygon(1.0, 2.0, 5.0, 10.0));
    }

    @Test
    @Override
    public void testResize() {
        Polygon poly = new Polygon(20.0, 30.0);
        poly.resize(10.0, 20.0);
        assert poly != null;
    }

    @Test
    @Override
    public void testDescription() {
        Polygon poly = new Polygon();
        assert poly.getDescription(1).equals("Polygone 0,0");
        poly = new Polygon(1.0, 5.0, 6.0, 7.0);
        assert poly.getDescription(1).equals("Polygone 1,5 6,7");
        poly = new Polygon(1.0, 5.0, 6.0, 7.0);
        assert poly.getDescription(2).equals("Polygone 1,5  6,7");
    }

}
