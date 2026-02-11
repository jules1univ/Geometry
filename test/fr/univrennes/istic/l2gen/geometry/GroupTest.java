package fr.univrennes.istic.l2gen.geometry;

import javax.management.relation.RoleList;

import fr.univrennes.istic.l2gen.geometry.base.Rectangle;

import java.util.ArrayList;

public class GroupTest extends AbstractShapeTest<Group<Rectangle>> {

    @Override
    public Group<Rectangle> create() {

        Group<Rectangle> grpRect = new Group<>(new ArrayList<Rectangle>());
        return grpRect;
    }

    @Override
    public void testCenter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testCenter'");
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
