package fr.univrennes.istic.l2gen.svg.animations;

/**
 * Énumération des types de transformation pour les animations SVG.
 * Détermine quel type de transformation est animée.
 */
public enum AnimationTransformType {
    /**
     * Animation de translation (déplacement).
     */
    TRANSLATE,
    /**
     * Animation d'échelle (redimensionnement).
     */
    SCALE,
    /**
     * Animation de rotation.
     */
    ROTATE,
    /**
     * Animation d'inclinaison selon l'axe X.
     */
    SKEWX,
    /**
     * Animation d'inclinaison selon l'axe Y.
     */
    SKEWY;

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
