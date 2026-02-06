package fr.univrennes.istic.l2gen.tests.geometry.base;

import org.junit.Test;

import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.geometry.base.Line;
import fr.univrennes.istic.l2gen.tests.geometry.AbstractShapeTest;

public class LineTest extends AbstractShapeTest<Line> {

    @Override
    public Line create() {
        return new Line(0, 0, 10, 10);
    }

    @Test
    @Override
    public void testCenter() {
        assert create().getCenter().equals(new Point(5, 5));
    }

    @Test
    @Override
    public void testMove() {
        Line line = create();
        line.move(5, 5);
        assert line.getStart().equals(new Point(5, 5));
        assert line.getEnd().equals(new Point(15, 15));
    }

    @Test
    @Override
    public void testResize() {
        Line line = create();
        line.resize(2, 2);
        assert line.getStart().equals(new Point(0, 0));
        assert line.getEnd().equals(new Point(20, 20));
    }

    @Test
    @Override
    public void testDescription() {
        assert create().getDescription(0).contains("Y1=0.0");
    }

}
