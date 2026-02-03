package fr.univrennes.istic.l2gen.application;

import fr.univrennes.istic.l2gen.geometrie.shapes.base.Rectangle;
import fr.univrennes.istic.l2gen.svg.export.SVGExport;

public class App {

    public static void main(String[] args) throws Exception {

        SVGExport exporter = new SVGExport(new Fractal().draw(new Rectangle(250, 250, 100, 50), 4));
        exporter.export("./output.svg");
    }
}
