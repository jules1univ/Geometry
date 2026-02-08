package fr.univrennes.istic.l2gen.svg.animations;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("animateTransform")
public final class SVGAnimateTransform extends SVGAnimate {

    public SVGAnimateTransform() {
        this.attributeName = Optional.of("transform");
    }

}
