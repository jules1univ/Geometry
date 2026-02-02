package fr.univrennes.istic.l2gen.geometrie.app;

import fr.univrennes.istic.l2gen.geometrie.infrastructure.export.svg.SVGExporter;
import fr.univrennes.istic.l2gen.geometrie.services.drawing.Fractal;
import fr.univrennes.istic.l2gen.geometrie.shapes.base.Rectangle;

public class App {

    public static void main(String[] args) throws Exception {

        SVGExporter exporter = new SVGExporter();
        exporter.export(new Fractal().draw(new Rectangle(250, 250, 100, 50), 4), "./output.svg");
    }
}
