package fr.univrennes.istic.l2gen.svg.animations;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("animateMotion")
public class SVGAnimateMotion extends SVGAnimate {

    @SVGField
    private Optional<String> path = Optional.empty();

    @SVGField
    private Optional<String> begin = Optional.empty();

    @SVGField
    private Optional<String> dur = Optional.empty();

    @SVGField
    private Optional<String> repeatCount = Optional.empty();

    public SVGAnimateMotion() {
    }
}
