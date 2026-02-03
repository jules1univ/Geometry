package fr.univrennes.istic.l2gen.geometrie.xml.model;

import java.util.Optional;

public record XMLAttribute(String name, String value) {

  public Optional<String> getValue() {
    return Optional.ofNullable(value);
  }

  public Optional<Integer> getIntValue() {
    try {
      return Optional.of(Integer.parseInt(value));
    } catch (NumberFormatException e) {
      return Optional.empty();
    }
  }

  public Optional<Double> getDoubleValue() {
    try {
      return Optional.of(Double.parseDouble(value));
    } catch (NumberFormatException e) {
      return Optional.empty();
    }
  }

  public Optional<Boolean> getBooleanValue() {
    try {
      return Optional.of(Boolean.parseBoolean(value));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public String toString() {
    return name + "=\"" + value + "\"";
  }
}
