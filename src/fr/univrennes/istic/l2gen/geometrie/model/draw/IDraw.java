package fr.univrennes.istic.l2gen.geometrie.model.draw;

import fr.univrennes.istic.l2gen.geometrie.model.formes.IForme;

public interface IDraw {
    
    public IForme draw(IForme forme);

    public IForme draw(IForme forme, int level);
}
