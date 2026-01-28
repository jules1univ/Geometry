package fr.univrennes.istic.l2gen.geometrie.model.generator;

import java.util.List;

import fr.univrennes.istic.l2gen.geometrie.model.formes.IShape;

public interface IGenerator<T extends IShape> {
    public List<T> generate(int size);
}
