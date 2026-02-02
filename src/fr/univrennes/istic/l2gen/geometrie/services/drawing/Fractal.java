package fr.univrennes.istic.l2gen.geometrie.services.drawing;

import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.shapes.AbstractShape;
import fr.univrennes.istic.l2gen.geometrie.shapes.Group;

public final class Fractal {

    public Fractal() {
    }

    public AbstractShape draw(AbstractShape base, int niveau) {
        if (niveau <= 0) {
            return new Group<>(List.of(base));
        }
        Group<AbstractShape> g = new Group<>();

        Group<AbstractShape> subG = new Group<>();
        subG.addChild(base);

        AbstractShape topRight = base.copy();
        topRight.resize(0.5, 0.5);
        topRight.move(base.getHeight(), base.getWidth() / -4);
        subG.addChild(this.draw(topRight, niveau - 1));

        AbstractShape bottomRight = base.copy();
        bottomRight.resize(0.5, 0.5);
        bottomRight.move(base.getHeight(), base.getWidth() / 4);
        subG.addChild(this.draw(bottomRight, niveau - 1));

        AbstractShape topLeft = base.copy();
        topLeft.resize(0.5, 0.5);
        topLeft.move(-base.getHeight(), base.getWidth() / -4);
        subG.addChild(this.draw(topLeft, niveau - 1));

        AbstractShape bottomLeft = base.copy();
        bottomLeft.resize(0.5, 0.5);
        bottomLeft.move(-base.getHeight(), base.getWidth() / 4);
        subG.addChild(this.draw(bottomLeft, niveau - 1));

        g.addChild(subG);

        return g;
    }

}
