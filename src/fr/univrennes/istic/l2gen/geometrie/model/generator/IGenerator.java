package fr.univrennes.istic.l2gen.geometrie.model.generator;

import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.model.formes.IForme;

public interface IGenerator<T extends IForme> {
    public List<T> generate(int size);
}
