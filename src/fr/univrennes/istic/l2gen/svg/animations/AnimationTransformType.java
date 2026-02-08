package fr.univrennes.istic.l2gen.svg.animations;

public enum AnimationTransformType {
    TRANSLATE,
    SCALE,
    ROTATE,
    SKEWX,
    SKEWY;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
