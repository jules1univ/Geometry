package fr.univrennes.istic.l2gen.svg.animations;

public record AnimationDuration(long miliseconds) {
    public static final AnimationDuration INDEFINITE = new AnimationDuration(-1);

    public static AnimationDuration s(double seconds) {
        return new AnimationDuration((long) (seconds * 1000));
    }

    public static AnimationDuration ms(long miliseconds) {
        return new AnimationDuration(miliseconds);
    }

    public static AnimationDuration min(double minutes) {
        return new AnimationDuration((long) (minutes * 60 * 1000));
    }

    public static AnimationDuration infinite() {
        return INDEFINITE;
    }

    @Override
    public String toString() {
        if (miliseconds == -1) {
            return "indefinite";
        }
        return miliseconds + "ms";
    }

}
