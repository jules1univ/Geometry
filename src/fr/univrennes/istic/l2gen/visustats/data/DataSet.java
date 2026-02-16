package fr.univrennes.istic.l2gen.visustats.data;

import java.util.ArrayList;
import java.util.List;

import fr.univrennes.istic.l2gen.svg.color.Color;

public record DataSet(List<Value> values) {

    public DataSet() {
        this(new ArrayList<>());
    }

    public double sum() {
        return this.values.stream().mapToDouble(Value::value).sum();
    }

    public double max() {
        return this.values.stream().mapToDouble(Value::value).max().orElse(0.0);
    }

    public double min() {
        return this.values.stream().mapToDouble(Value::value).min().orElse(0.0);
    }

    public double getValue(int index) {
        return this.values.get(index).value();
    }

    public Color getColor(int index) {
        return this.values.get(index).color();
    }

    public int size() {
        return this.values.size();
    }
}
