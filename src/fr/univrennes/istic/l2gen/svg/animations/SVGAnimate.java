package fr.univrennes.istic.l2gen.svg.animations;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

@SVGTag("animate")
public class SVGAnimate {

    @SVGField
    private Optional<String> attributeName = Optional.empty();

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

    public SVGAnimate() {
    }

    public SVGAnimate attributeName(String attributeName) {
        this.attributeName = Optional.of(attributeName);
        return this;
    }

    public SVGAnimate from(String from) {
        this.from = Optional.of(from);
        return this;
    }

    public SVGAnimate to(String to) {
        this.to = Optional.of(to);
        return this;
    }

    public SVGAnimate dur(String dur) {
        this.dur = Optional.of(dur);
        return this;
    }

    public SVGAnimate repeatCount(String repeatCount) {
        this.repeatCount = Optional.of(repeatCount);
        return this;
    }

    public void reset() {
        attributeName = Optional.empty();
        from = Optional.empty();
        to = Optional.empty();
        dur = Optional.empty();
        repeatCount = Optional.empty();
    }

}
