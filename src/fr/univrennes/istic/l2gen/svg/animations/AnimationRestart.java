package fr.univrennes.istic.l2gen.svg.animations;

/**
 * Énumération des modes de redémarrage d'une animation SVG.
 * Définit quand l'animation peut recommencer.
 */
public enum AnimationRestart {
    /**
     * L'animation peut redémarrer n'importe quand.
     */
    ALWAYS,
    /**
     * L'animation ne peut redémarrer que si elle n'est pas active.
     */
    WHEN_NOT_ACTIVE,
    /**
     * L'animation ne peut pas redémarrer.
     */
    NEVER;

    /**
     * Retourne la représentation en chaîne en minuscules pour SVG.
     * 
     * @return le nom en minuscules
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
