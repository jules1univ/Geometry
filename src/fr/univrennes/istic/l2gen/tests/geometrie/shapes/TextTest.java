package fr.univrennes.istic.l2gen.tests.geometrie.shapes;

import fr.univrennes.istic.l2gen.geometrie.base.Text;

public class TextTest implements IShapeTest<Text> {
    // TODO Keylem add more asserts
    @Override
    public Text create() {
        return new Text(0, 0, "Hello World");
    }

    @Override
    public void testCenter() {
        Text text = create();
        assert text.getCenter().getX() == 0;
        assert text.getCenter().getY() == 0;
    }

    @Override
    public void testMove() {
        Text text = create();
        text.move(10, 15);
        assert text.getCenter().getX() == 10;
        assert text.getCenter().getY() == 15;
    }

    @Override
    public void testResize() {
        Text text = create();
        text.resize(200, 50);
        assert text.getWidth() == 200;
        assert text.getHeight() == 50;
    }

    @Override
    public void testDescription() {
        Text text = create();
        String desc = text.getDescription(1);
        assert desc.contains("Text");
        assert desc.contains("Hello World");
    }

}
