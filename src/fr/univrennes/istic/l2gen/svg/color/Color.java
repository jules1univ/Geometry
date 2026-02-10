package fr.univrennes.istic.l2gen.svg.color;

public final class Color {
    public static final Color TRANSPARENT = new Color("#00000000");

    public static final Color BLACK = new Color("#000000");
    public static final Color WHITE = new Color("#ffffff");

    public static final Color RED = new Color("#ff0000");
    public static final Color GREEN = new Color("#00ff00");
    public static final Color BLUE = new Color("#0000ff");

    private final String hex;

    public static Color hex(String hex) {
        return new Color(hex);
    }

    public static Color rgb(int r, int g, int b) {
        return new Color(r, g, b, 255);
    }

    public static Color rgba(int r, int g, int b, int a) {
        return new Color(r, g, b, a);
    }

    public static Color random() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b, 255);
    }

    public static Color raw(String raw) {
        if (raw.startsWith("rgb(") && raw.endsWith(")")) {
            String[] parts = raw.substring(4, raw.length() - 1).split(",");
            int r = Integer.parseInt(parts[0].trim());
            int g = Integer.parseInt(parts[1].trim());
            int b = Integer.parseInt(parts[2].trim());
            return new Color(r, g, b, 255);
        } else if (raw.startsWith("rgba(") && raw.endsWith(")")) {
            String[] parts = raw.substring(5, raw.length() - 1).split(",");
            int r = Integer.parseInt(parts[0].trim());
            int g = Integer.parseInt(parts[1].trim());
            int b = Integer.parseInt(parts[2].trim());
            int a = Integer.parseInt(parts[3].trim());
            return new Color(r, g, b, a);
        } else if (raw.startsWith("#")) {
            return new Color(raw);
        } else {
            return null;
        }
    }

    private Color(int r, int g, int b, int a) {
        this.hex = String.format("#%02x%02x%02x%02x", r, g, b, a);
    }

    private Color(String hex) {
        if (!hex.matches("^#([0-9a-fA-F]{3}|[0-9a-fA-F]{4}|[0-9a-fA-F]{6}|[0-9a-fA-F]{8})$")) {
            throw new IllegalArgumentException("Invalid hex color: " + hex);
        }
        this.hex = hex;
    }

    @Override
    public String toString() {
        return hex;
    }
}
