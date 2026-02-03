package fr.univrennes.istic.l2gen.application;

import fr.univrennes.istic.l2gen.geometrie.shapes.Group;
import fr.univrennes.istic.l2gen.geometrie.shapes.IShape;
import fr.univrennes.istic.l2gen.geometrie.shapes.base.Rectangle;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.io.SVGExport;
import fr.univrennes.istic.l2gen.svg.io.SVGImport;

public class App {

    public static void main(String[] args) throws Exception {

        IShape fractal = new Fractal().draw(new Rectangle(250, 250, 100, 50), 4);
        SVGExport.export(fractal, "output.svg");

        ISVGShape svgShape = SVGImport.load("output.svg");
        if (svgShape instanceof Group<?> groupShape) {
            System.out.println(groupShape.getDescription(0));
        }
    }
}
