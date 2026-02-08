package fr.univrennes.istic.l2gen.svg.animations;

public enum AnimationRestart {
    ALWAYS,
    WHEN_NOT_ACTIVE,
    NEVER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
