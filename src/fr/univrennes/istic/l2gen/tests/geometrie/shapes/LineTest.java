package fr.univrennes.istic.l2gen.tests.geometrie.shapes;

import fr.univrennes.istic.l2gen.geometrie.base.Line;
import fr.univrennes.istic.l2gen.geometrie.Point;

import static org.junit.Assert.assertEquals;

public class LineTest implements IShapeTest<Line> {

    @Override
    public Line create() {
        return new Line(0, 0, 4, 0, 3, 4, 0, 4);
    }

    @Test
    @Override
    public void testCenter() {
        Line line = create();
        Point center = line.getCenter();
        Point centreLogique = new Point((0 + 4 + 3 + 0) / 4.0, (0 + 0 + 4 + 4) / 4.0);
        assertEquals(centreLogique.getX(), center.getX(), 0.0001);
        assertEquals(centreLogique.getY(), center.getY(), 0.0001);
    }

    @Test
    @Override
    public void testMove() {
        Line line = create();
        Line lineAfterMove = new Line(2, 3, 6, 3, 5, 7, 2, 7);
        line.move(2, 3);
        assertEquals(lineAfterMove.getDescription(0), line.getDescription(0));

    }

    // à vérifier
    @Test
    @Override
    public void testResize() {
        Line line = create();
        Line lineAfterResize = new Line(-1.75, -2, 6.25, -2, 4.25, 6, -1.75, 6);
        line.resize(2, 2);
        assertEquals(lineAfterResize.getDescription(0), line.getDescription(0));
    }

    @Test
    @Override
    public void testDescription() {
        Line line = create();
        String expectedDescription = "Ligne 0.0,0.0 4.0,0.0 3.0,4.0 0.0,4.0 ";
        assertEquals(expectedDescription, line.getDescription(0));
    }

}
