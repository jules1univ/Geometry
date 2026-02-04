package fr.univrennes.istic.l2gen.svg.animations;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;

public class SVGAnimateTransform extends SVGAnimate {

    @SVGField
    private final String attributeName = "transform";

    @SVGField
    private Optional<String> begin = Optional.empty();

    @SVGField
    private Optional<String> from = Optional.empty();

    @SVGField
    private Optional<String> to = Optional.empty();

    @SVGField
    private Optional<String> dur = Optional.empty();

    @SVGField
    private Optional<String> repeatCount = Optional.empty();

    @SVGField
    private Optional<String> fill = Optional.empty();

    public SVGAnimateTransform() {
    }
}
