package fr.univrennes.istic.l2gen.svg.animations;

public record AnimationCount(int count) {
    public static final AnimationCount INDEFINITE = new AnimationCount(-1);

    @Override
    public String toString() {
        if (count == -1) {
            return "indefinite";
        }
        return Integer.toString(count);
    }
}
