package fr.univrennes.istic.l2gen.geometry;

import javax.management.relation.RoleList;

import fr.univrennes.istic.l2gen.geometry.base.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class GroupTest extends AbstractShapeTest<Group> {

    Group grpRect = null;

    @Override
    public Group create() {
        Group grpRect = new Group(new ArrayList<IShape>());
        return grpRect;
    }

    @Before
    public void testCreate() {
        grpRect = new Group(new ArrayList<IShape>());
    }

    @Test
    public void testAdd() {
        grpRect.add(new Rectangle());
        /*
         * grpRect.add(new Circle());
         * grpRect.add(new Ellipse());
         * grpRect.add(new Line());
         * grpRect.add(new Polygon());
         * grpRect.add(new PolyLine());
         * grpRect.add(new Text());
         * grpRect.add(new Triangle());
         * grpRect.add(new Path());
         * grpRect.add(new Point());
         */
    }

    @Test
    @Override
    public void testCenter() {
        assertTrue(grpRect.getCenter().equals(new Point(0, 0)));
    }

    @Test
    @Override
    public void testMove() {

        Group grpTest = new Group(new ArrayList<IShape>());
        grpTest.add(new Rectangle(new Point(1, 1), 0, 0));

        grpRect.move(1.0, 1.0);
        assertTrue(grpRect.equals(grpTest));
    }

    @Test
    @Override
    public void testResize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testResize'");
    }

    @Test
    @Override
    public void testDescription() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testDescription'");
    }

}
