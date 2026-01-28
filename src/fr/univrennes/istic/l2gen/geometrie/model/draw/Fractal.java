package fr.univrennes.istic.l2gen.geometrie.model.draw;

import fr.univrennes.istic.l2gen.geometrie.model.Groupe;
import fr.univrennes.istic.l2gen.geometrie.model.formes.IForme;

public class Fractal implements IDraw {

    public Fractal() {
    }

    @Override
    public IForme draw(IForme forme) {
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public IForme draw(IForme base, int niveau) {
        if (niveau <= 0) {
            return new Groupe<>(base);
        }
        Groupe<IForme> g = new Groupe<>();

        Groupe<IForme> subG = new Groupe<>();
        subG.ajouter(base);

        IForme topRight = base.dupliquer();
        topRight.redimensionner(0.5, 0.5);
        topRight.deplacer(base.hauteur(), base.largeur() / -4);
        subG.ajouter(this.draw(topRight, niveau - 1));

        IForme bottomRight = base.dupliquer();
        bottomRight.redimensionner(0.5, 0.5);
        bottomRight.deplacer(base.hauteur(), base.largeur() / 4);
        subG.ajouter(this.draw(bottomRight, niveau - 1));

        IForme topLeft = base.dupliquer();
        topLeft.redimensionner(0.5, 0.5);
        topLeft.deplacer(-base.hauteur(), base.largeur() / -4);
        subG.ajouter(this.draw(topLeft, niveau - 1));

        IForme bottomLeft = base.dupliquer();
        bottomLeft.redimensionner(0.5, 0.5);
        bottomLeft.deplacer(-base.hauteur(), base.largeur() / 4);
        subG.ajouter(this.draw(bottomLeft, niveau - 1));

        g.ajouter(subG);

        return g;
    }

}
