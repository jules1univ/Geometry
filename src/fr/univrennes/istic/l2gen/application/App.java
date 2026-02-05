package fr.univrennes.istic.l2gen.application;

import fr.univrennes.istic.l2gen.geometry.Group;
import fr.univrennes.istic.l2gen.geometry.IShape;
import fr.univrennes.istic.l2gen.geometry.Path;
import fr.univrennes.istic.l2gen.geometry.Point;
import fr.univrennes.istic.l2gen.geometry.base.Circle;
import fr.univrennes.istic.l2gen.geometry.base.Ellipse;
import fr.univrennes.istic.l2gen.geometry.base.PolyLine;
import fr.univrennes.istic.l2gen.geometry.base.Polygon;
import fr.univrennes.istic.l2gen.geometry.base.Rectangle;
import fr.univrennes.istic.l2gen.geometry.base.Text;
import fr.univrennes.istic.l2gen.geometry.base.Triangle;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.io.SVGExport;
import fr.univrennes.istic.l2gen.svg.io.SVGImport;

public class App {

    static {
        SVGImport.register(Point.class);
        SVGImport.register(Group.class);
        SVGImport.register(Path.class);

        SVGImport.register(Circle.class);
        SVGImport.register(Ellipse.class);
        SVGImport.register(PolyLine.class);
        SVGImport.register(Polygon.class);
        SVGImport.register(Rectangle.class);
        SVGImport.register(Text.class);
        SVGImport.register(Triangle.class);
    }

    public static void main(String[] args) throws Exception {
        // IMPORTANT: ne pas mettre a jour le code present ici
        // il faut séparer les fonctionnalité dans d'autre fichier de l'app puis les
        // charger ici

        IShape fractal = new Fractal().draw(new Rectangle(500, 500, 100, 50), 4);
        SVGExport.export(fractal, "output.svg");

        ISVGShape svgShape = SVGImport.load("output.svg");
        if (svgShape instanceof IShape shape) {
            System.out.println(shape.getDescription(0));
        }
    }
}
