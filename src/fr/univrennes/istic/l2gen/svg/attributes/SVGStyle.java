package fr.univrennes.istic.l2gen.svg.attributes;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.interfaces.SVGAttribute;

@SVGAttribute
public final class SVGStyle {
    private Optional<Double> strokeWidth = Optional.empty();
    private Optional<String> strokeColor = Optional.empty();
    private Optional<String> fillColor = Optional.empty();

    public SVGStyle() {

    }

    public SVGStyle strokeWidth(double width) {
        this.strokeWidth = Optional.of(width);
        return this;
    }

    public SVGStyle strokeColor(String color) {
        this.strokeColor = Optional.of(color);
        return this;
    }

    public SVGStyle fillColor(String color) {
        this.fillColor = Optional.of(color);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        strokeWidth.ifPresent(w -> sb.append("stroke-width:").append(w).append(";"));
        strokeColor.ifPresent(c -> sb.append("stroke:").append(c).append(";"));
        fillColor.ifPresent(c -> sb.append("fill:").append(c).append(";"));
        return sb.toString();
    }
}
