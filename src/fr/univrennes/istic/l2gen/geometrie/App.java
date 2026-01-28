package fr.univrennes.istic.l2gen.geometrie;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.formes.IForme;
import fr.univrennes.istic.l2gen.geometrie.formes.Rectangle;

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

        String svg = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"500\" height=\"500\">\n";
        svg += "<rect width=\"100%\" height=\"100%\" fill=\"white\"/>\n";
        svg += fractal(new Rectangle(250, 250, 100, 50), 4).enSVG();
        svg += "\n</svg>";

        FileWriter fw = new FileWriter("./output.svg");
        ;
        fw.write(svg);
        fw.close();
    }
}
