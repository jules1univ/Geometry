package fr.univrennes.istic.l2gen.svg.attributes.path;

public record BoundingBox(double minX, double minY, double maxX, double maxY) {

    public static BoundingBox empty() {
        return new BoundingBox(0.0, 0.0, 0.0, 0.0);
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
