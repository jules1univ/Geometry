package fr.univrennes.istic.l2gen.geometrie.model.draw;

import fr.univrennes.istic.l2gen.geometrie.model.shapes.IShape;

public interface IDraw {
    
    public IShape draw(IShape forme);

    public IShape draw(IShape forme, int level);
}
