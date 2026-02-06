package fr.univrennes.istic.l2gen.application;

import java.util.List;

import fr.univrennes.istic.l2gen.geometry.Group;
import fr.univrennes.istic.l2gen.geometry.IShape;

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
        topRight.move(base.getWidth() / 4, -base.getHeight() / 2);
        topRight.move(topRight.getWidth() / 2, 0);
        subG.add(this.draw(topRight, niveau - 1));

        IShape bottomRight = base.copy();
        bottomRight.resize(0.5, 0.5);
        bottomRight.move(base.getWidth() / 4, base.getHeight() / 4);
        bottomRight.move(bottomRight.getWidth() / 2, bottomRight.getHeight() / 2);
        subG.add(this.draw(bottomRight, niveau - 1));

        IShape topLeft = base.copy();
        topLeft.resize(0.5, 0.5);
        topLeft.move(-base.getWidth() / 2, -base.getHeight() / 2);
        subG.add(this.draw(topLeft, niveau - 1));

        IShape bottomLeft = base.copy();
        bottomLeft.resize(0.5, 0.5);
        bottomLeft.move(-base.getWidth() / 2, base.getHeight() / 4);
        bottomLeft.move(0, bottomLeft.getHeight() / 2);
        subG.add(this.draw(bottomLeft, niveau - 1));

        g.add(subG);

        return g;
    }

}
