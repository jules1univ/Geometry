package fr.univrennes.istic.l2gen.svg.attributes;

import fr.univrennes.istic.l2gen.svg.attributes.util.BoundingBox;
import fr.univrennes.istic.l2gen.svg.attributes.util.PathBox;
import fr.univrennes.istic.l2gen.svg.interfaces.ISVGAttribute;

public class SVGPath implements ISVGAttribute {

    private StringBuilder path = new StringBuilder();

    private boolean isDirty = true;
    private BoundingBox cachedBox = null;

    public SVGPath() {
    }

    public void move(double x, double y) {
        this.path.append("M").append(x).append(",").append(y).append(" ");
        refreshBox();
    }

    public void line(double x, double y) {
        this.path.append("L").append(x).append(",").append(y).append(" ");
        refreshBox();
    }

    public void lineHorizontal(double x) {
        this.path.append("H").append(x).append(" ");
        refreshBox();
    }

    public void lineVertical(double y) {
        this.path.append("V").append(y).append(" ");
        refreshBox();
    }

    public void cubicBezier(double x1, double y1, double x2, double y2, double x, double y) {
        this.path.append("C").append(x1).append(",").append(y1).append(" ")
                .append(x2).append(",").append(y2).append(" ")
                .append(x).append(",").append(y).append(" ");
        refreshBox();
    }

    public void cubicBezierSmooth(double x2, double y2, double x, double y) {
        this.path.append("S").append(x2).append(",").append(y2).append(" ")
                .append(x).append(",").append(y).append(" ");
        refreshBox();
    }

    public void quadraticBezier(double x1, double y1, double x, double y) {
        this.path.append("Q").append(x1).append(",").append(y1).append(" ")
                .append(x).append(",").append(y).append(" ");
        refreshBox();
    }

    public void quadraticBezierSmooth(double x, double y) {
        this.path.append("T").append(x).append(",").append(y).append(" ");
        refreshBox();
    }

    public void arc(double rx, double ry, double xAxisRotation, boolean largeArcFlag, boolean sweepFlag, double x,
            double y) {
        this.path.append("A").append(rx).append(",").append(ry).append(" ")
                .append(xAxisRotation).append(" ")
                .append(largeArcFlag ? "1" : "0").append(" ")
                .append(sweepFlag ? "1" : "0").append(" ")
                .append(x).append(",").append(y).append(" ");
        refreshBox();
    }

    public void close() {
        this.path.append("Z ");
    }

    public void reset() {
        this.path.setLength(0);
        refreshBox();
    }

    private void refreshBox() {
        this.isDirty = true;
    }

    private void updateBox() {
        if (!this.isDirty) {
            return;
        }
        this.cachedBox = PathBox.computeBox(this.path.toString());
        this.isDirty = false;
    }

    public BoundingBox getBoundingBox() {
        updateBox();
        return this.cachedBox;
    }

    @Override
    public boolean hasContent() {
        return this.path.length() > 0;
    }

    @Override
    public String getContent() {
        return this.path.toString().trim();
    }
}