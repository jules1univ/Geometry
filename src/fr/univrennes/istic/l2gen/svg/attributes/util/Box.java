package fr.univrennes.istic.l2gen.svg.attributes.util;

public record Box(double minX, double minY, double maxX, double maxY) {

    public static Box empty() {
        return new Box(0.0, 0.0, 0.0, 0.0);
    }

    public double getWidth() {
        return maxX - minX;
    }

    public double getHeight() {
        return maxY - minY;
    }

    public double getCenterX() {
        return (minX + maxX) / 2;
    }

    public double getCenterY() {
        return (minY + maxY) / 2;
    }
}
