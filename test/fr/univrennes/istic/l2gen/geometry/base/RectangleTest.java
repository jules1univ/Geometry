package fr.univrennes.istic.l2gen.geometry.base;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.geometry.AbstractShapeTest;

public final class RectangleTest extends AbstractShapeTest<Rectangle> {

    @Override
    public Rectangle create() {
        return new Rectangle(0, 0, 10, 10);
    }

    @Override
    public void testCenter() {
        assert create().getCenter().equals(new Point(10 / 2, 10 / 2));
    }

    @Override
    public void testMove() {
        Rectangle r = create();
        r.move(5, 5);
        assert r.getCenter().equals(new Point(10 / 2 + 5, 10 / 2 + 5));
    }

    @Override
    public void testResize() {
        Rectangle r = create();
        r.resize(2, 2);

        assert r.getWidth() == 20;
        assert r.getHeight() == 20;

        r.resize(0.5, 0.5);
        assert r.getWidth() == 10;
        assert r.getHeight() == 10;
    }

    @Override
    public void testDescription() {
        Rectangle r = create();
        assert "Rectangle X=5.0 Y=5.0 W=10.0 H=10.0".compareTo(r.getDescription(0)) == 0;
    }

}
