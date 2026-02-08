package fr.univrennes.istic.l2gen.svg.animations;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

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

    public SVGAnimate() {
    }

    public SVGAnimate type(AnimationTransformType type) {
        this.type = Optional.of(type);
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

    public SVGAnimate by(String by) {
        this.by = Optional.of(by);
        return this;
    }

    public SVGAnimate begin(String begin) {
        this.begin = Optional.of(begin);
        return this;
    }

    public SVGAnimate end(String end) {
        this.end = Optional.of(end);
        return this;
    }

    public SVGAnimate dur(AnimationDuration dur) {
        this.dur = Optional.of(dur);
        return this;
    }

    public SVGAnimate min(AnimationDuration min) {
        this.min = Optional.of(min);
        return this;
    }

    public SVGAnimate max(AnimationDuration max) {
        this.max = Optional.of(max);
        return this;
    }

    public SVGAnimate restart(AnimationRestart restart) {
        this.restart = Optional.of(restart);
        return this;
    }

    public SVGAnimate repeatCount(AnimationCount repeatCount) {
        this.repeatCount = Optional.of(repeatCount);
        return this;
    }

    public SVGAnimate repeatDur(AnimationDuration repeatDur) {
        this.repeatDur = Optional.of(repeatDur);
        return this;
    }

    public SVGAnimate fill(AnimationFill fill) {
        this.fill = Optional.of(fill);
        return this;
    }

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
