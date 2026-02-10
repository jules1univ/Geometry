package fr.univrennes.istic.l2gen.visustats.data;

import fr.univrennes.istic.l2gen.svg.color.Color;

public record Label(String name, Color color) {
    public Label(String name) {
        this(name, Color.BLACK);
    }
}
