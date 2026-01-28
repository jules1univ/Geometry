package fr.univrennes.istic.l2gen.geometrie.app;

import fr.univrennes.istic.l2gen.geometrie.model.Groupe;
import fr.univrennes.istic.l2gen.geometrie.model.export.SvgExporter;
import fr.univrennes.istic.l2gen.geometrie.model.formes.Cercle;
import fr.univrennes.istic.l2gen.geometrie.model.formes.IForme;
import fr.univrennes.istic.l2gen.geometrie.model.formes.Rectangle;
import java.util.ArrayList;
import java.util.List;

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

    public static Groupe<IForme> arbre(IForme shape) {
        Groupe<IForme> g = new Groupe<>();
        g.ajouter(shape);

        IForme mini = shape.dupliquer();
        mini.redimensionner(0.5, 0.5);
        g.ajouter(mini);

        IForme miniG = g.dupliquer();
        miniG.redimensionner(0.25, 0.25);
        g.ajouter(miniG);

        return g;
    }

    public static Groupe<IForme> fractal(IForme base, int niveau) {
        if (niveau <= 0) {
            return new Groupe<>(base);
        }
        Groupe<IForme> g = new Groupe<>();

        Groupe<IForme> subG = new Groupe<>();
        subG.ajouter(base);

        IForme topRight = base.dupliquer();
        topRight.redimensionner(0.5, 0.5);
        topRight.deplacer(base.hauteur(), base.largeur() / -4);
        subG.ajouter(fractal(topRight, niveau - 1));

        IForme bottomRight = base.dupliquer();
        bottomRight.redimensionner(0.5, 0.5);
        bottomRight.deplacer(base.hauteur(), base.largeur() / 4);
        subG.ajouter(fractal(bottomRight, niveau - 1));

        IForme topLeft = base.dupliquer();
        topLeft.redimensionner(0.5, 0.5);
        topLeft.deplacer(-base.hauteur(), base.largeur() / -4);
        subG.ajouter(fractal(topLeft, niveau - 1));

        IForme bottomLeft = base.dupliquer();
        bottomLeft.redimensionner(0.5, 0.5);
        bottomLeft.deplacer(-base.hauteur(), base.largeur() / 4);
        subG.ajouter(fractal(bottomLeft, niveau - 1));

        g.ajouter(subG);

        return g;
    }

    public static void main(String[] args) throws Exception {

        SvgExporter exporter = new SvgExporter(1000, "white");
        exporter.export(fractal(new Cercle(256, 256, 128), 4), "./output.svg");
    }
}
