package fr.univrennes.istic.l2gen.geometry;

import javax.management.relation.RoleList;

import fr.univrennes.istic.l2gen.geometry.base.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class GroupTest extends AbstractShapeTest<Group> {

    Group grp = null;

    @Override
    public Group create() {
        Group grp = new Group(new ArrayList<IShape>());
        return grp;
    }

    @Before
    public void testCreate() {
        grp = new Group(new ArrayList<IShape>());
        grp.add(new Rectangle());
        grp.add(new Circle());
        grp.add(new Ellipse());
        grp.add(new Line());
        grp.add(new Polygon());
        grp.add(new PolyLine());
        grp.add(new Text());
        grp.add(new Triangle());
        grp.add(new Path());
        grp.add(new Point());
    }

    @Test
    public void testAdd() {
        grp.add(new Rectangle());
        grp.add(new Circle());
        grp.add(new Ellipse());
        grp.add(new Line());
        grp.add(new Polygon());
        grp.add(new PolyLine());
        grp.add(new Text());
        grp.add(new Triangle());
        grp.add(new Path());
        grp.add(new Point());
    }

    @Test
    @Override
    public void testCenter() {
        assertEquals(0.0, grp.getCenter().getX(), 0.1);
    }

    @Test
    @Override
    public void testMove() {
        grp.move(1.0, 1.0);
        assertEquals(1.0, grp.getCenter().getX(), 0.2);
        assertEquals(1.0, grp.getCenter().getY(), 0.2);
    }

    @Test
    @Override
    public void testResize() {
        grp.move(1.0, 1.0);
        grp.resize(-1.0, -1.0);
        assertEquals(0.5, grp.getCenter().getX(), 0.2);
        assertEquals(0.5, grp.getCenter().getY(), 0.2);

    }

    @Test
    @Override
    public void testDescription() {
        String str = grp.getDescription(1);
        String strAttendu = " Group\n" +
                "  Rectangle X=0.0 Y=0.0 L=0.0 H=0.0\n" +
                "  Circle C=0,0 R=0.0\n" +
                "  Ellipse X=0.0 Y=0.0 RX=0.0 RY=0.0\n" +
                "Line  X1=0.0 Y1=0.0 X2=0.0 Y2=0.0\n" +
                "  Polygon POINTS=\n" +
                "  PolyLine POINTS=\n" +
                "  Text VALUE= X=0.0 Y=0.0\n" +
                "  Triangle POINTS= 0.0,0.0 0.0,0.0 0.0,0.0\n" +
                "  Path D=\n" +
                "    Point 0,0";

        assertEquals(str, strAttendu);
    }

    @Test
    public void testGetWidth() {
        grp.add(new Circle(1.0, 1.0, 0.5));
        assertEquals(1.0, grp.getCenter().getWidth(), 0.1);
    }

    @Test
    public void testGetHeight() {
        grp.add(new Circle(1.0, 1.0, 0.5));
        assertEquals(1.0, grp.getCenter().getHeight(), 0.1);
    }
}
