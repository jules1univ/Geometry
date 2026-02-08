package fr.univrennes.istic.l2gen.svg.animations;

public enum AnimationFill {
    REMOVE,
    FREEZE,
    AUTO;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
