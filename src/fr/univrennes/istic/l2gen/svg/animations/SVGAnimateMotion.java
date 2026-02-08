package fr.univrennes.istic.l2gen.svg.animations;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.interfaces.ISVGShape;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGField;
import fr.univrennes.istic.l2gen.svg.interfaces.SVGTag;

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

    public SVGAnimateMotion() {
    }

    public SVGAnimateMotion begin(String begin) {
        this.begin = Optional.of(begin);
        return this;
    }

    public SVGAnimateMotion end(String end) {
        this.end = Optional.of(end);
        return this;
    }

    public SVGAnimateMotion dur(AnimationDuration dur) {
        this.dur = Optional.of(dur);
        return this;
    }

    public SVGAnimateMotion min(AnimationDuration min) {
        this.min = Optional.of(min);
        return this;
    }

    public SVGAnimateMotion max(AnimationDuration max) {
        this.max = Optional.of(max);
        return this;
    }

    public SVGAnimateMotion restart(AnimationRestart restart) {
        this.restart = Optional.of(restart);
        return this;
    }

    public SVGAnimateMotion repeatCount(AnimationCount repeatCount) {
        this.repeatCount = Optional.of(repeatCount);
        return this;
    }

    public SVGAnimateMotion repeatDur(AnimationDuration repeatDur) {
        this.repeatDur = Optional.of(repeatDur);
        return this;
    }

    public SVGAnimateMotion fill(AnimationFill fill) {
        this.fill = Optional.of(fill);
        return this;
    }

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
