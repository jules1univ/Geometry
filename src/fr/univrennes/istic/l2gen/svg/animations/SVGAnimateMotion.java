package fr.univrennes.istic.l2gen.svg.animations;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

/**
 * Élément d'animation SVG pour animer le mouvement d'un élément le long d'un
 * chemin.
 * Support les paramétres de timing et de répétition contrôlant le mouvement.
 */
@SVGTag("animateMotion")
public final class SVGAnimateMotion implements ISVGShape {

    @SVGField
    private Optional<String> begin = Optional.empty();

    @SVGField
    private Optional<String> end = Optional.empty();

    @SVGField
    private Optional<AnimationDuration> dur = Optional.empty();

    @SVGField
    private Optional<AnimationDuration> min = Optional.empty();

    @SVGField
    private Optional<AnimationDuration> max = Optional.empty();

    @SVGField
    private Optional<AnimationRestart> restart = Optional.empty();

    @SVGField
    private Optional<AnimationCount> repeatCount = Optional.empty();

    @SVGField
    private Optional<AnimationDuration> repeatDur = Optional.empty();

    @SVGField
    private Optional<AnimationFill> fill = Optional.empty();

    /**
     * Constructeur par défaut. Crée une animation de mouvement avec tous les
     * paramétres vides.
     */
    public SVGAnimateMotion() {
    }

    /**
     * Définit quand l'animation de mouvement doit commencer.
     * 
     * @param begin le moment de début
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimateMotion begin(String begin) {
        this.begin = Optional.of(begin);
        return this;
    }

    /**
     * Retourne le moment de début de l'animation de mouvement.
     * 
     * @return le moment de début, ou null si non défini
     */
    public String begin() {
        return begin.orElse(null);
    }

    /**
     * Définit quand l'animation de mouvement doit se terminer.
     * 
     * @param end le moment de fin
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimateMotion end(String end) {
        this.end = Optional.of(end);
        return this;
    }

    /**
     * Retourne le moment de fin de l'animation de mouvement.
     * 
     * @return le moment de fin, ou null si non défini
     */
    public String end() {
        return end.orElse(null);
    }

    /**
     * Définit la durée de l'animation de mouvement.
     * 
     * @param dur la durée
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimateMotion dur(AnimationDuration dur) {
        this.dur = Optional.of(dur);
        return this;
    }

    /**
     * Retourne la durée de l'animation de mouvement.
     * 
     * @return la durée, ou null si non définie
     */
    public AnimationDuration dur() {
        return dur.orElse(null);
    }

    /**
     * Définit la durée minimale de l'animation de mouvement.
     * 
     * @param min la durée minimale
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimateMotion min(AnimationDuration min) {
        this.min = Optional.of(min);
        return this;
    }

    /**
     * Retourne la durée minimale de l'animation de mouvement.
     * 
     * @return la durée minimale, ou null si non définie
     */
    public AnimationDuration min() {
        return min.orElse(null);
    }

    /**
     * Définit la durée maximale de l'animation de mouvement.
     * 
     * @param max la durée maximale
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimateMotion max(AnimationDuration max) {
        this.max = Optional.of(max);
        return this;
    }

    /**
     * Retourne la durée maximale de l'animation de mouvement.
     * 
     * @return la durée maximale, ou null si non définie
     */
    public AnimationDuration max() {
        return max.orElse(null);
    }

    /**
     * Définit le comportement de redémarrage de l'animation de mouvement.
     * 
     * @param restart le mode de redémarrage
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimateMotion restart(AnimationRestart restart) {
        this.restart = Optional.of(restart);
        return this;
    }

    /**
     * Retourne le mode de redémarrage de l'animation de mouvement.
     * 
     * @return le mode de redémarrage, ou null si non défini
     */
    public AnimationRestart restart() {
        return restart.orElse(null);
    }

    /**
     * Définit le nombre de répétitions de l'animation de mouvement.
     * 
     * @param repeatCount le nombre de répétitions
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimateMotion repeatCount(AnimationCount repeatCount) {
        this.repeatCount = Optional.of(repeatCount);
        return this;
    }

    /**
     * Retourne le nombre de répétitions de l'animation de mouvement.
     * 
     * @return le nombre de répétitions, ou null si non défini
     */
    public AnimationCount repeatCount() {
        return repeatCount.orElse(null);
    }

    /**
     * Définit la durée totale de répétition de l'animation de mouvement.
     * 
     * @param repeatDur la durée de répétition
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimateMotion repeatDur(AnimationDuration repeatDur) {
        this.repeatDur = Optional.of(repeatDur);
        return this;
    }

    /**
     * Retourne la durée totale de répétition de l'animation de mouvement.
     * 
     * @return la durée de répétition, ou null si non définie
     */
    public AnimationDuration repeatDur() {
        return repeatDur.orElse(null);
    }

    /**
     * Définit le comportement de remplissage après l'animation de mouvement.
     * 
     * @param fill le mode de remplissage
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimateMotion fill(AnimationFill fill) {
        this.fill = Optional.of(fill);
        return this;
    }

    /**
     * Retourne le mode de remplissage après l'animation de mouvement.
     * 
     * @return le mode de remplissage, ou null si non défini
     */
    public AnimationFill fill() {
        return fill.orElse(null);
    }

    /**
     * Réinitialise tous les paramètres de l'animation de mouvement à vide.
     */
    public void reset() {
        begin = Optional.empty();
        end = Optional.empty();
        dur = Optional.empty();
        min = Optional.empty();
        max = Optional.empty();
        restart = Optional.empty();
        repeatCount = Optional.empty();
        repeatDur = Optional.empty();
        fill = Optional.empty();
    }

}
