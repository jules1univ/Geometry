package fr.univrennes.istic.l2gen.svg.animations;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.interfaces.tag.SVGTag;

/**
 * Élément d'animation SVG pour animer les transformations (rotation, échelle,
 * translation)
 * d'un élément.
 * Hérite de SVGAnimate et spécialise l'attribut à "transform".
 */
@SVGTag("animateTransform")
public final class SVGAnimateTransform extends SVGAnimate {

    /**
     * Constructeur par défaut.
     * Définit automatiquement l'attribut animé à "transform".
     */
    public SVGAnimateTransform() {
        this.attributeName = Optional.of("transform");
    }

}
