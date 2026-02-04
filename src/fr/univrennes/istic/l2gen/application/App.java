package fr.univrennes.istic.l2gen.application;

import fr.univrennes.istic.l2gen.geometrie.Group;
import fr.univrennes.istic.l2gen.geometrie.IShape;
import fr.univrennes.istic.l2gen.geometrie.Point;
import fr.univrennes.istic.l2gen.geometrie.base.Circle;
import fr.univrennes.istic.l2gen.geometrie.base.Line;
import fr.univrennes.istic.l2gen.geometrie.base.Polygon;
import fr.univrennes.istic.l2gen.geometrie.base.Rectangle;
import fr.univrennes.istic.l2gen.geometrie.base.Text;
import fr.univrennes.istic.l2gen.geometrie.base.Triangle;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.io.SVGExport;
import fr.univrennes.istic.l2gen.svg.io.SVGImport;

public class App {

    static {
        SVGImport.register(Point.class);
        SVGImport.register(Group.class);

        SVGImport.register(Circle.class);
        SVGImport.register(Line.class);
        SVGImport.register(Polygon.class);
        SVGImport.register(Rectangle.class);
        SVGImport.register(Text.class);
        SVGImport.register(Triangle.class);
    }

    public static void main(String[] args) throws Exception {
        // IMPORTANT: ne pas mettre a jour le code present ici
        // il faut séparer les fonctionnalité dans d'autre fichier de l'app puis les
        // charger ici

        IShape fractal = new Fractal().draw(new Rectangle(500, 500, 100, 50), 5);
        SVGExport.export(fractal, "output.svg");

        ISVGShape svgShape = SVGImport.load("output.svg");
        if (svgShape instanceof IShape shape) {

            // TODO: faire un system de description automatique comme pour les SVG
            System.out.println(shape.getDescription(0));
        }
    }
}
