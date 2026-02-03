package fr.univrennes.istic.l2gen.application;

import fr.univrennes.istic.l2gen.geometrie.IShape;
import fr.univrennes.istic.l2gen.geometrie.base.Rectangle;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.io.SVGExport;
import fr.univrennes.istic.l2gen.svg.io.SVGImport;

public class App {

    public static void main(String[] args) throws Exception {
        // IMPORTANT: ne pas mettre a jour le code present ici
        // il faut séparer les fonctionnalité dans d'autre fichier de l'app puis les
        // charger ici

        IShape fractal = new Fractal().draw(new Rectangle(500, 500, 100, 50), 5);
        SVGExport.export(fractal, "output.svg");

        // FIXME: import svg ne fonctionne pas correctement pour le moment
        ISVGShape svgShape = SVGImport.load("output.svg");
        if (svgShape instanceof IShape shape) {

            // TODO: faire un system de description automatique comme pour les SVG
            System.out.println(shape.getDescription(0));
        }
    }
}
