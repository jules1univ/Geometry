package fr.univrennes.istic.l2gen.geometrie.model.draw;

import fr.univrennes.istic.l2gen.geometrie.model.formes.IShape;

public interface IDraw {
    
    public IShape draw(IShape forme);

    public IShape draw(IShape forme, int level);
}
