package fr.univrennes.istic.l2gen.svg.xml.model;

import java.util.Optional;

public record XMLAttribute(String name, String value) {

  public String getValue() {
    return value;
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
    if (value == null) {
      return name;
    }
    return name + "=\"" + value + "\"";
  }
}
