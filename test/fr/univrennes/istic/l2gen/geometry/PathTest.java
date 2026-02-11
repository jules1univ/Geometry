package fr.univrennes.istic.l2gen.geometry;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PathTest extends AbstractShapeTest<Path> {
    // Test en cours
    @Override
    public Path create() {
        Path path = new Path();
        return path;

    }

    @Test
    @Override
    public void testCenter() {
        Path path = create();
        Point center = path.getCenter();
        Point ExpectedCenter = new Point(0, 0);
        assertEquals(ExpectedCenter, center);
    }

    @Test
    @Override
    public void testMove() {
        Path p = create();
        // le center reste a 0 0 car il n'a pas de bouding box
        p.move(5, 5);
        assertEquals(new Point(0, 0), p.getCenter());
    }

    @Test
    @Override
    public void testResize() {
        Path p = create();
        p.resize(2, 3);
        String content = p.getTransform().getContent();
        assertEquals(true, content.contains("scale("));
    }

    @Test
    @Override
    public void testDescription() {
        Path p = create();
        String expected = "Path D=" + p.draw().getContent();
        assertEquals(expected, p.getDescription(0));
    }

}
