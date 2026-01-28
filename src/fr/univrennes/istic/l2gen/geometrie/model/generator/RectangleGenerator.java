package fr.univrennes.istic.l2gen.geometrie.model.generator;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.model.shapes.Rectangle;

public class RectangleGenerator implements IGenerator<Rectangle> {

    public RectangleGenerator() {
    }

    public Rectangle generate() {
        double x = Math.random() * 500;
        double y = Math.random() * 500;
        double width = 20 + Math.random() * 100;
        double height = 20 + Math.random() * 100;
        return new Rectangle(x, y, width, height);
    }

    @Override
    public List<Rectangle> generate(int size) {
        List<Rectangle> rectangles = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            rectangles.add(generate());
        }
        return rectangles;
    }

}
