package fr.univrennes.istic.l2gen.svg.animations;

/**
 * Énumération des modes de remplissage après une animation SVG.
 * Définit le comportement de l'élément après la fin de l'animation.
 */
public enum AnimationFill {
    /**
     * Enlève l'effet de l'animation (reviens à l'état initial).
     */
    REMOVE,
    /**
     * Conserve l'effet de l'animation à la fin.
     */
    FREEZE,
    /**
     * Mode automatique (par défaut).
     */
    AUTO;

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
