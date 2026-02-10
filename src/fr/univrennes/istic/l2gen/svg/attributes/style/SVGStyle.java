package fr.univrennes.istic.l2gen.svg.attributes.style;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.color.Color;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGAttribute;

public final class SVGStyle implements ISVGAttribute {
    private Optional<Double> strokeWidth = Optional.empty();
    private Optional<Color> strokeColor = Optional.empty();
    private Optional<Color> fillColor = Optional.empty();

    public SVGStyle() {

    }

    public SVGStyle(String raw) {
        String[] declarations = raw.split(";");
        for (String declaration : declarations) {
            if (declaration.startsWith("stroke-width:")) {
                try {
                    double width = Double.parseDouble(declaration.substring("stroke-width:".length()));
                    this.strokeWidth = Optional.of(width);
                } catch (NumberFormatException e) {
                }
            } else if (declaration.startsWith("stroke:")) {
                Color color = Color.raw(declaration.substring("stroke:".length()));
                if (color != null) {
                    this.strokeColor = Optional.of(color);
                }
            } else if (declaration.startsWith("fill:")) {
                Color color = Color.raw(declaration.substring("fill:".length()));
                if (color != null) {
                    this.fillColor = Optional.of(color);
                }
            }
        }
    }

    public SVGStyle strokeWidth(double width) {
        this.strokeWidth = Optional.of(width);
        return this;
    }

    public Optional<Double> strokeWidth() {
        return strokeWidth;
    }

    public SVGStyle strokeColor(Color color) {
        this.strokeColor = Optional.of(color);
        return this;
    }

    public Optional<Color> strokeColor() {
        return strokeColor;
    }

    public SVGStyle fillColor(Color color) {
        this.fillColor = Optional.of(color);
        return this;
    }

    public Optional<Color> fillColor() {
        return fillColor;
    }

    @Override
    public boolean hasContent() {
        return strokeWidth.isPresent() || strokeColor.isPresent() || fillColor.isPresent();
    }

    @Override
    public String getContent() {
        StringBuilder sb = new StringBuilder();
        strokeWidth.ifPresent(w -> sb.append("stroke-width:").append(w).append(";"));
        strokeColor.ifPresent(c -> sb.append("stroke:").append(c).append(";"));
        fillColor.ifPresent(c -> sb.append("fill:").append(c).append(";"));
        return sb.toString();
    }
}
