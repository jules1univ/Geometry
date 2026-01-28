package fr.univrennes.istic.l2gen.geometrie.model.draw;

import fr.univrennes.istic.l2gen.geometrie.model.Groupe;
import fr.univrennes.istic.l2gen.geometrie.model.formes.IForme;

public class Arbre implements IDraw {

    public Arbre() {
    }

    @Override
    public IForme draw(IForme shape) {
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

    @Override
    public IForme draw(IForme forme, int level) {
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

}
