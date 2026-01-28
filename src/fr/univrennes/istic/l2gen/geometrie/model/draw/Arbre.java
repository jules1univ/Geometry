package fr.univrennes.istic.l2gen.geometrie.model.draw;

import fr.univrennes.istic.l2gen.geometrie.model.Groupe;
import fr.univrennes.istic.l2gen.geometrie.model.shapes.IShape;

public final class Arbre implements IDraw {

    public Arbre() {
    }

    @Override
    public IShape draw(IShape shape) {
        Groupe<IShape> g = new Groupe<>();
        g.add(shape);

        IShape mini = shape.copy();
        mini.resize(0.5, 0.5);
        g.add(mini);

        IShape miniG = g.copy();
        miniG.resize(0.25, 0.25);
        g.add(miniG);

        return g;
    }

    @Override
    public IShape draw(IShape forme, int level) {
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

}
