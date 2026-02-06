package fr.univrennes.istic.l2gen.tests.geometry.base;

import org.junit.Test;

import fr.univrennes.istic.l2gen.geometry.base.Text;
import fr.univrennes.istic.l2gen.tests.geometry.AbstractShapeTest;

public class TextTest extends AbstractShapeTest<Text> {

    @Override
    public Text create() {
        return new Text(0, 0, "Hello World");
    }

    @Test
    @Override
    public void testCenter() {
        Text text = create();
        assert text.getCenter().getX() == 0;
        assert text.getCenter().getY() == 0;
    }

    @Test
    @Override
    public void testMove() {
        Text text = create();
        text.move(10, 15);
        assert text.getCenter().getX() == 10;
        assert text.getCenter().getY() == 15;
    }

    @Test
    @Override
    public void testResize() {
        Text text = create();
        text.resize(200, 50);

        assert text.getWidth() != 200;
        assert text.getHeight() != 50;
    }

    @Test
    @Override
    public void testDescription() {
        Text text = create();
        String desc = text.getDescription(1);
        assert desc.contains("Text");
        assert desc.contains("Hello World");
    }

}
