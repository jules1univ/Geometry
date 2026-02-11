package fr.univrennes.istic.l2gen.svg.attributes.path.commands;

/**
 * Interface pour les commandes de chemin SVG.
 * Définit le contrat pour toutes les implémentations de commandes de tracé.
 */
public interface IPathCommand {

    /**
     * Retourne la représentation en chaîne SVG de cette commande.
     * 
     * @return la commande au format SVG
     */
    String getValue();
}
