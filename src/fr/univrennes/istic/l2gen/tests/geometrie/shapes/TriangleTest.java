package fr.univrennes.istic.l2gen.tests.geometrie.shapes;

import fr.univrennes.istic.l2gen.geometrie.Point;
import fr.univrennes.istic.l2gen.geometrie.base.Triangle;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TriangleTest implements IShapeTest<Triangle> {
    @Override
    public Triangle create() {
        return new Triangle(0, 0, 3, 0, 0, 4);
    }

    @Override
    public void testCenter() {
        Triangle t1 = create();
        Point center = t1.getCenter();
        List<Point> liste = t1.getVertices();

        double sumX = 0.0;
        double sumY = 0.0;
        for (Point point : liste) {
            sumX += point.getX();
            sumY += point.getY();
        }
        double centerX = sumX / liste.size();
        double centerY = sumY / liste.size();
    }

    @Override
    public void testMove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testMove'");
    }

    @Override
    public void testResize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testResize'");
    }

    @Override
    public void testDescription() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testDescription'");
    }

}
