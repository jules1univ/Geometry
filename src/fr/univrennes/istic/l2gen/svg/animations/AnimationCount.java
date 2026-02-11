package fr.univrennes.istic.l2gen.svg.animations;

/**
 * Record représentant le nombre de répétitions d'une animation SVG.
 * Utilise -1 pour indiquer une répétition infinie.
 */
public record AnimationCount(int count) {
    /**
     * Constante représentant une répétition infinie.
     */
    public static final AnimationCount INDEFINITE = new AnimationCount(-1);

    /**
     * Retourne la représentation en chaîne de caractères.
     * 
     * @return "indefinite" si count == -1, sinon le nombre en chaîne
     */
    @Override
    public String toString() {
        if (count == -1) {
            return "indefinite";
        }
        return Integer.toString(count);
    }
}
