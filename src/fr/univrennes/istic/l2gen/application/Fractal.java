package fr.univrennes.istic.l2gen.application;

import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.Group;
import fr.univrennes.istic.l2gen.geometrie.IShape;

public final class Fractal {
    // TODO: definir une methode general pour les classes de dessin du style
    // IDrawer, IDraw, ect...

    public Fractal() {
    }

    public IShape draw(IShape base, int niveau) {
        if (niveau <= 0) {
            return new Group<>(List.of(base));
        }
        Group<IShape> g = new Group<>();

        Group<IShape> subG = new Group<>();
        subG.add(base);

        IShape topRight = base.copy();
        topRight.resize(0.5, 0.5);
        topRight.move(base.getHeight() * 2.5, base.getWidth() * 10 / 16);
        subG.add(this.draw(topRight, niveau - 1));

        IShape bottomRight = base.copy();
        bottomRight.resize(0.5, 0.5);
        bottomRight.move(base.getHeight() * 0.5, base.getWidth() * 10 / 16);
        subG.add(this.draw(bottomRight, niveau - 1));

        IShape topLeft = base.copy();
        topLeft.resize(0.5, 0.5);
        topLeft.move(base.getHeight() / 2, base.getWidth() / 8);
        subG.add(this.draw(topLeft, niveau - 1));

        IShape bottomLeft = base.copy();
        bottomLeft.resize(0.5, 0.5);
        bottomLeft.move(base.getHeight() * 2.5, base.getWidth() / 8);
        subG.add(this.draw(bottomLeft, niveau - 1));

        g.add(subG);

        return g;
    }

}
