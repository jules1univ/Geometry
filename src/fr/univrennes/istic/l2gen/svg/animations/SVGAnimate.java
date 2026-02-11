package fr.univrennes.istic.l2gen.svg.animations;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

/**
 * Élément d'animation SVG pour animer les attributs d'un élément.
 * Support les animations de valeur, de durée, de répétition et de remplissage.
 * Implement ISVGShape pour l'export dans les structures XML.
 */
@SVGTag("animate")
public class SVGAnimate implements ISVGShape {

    @SVGField
    protected Optional<String> attributeName = Optional.empty();

    @SVGField
    private Optional<AnimationTransformType> type = Optional.empty();

    @SVGField
    private Optional<String> from = Optional.empty();

    @SVGField
    private Optional<String> to = Optional.empty();

    @SVGField
    private Optional<String> by = Optional.empty();

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
     * Constructeur par défaut. Crée une animation avec tous les paramétres vides.
     */
    public SVGAnimate() {
    }

    /**
     * Définit le type de transformation pour cette animation.
     * 
     * @param type le type de transformation
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate type(AnimationTransformType type) {
        this.type = Optional.of(type);
        return this;
    }

    /**
     * Retourne le type de transformation de cette animation.
     * 
     * @return le type de transformation, ou null si non défini
     */
    public AnimationTransformType type() {
        return type.orElse(null);
    }

    /**
     * Définit la valeur initiale pour l'animation.
     * 
     * @param from la valeur initiale
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate from(String from) {
        this.from = Optional.of(from);
        return this;
    }

    /**
     * Retourne la valeur initiale de l'animation.
     * 
     * @return la valeur initiale, ou null si non définie
     */
    public String from() {
        return from.orElse(null);
    }

    /**
     * Définit la valeur finale pour l'animation.
     * 
     * @param to la valeur finale
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate to(String to) {
        this.to = Optional.of(to);
        return this;
    }

    /**
     * Retourne la valeur finale de l'animation.
     * 
     * @return la valeur finale, ou null si non définie
     */
    public String to() {
        return to.orElse(null);
    }

    /**
     * Définit le changement de valeur pour l'animation (delta).
     * 
     * @param by le changement de valeur
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate by(String by) {
        this.by = Optional.of(by);
        return this;
    }

    /**
     * Retourne le changement de valeur de l'animation.
     * 
     * @return le changement, ou null si non défini
     */
    public String by() {
        return by.orElse(null);
    }

    /**
     * Définit quand l'animation doit commencer.
     * 
     * @param begin le moment de début
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate begin(String begin) {
        this.begin = Optional.of(begin);
        return this;
    }

    /**
     * Retourne le moment de début de l'animation.
     * 
     * @return le moment de début, ou null si non défini
     */
    public String begin() {
        return begin.orElse(null);
    }

    /**
     * Définit quand l'animation doit se terminer.
     * 
     * @param end le moment de fin
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate end(String end) {
        this.end = Optional.of(end);
        return this;
    }

    /**
     * Retourne le moment de fin de l'animation.
     * 
     * @return le moment de fin, ou null si non défini
     */
    public String end() {
        return end.orElse(null);
    }

    /**
     * Définit la durée de l'animation.
     * 
     * @param dur la durée
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate dur(AnimationDuration dur) {
        this.dur = Optional.of(dur);
        return this;
    }

    /**
     * Retourne la durée de l'animation.
     * 
     * @return la durée, ou null si non définie
     */
    public AnimationDuration dur() {
        return dur.orElse(null);
    }

    /**
     * Définit la durée minimale de l'animation.
     * 
     * @param min la durée minimale
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate min(AnimationDuration min) {
        this.min = Optional.of(min);
        return this;
    }

    /**
     * Retourne la durée minimale de l'animation.
     * 
     * @return la durée minimale, ou null si non définie
     */
    public AnimationDuration min() {
        return min.orElse(null);
    }

    /**
     * Définit la durée maximale de l'animation.
     * 
     * @param max la durée maximale
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate max(AnimationDuration max) {
        this.max = Optional.of(max);
        return this;
    }

    /**
     * Retourne la durée maximale de l'animation.
     * 
     * @return la durée maximale, ou null si non définie
     */
    public AnimationDuration max() {
        return max.orElse(null);
    }

    /**
     * Définit le comportement de redémarrage de l'animation.
     * 
     * @param restart le mode de redémarrage
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate restart(AnimationRestart restart) {
        this.restart = Optional.of(restart);
        return this;
    }

    /**
     * Retourne le mode de redémarrage de l'animation.
     * 
     * @return le mode de redémarrage, ou null si non défini
     */
    public AnimationRestart restart() {
        return restart.orElse(null);
    }

    /**
     * Définit le nombre de répétitions de l'animation.
     * 
     * @param repeatCount le nombre de répétitions
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate repeatCount(AnimationCount repeatCount) {
        this.repeatCount = Optional.of(repeatCount);
        return this;
    }

    /**
     * Retourne le nombre de répétitions de l'animation.
     * 
     * @return le nombre de répétitions, ou null si non défini
     */
    public AnimationCount repeatCount() {
        return repeatCount.orElse(null);
    }

    /**
     * Définit la durée totale de répétition de l'animation.
     * 
     * @param repeatDur la durée de répétition
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate repeatDur(AnimationDuration repeatDur) {
        this.repeatDur = Optional.of(repeatDur);
        return this;
    }

    /**
     * Retourne la durée totale de répétition de l'animation.
     * 
     * @return la durée de répétition, ou null si non définie
     */
    public AnimationDuration repeatDur() {
        return repeatDur.orElse(null);
    }

    /**
     * Définit le comportement de remplissage après l'animation.
     * 
     * @param fill le mode de remplissage
     * @return cette animation (pour le chaîinage)
     */
    public SVGAnimate fill(AnimationFill fill) {
        this.fill = Optional.of(fill);
        return this;
    }

    /**
     * Retourne le mode de remplissage après l'animation.
     * 
     * @return le mode de remplissage, ou null si non défini
     */
    public AnimationFill fill() {
        return fill.orElse(null);
    }

    /**
     * Réinitialise tous les paramètres de l'animation à vide.
     */
    public void reset() {
        type = Optional.empty();
        from = Optional.empty();
        to = Optional.empty();
        by = Optional.empty();
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
