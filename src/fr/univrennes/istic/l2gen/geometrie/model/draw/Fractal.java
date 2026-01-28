package fr.univrennes.istic.l2gen.geometrie.model.draw;

import fr.univrennes.istic.l2gen.geometrie.model.Groupe;
import fr.univrennes.istic.l2gen.geometrie.model.shapes.IShape;

public final class Fractal implements IDraw {

    public Fractal() {
    }

    @Override
    public IShape draw(IShape forme) {
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public IShape draw(IShape base, int niveau) {
        if (niveau <= 0) {
            return new Groupe<>(base);
        }
        Groupe<IShape> g = new Groupe<>();

        Groupe<IShape> subG = new Groupe<>();
        subG.add(base);

        IShape topRight = base.copy();
        topRight.resize(0.5, 0.5);
        topRight.move(base.getHeight(), base.getWidth() / -4);
        subG.add(this.draw(topRight, niveau - 1));

        IShape bottomRight = base.copy();
        bottomRight.resize(0.5, 0.5);
        bottomRight.move(base.getHeight(), base.getWidth() / 4);
        subG.add(this.draw(bottomRight, niveau - 1));

        IShape topLeft = base.copy();
        topLeft.resize(0.5, 0.5);
        topLeft.move(-base.getHeight(), base.getWidth() / -4);
        subG.add(this.draw(topLeft, niveau - 1));

        IShape bottomLeft = base.copy();
        bottomLeft.resize(0.5, 0.5);
        bottomLeft.move(-base.getHeight(), base.getWidth() / 4);
        subG.add(this.draw(bottomLeft, niveau - 1));

        g.add(subG);

        return g;
    }

}
