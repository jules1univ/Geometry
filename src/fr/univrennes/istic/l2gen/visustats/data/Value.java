package fr.univrennes.istic.l2gen.visustats.data;

import java.util.Optional;

import fr.univrennes.istic.l2gen.svg.color.Color;

public record Value(double value, Optional<Label> label, Optional<Color> color) {
    public Value(double value) {
        this(value, Optional.empty(), Optional.empty());
    }

    public Value(double value, Label label) {
        this(value, Optional.of(label), Optional.empty());
    }

    public Value(double value, Color color) {
        this(value, Optional.empty(), Optional.of(color));
    }

    public Value(double value, Label label, Color color) {
        this(value, Optional.of(label), Optional.of(color));
    }
}
