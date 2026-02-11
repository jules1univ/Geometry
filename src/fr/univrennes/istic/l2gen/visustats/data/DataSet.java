package fr.univrennes.istic.l2gen.visustats.data;

import java.util.List;

import fr.univrennes.istic.l2gen.svg.color.Color;

public record DataSet(List<Value> values, Label axisX, Color mainColor) {

    public double getValue(int index) {
        return this.values.get(index).value();
    }

    public Value get(int index) {
        return this.values.get(index);
    }

    public int size() {
        return this.values.size();
    }
}
