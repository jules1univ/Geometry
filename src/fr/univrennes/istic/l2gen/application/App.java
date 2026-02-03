package fr.univrennes.istic.l2gen.application;

import fr.univrennes.istic.l2gen.geometrie.shapes.base.Rectangle;
import fr.univrennes.istic.l2gen.svg.out.SVGExporter;

public class App {

    public static void main(String[] args) throws Exception {

        SVGExporter exporter = new SVGExporter();
        exporter.export(new Fractal().draw(new Rectangle(250, 250, 100, 50), 4), "./output.svg");
    }
}
