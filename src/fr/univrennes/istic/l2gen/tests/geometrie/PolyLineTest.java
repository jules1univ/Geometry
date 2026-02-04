package fr.univrennes.istic.l2gen.tests.geometrie;

import fr.univrennes.istic.l2gen.geometrie.base.PolyLine;
import fr.univrennes.istic.l2gen.geometrie.Point;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PolyLineTest implements IShapeTest<PolyLine> {

    @Override
    public PolyLine create() {
        return new PolyLine(0, 0, 4, 0, 3, 4, 0, 4);
    }

    @Test
    @Override
    public void testCenter() {
        PolyLine line = create();
        Point center = line.getCenter();
        Point centreLogique = new Point((0 + 4 + 3 + 0) / 4.0, (0 + 0 + 4 + 4) / 4.0);
        assertEquals(centreLogique.getX(), center.getX(), 0.0001);
        assertEquals(centreLogique.getY(), center.getY(), 0.0001);
    }

    @Test
    @Override
    public void testMove() {
        PolyLine line = create();
        PolyLine lineAfterMove = new PolyLine(2, 3, 6, 3, 5, 7, 2, 7);
        line.move(2, 3);
        assertEquals(lineAfterMove.getDescription(0), line.getDescription(0));

    }

    // TODO: à vérifier
    @Test
    @Override
    public void testResize() {
        PolyLine line = create();
        PolyLine lineAfterResize = new PolyLine(-1.75, -2, 6.25, -2, 4.25, 6, -1.75, 6);
        line.resize(2, 2);
        assertEquals(lineAfterResize.getDescription(0), line.getDescription(0));
    }

    @Test
    @Override
    public void testDescription() {
        PolyLine line = create();
        String expectedDescription = "Ligne 0.0,0.0 4.0,0.0 3.0,4.0 0.0,4.0 ";
        assertEquals(expectedDescription, line.getDescription(0));
    }

}
