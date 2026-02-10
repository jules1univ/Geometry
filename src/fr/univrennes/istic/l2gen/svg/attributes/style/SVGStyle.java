package fr.univrennes.istic.l2gen.svg.attributes.style;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.color.Color;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGAttribute;

public final class SVGStyle implements ISVGAttribute {
    private Optional<Double> strokeWidth = Optional.empty();
    private Optional<Color> strokeColor = Optional.empty();
    private Optional<Color> fillColor = Optional.empty();

    private Optional<Double> fontSize = Optional.empty();
    private Optional<String> fontFamily = Optional.empty();

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
            } else if (declaration.startsWith("font-size:")) {
                try {
                    double size = Double.parseDouble(declaration.substring("font-size:".length()));
                    this.fontSize = Optional.of(size);
                } catch (NumberFormatException e) {
                }
            } else if (declaration.startsWith("font-family:")) {
                String family = declaration.substring("font-family:".length());
                this.fontFamily = Optional.of(family);
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

    public SVGStyle fontSize(double size) {
        this.fontSize = Optional.of(size);
        return this;
    }

    public Optional<Double> fontSize() {
        return fontSize;
    }

    public SVGStyle fontFamily(String family) {
        this.fontFamily = Optional.of(family);
        return this;
    }

    public Optional<String> fontFamily() {
        return fontFamily;
    }

    @Override
    public boolean hasContent() {
        return strokeWidth.isPresent() || strokeColor.isPresent() || fillColor.isPresent() || fontSize.isPresent()
                || fontFamily.isPresent();
    }

    @Override
    public String getContent() {
        StringBuilder sb = new StringBuilder();
        strokeWidth.ifPresent(w -> sb.append("stroke-width:").append(w).append(";"));
        strokeColor.ifPresent(c -> sb.append("stroke:").append(c).append(";"));
        fillColor.ifPresent(c -> sb.append("fill:").append(c).append(";"));
        fontSize.ifPresent(s -> sb.append("font-size:").append(s).append(";"));
        fontFamily.ifPresent(f -> sb.append("font-family:").append(f).append(";"));
        return sb.toString();
    }
}
