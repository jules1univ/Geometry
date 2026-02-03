package fr.univrennes.istic.l2gen.svg.out;

import fr.univrennes.istic.l2gen.geometrie.shapes.IShape;

public abstract class Exporter {
    // TODO: definir une methode generique d'export
    // pour avoir une interface commune a tous les exporteurs
    // et ensuite specifier des tailles, des couleurs de font, etc...

    public abstract boolean export(IShape forme, String filename);
}
