package fr.univrennes.istic.l2gen.svg.animations;

/**
 * Record représentant la durée d'une animation SVG en millisecondes.
 * Utilise -1 pour indiquer une durée infinie.
 */
public record AnimationDuration(long miliseconds) {
    /**
     * Constante représentant une durée infinie.
     */
    public static final AnimationDuration INDEFINITE = new AnimationDuration(-1);

    /**
     * Crée une durée d'animation à partir de secondes.
     * 
     * @param seconds la durée en secondes
     * @return une new AnimationDuration
     */
    public static AnimationDuration s(double seconds) {
        return new AnimationDuration((long) (seconds * 1000));
    }

    /**
     * Crée une durée d'animation à partir de millisecondes.
     * 
     * @param miliseconds la durée en millisecondes
     * @return une new AnimationDuration
     */
    public static AnimationDuration ms(long miliseconds) {
        return new AnimationDuration(miliseconds);
    }

    /**
     * Crée une durée d'animation à partir de minutes.
     * 
     * @param minutes la durée en minutes
     * @return une new AnimationDuration
     */
    public static AnimationDuration min(double minutes) {
        return new AnimationDuration((long) (minutes * 60 * 1000));
    }

    /**
     * Crée une durée infinie.
     * 
     * @return AnimationDuration.INDEFINITE
     */
    public static AnimationDuration infinite() {
        return INDEFINITE;
    }

    /**
     * Retourne la représentation en chaîne de caractères.
     * 
     * @return "indefinite" si miliseconds == -1, sinon la valeur avec "ms"
     */
    @Override
    public String toString() {
        if (miliseconds == -1) {
            return "indefinite";
        }
        return miliseconds + "ms";
    }

}
