package fr.univrennes.istic.l2gen.geometrie.app;

import fr.univrennes.istic.l2gen.geometrie.model.draw.Fractal;
import fr.univrennes.istic.l2gen.geometrie.model.export.SvgExporter;
import fr.univrennes.istic.l2gen.geometrie.model.formes.Rectangle;

public class App {

    public static void main(String[] args) throws Exception {

        SvgExporter exporter = new SvgExporter(1000, "white");
        exporter.export(new Fractal().draw(new Rectangle(250, 250, 100, 50), 4), "./output.svg");
    }
}
