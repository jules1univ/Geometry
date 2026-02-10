package fr.univrennes.istic.l2gen.svg.attributes.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.univrennes.istic.l2gen.svg.interfaces.ISVGAttribute;

public final class SVGTransform implements ISVGAttribute {
    private Optional<Double> rotate = Optional.empty();
    private Optional<Double> rotatePointX = Optional.empty();
    private Optional<Double> rotatePointY = Optional.empty();

    private Optional<Double> translateX = Optional.empty();
    private Optional<Double> translateY = Optional.empty();

    private Optional<Double> scaleX = Optional.empty();
    private Optional<Double> scaleY = Optional.empty();

    private Optional<Double> skewX = Optional.empty();
    private Optional<Double> skewY = Optional.empty();

    private Optional<Double[]> matrix = Optional.empty();

    public SVGTransform() {
    }

    public SVGTransform(String raw) {
        Pattern fnPattern = Pattern.compile(
                "(matrix|translate|scale|rotate|skewX|skewY)" +
                        "\\s*\\(([^)]*)\\)",
                Pattern.CASE_INSENSITIVE);

        Pattern numPattern = Pattern.compile(
                "[+-]?(?:\\d+\\.?\\d*|\\.\\d+)(?:[eE][+-]?\\d+)?");

        Matcher fnMatcher = fnPattern.matcher(raw);
        while (fnMatcher.find()) {
            String name = fnMatcher.group(1).toLowerCase();
            String args = fnMatcher.group(2);

            List<Double> params = new ArrayList<>();
            Matcher numMatcher = numPattern.matcher(args);
            while (numMatcher.find()) {
                params.add(Double.parseDouble(numMatcher.group()));
            }

            switch (name) {

                case "matrix" -> {
                    if (params.size() >= 6) {
                        this.matrix = Optional.of(new Double[] {
                                params.get(0), params.get(1), params.get(2),
                                params.get(3), params.get(4), params.get(5)
                        });
                    }
                }

                case "translate" -> {
                    if (params.size() >= 1) {
                        this.translateX = Optional.of(params.get(0));
                        this.translateY = Optional.of(params.size() >= 2 ? params.get(1) : 0.0);
                    }
                }

                case "scale" -> {
                    if (params.size() >= 1) {
                        this.scaleX = Optional.of(params.get(0));
                        this.scaleY = Optional.of(params.size() >= 2 ? params.get(1) : params.get(0));
                    }
                }

                case "rotate" -> {
                    if (params.size() >= 1) {
                        this.rotate = Optional.of(params.get(0));
                        if (params.size() >= 3) {
                            this.rotatePointX = Optional.of(params.get(1));
                            this.rotatePointY = Optional.of(params.get(2));
                        } else {
                            this.rotatePointX = Optional.empty();
                            this.rotatePointY = Optional.empty();
                        }
                    }
                }

                case "skewx" -> {
                    if (params.size() >= 1) {
                        this.skewX = Optional.of(params.get(0));
                    }
                }

                case "skewy" -> {
                    if (params.size() >= 1) {
                        this.skewY = Optional.of(params.get(0));
                    }
                }
            }
        }
    }

    public SVGTransform rotate(double angle) {
        this.rotate = Optional.of(angle);
        return this;
    }

    public SVGTransform rotate(double angle, double x, double y) {
        this.rotate = Optional.of(angle);
        this.rotatePointX = Optional.of(x);
        this.rotatePointY = Optional.of(y);
        return this;
    }

    public SVGTransform translate(double x, double y) {
        this.translateX = Optional.of(x);
        this.translateY = Optional.of(y);
        return this;
    }

    public SVGTransform scale(double x, double y) {
        this.scaleX = Optional.of(x);
        this.scaleY = Optional.of(y);
        return this;
    }

    public SVGTransform skew(double x, double y) {
        this.skewX = Optional.of(x);
        this.skewY = Optional.of(y);
        return this;
    }

    public SVGTransform matrix(double a, double b, double c, double d, double e, double f) {
        this.matrix = Optional.of(new Double[] { a, b, c, d, e, f });
        return this;
    }

    public SVGTransform reset() {
        this.rotate = Optional.empty();
        this.rotatePointX = Optional.empty();
        this.rotatePointY = Optional.empty();

        this.translateX = Optional.empty();
        this.translateY = Optional.empty();

        this.scaleX = Optional.empty();
        this.scaleY = Optional.empty();

        this.skewX = Optional.empty();
        this.skewY = Optional.empty();

        this.matrix = Optional.empty();

        return this;
    }

    @Override
    public boolean hasContent() {
        return rotate.isPresent() || translateX.isPresent() || scaleX.isPresent() || skewX.isPresent()
                || matrix.isPresent();
    }

    @Override
    public String getContent() {

        StringBuilder sb = new StringBuilder();
        matrix.ifPresent(m -> sb.append(String.format("matrix(%f %f %f %f %f %f) ",
                m[0], m[1], m[2], m[3], m[4], m[5])));
        translateX.ifPresent(x -> translateY.ifPresent(y -> sb.append(String.format("translate(%f %f) ", x, y))));
        scaleX.ifPresent(x -> scaleY.ifPresent(y -> sb.append(String.format("scale(%f %f) ", x, y))));
        skewX.ifPresent(x -> skewY.ifPresent(y -> sb.append(String.format("skewX(%f) skewY(%f) ", x, y))));
        rotate.ifPresent(angle -> {
            if (rotatePointX.isPresent() && rotatePointY.isPresent()) {
                sb.append(String.format("rotate(%f %f %f) ", angle, rotatePointX.get(), rotatePointY.get()));
            } else {
                sb.append(String.format("rotate(%f) ", angle));
            }
        });
        return sb.toString().trim();
    }
}
