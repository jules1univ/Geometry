package fr.univrennes.istic.l2gen.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PathTest extends AbstractShapeTest<Path> {

    @Override
    public Path create() {
        return new Path();
    }

    @Test
    public void testWidth() {
        Path path = create();
        assertEquals(0, path.getWidth(), 0.001);

        path.draw().move(0, 0, false).line(10, 0, false).line(10, 10, false);
        assertEquals(10, path.getWidth(), 0.001);
    }

    @Test
    public void testHeight() {
        Path path = create();
        assertEquals(0, path.getHeight(), 0.001);

        path.draw().move(0, 0, false).line(0, 10, false).line(10, 10, false);
        assertEquals(10, path.getHeight(), 0.001);
    }

    @Test
    @Override
    public void testCenter() {
        Path path = create();
        assertEquals(new Point(0, 0), path.getCenter());

        path.draw().move(10, 10, false).line(20, 20, false);
        Point center = path.getCenter();
        assertNotNull(center);
    }

    @Test
    @Override
    public void testMove() {
        Path path = create();

        path.draw().move(0, 0, false).line(3, 0, false).line(0, 4, false);
        assertEquals(new Point(3 / 2.0, 4 / 2.0), path.getCenter());

        path.move(5, 5);
        assertEquals(new Point(3 / 2.0 + 5, 4 / 2.0 + 5), path.getCenter());

        path.move(-10, -15);
        assertEquals(new Point(3 / 2.0 - 5, 4 / 2.0 - 10), path.getCenter());
    }

    @Test
    @Override
    public void testResize() {
        Path path = create();

        path.draw().move(0, 0, false).line(3, 0, false).line(0, 4, false);

        path.resize(2, 3);
        String content = path.getTransform().getContent();
        assertTrue(content.contains("scale("));
        assertTrue(content.contains("2") || content.contains("3"));

        Path p2 = create();
        p2.resize(1, 1);
        assertNotNull(p2.getTransform());
    }

    @Test
    @Override
    public void testDescription() {
        Path path = create();

        String expected = "Path D=" + path.draw().getContent();
        assertEquals(expected, path.getDescription(0));
    }

    @Test
    public void testEmptyPath() {
        Path emptyPath = create();
        assertNotNull(emptyPath.draw());
        assertNotNull(emptyPath.getDescription(0));
    }

    @Test
    public void testComplexPath() {
        Path path = create();

        path.draw()
                .move(0, 0, false)
                .line(10, 0, false)
                .line(10, 10, false)
                .line(0, 10, false)
                .close();

        assertNotNull(path.getCenter());
        assertNotNull(path.getDescription(0));
    }
}
