package fr.univrennes.istic.l2gen.geometrie.app;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.model.draw.Fractal;
import fr.univrennes.istic.l2gen.geometrie.model.export.SvgExporter;
import fr.univrennes.istic.l2gen.geometrie.model.formes.Rectangle;

public class App {

    public static List<Rectangle> genererRectangles(int n) {
        List<Rectangle> rectangles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double x = Math.random() * 500;
            double y = Math.random() * 500;
            double width = 20 + Math.random() * 100;
            double height = 20 + Math.random() * 100;
            rectangles.add(new Rectangle(x, y, width, height));
        }
        return rectangles;
    }


    public static void main(String[] args) throws Exception {

        SvgExporter exporter = new SvgExporter(1000, "white");
        exporter.export(new Fractal().draw(new Rectangle(250, 250, 100, 50), 4), "./output.svg");
    }
}
