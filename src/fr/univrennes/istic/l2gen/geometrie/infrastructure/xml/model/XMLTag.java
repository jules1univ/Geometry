package fr.univrennes.istic.l2gen.geometrie.infrastructure.xml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class XMLTag {
  protected String name;
  protected Optional<String> content;

  private Map<String, XMLAttribute> attributes = new HashMap<>();
  protected List<XMLTag> children = new ArrayList<>();

  public XMLTag(String name) {
    this.name = name;
    this.content = Optional.empty();
  }

  public XMLTag(String name, String content) {
    this.name = name;
    this.content = Optional.ofNullable(content);
  }

  public final String getTagName() {
    return name;
  }

  public final Optional<String> getTextContent() {
    return content;
  }

  public final void setTextContent(String content) {
    this.content = Optional.ofNullable(content);
  }

  public final XMLAttribute getAttribute(String name) {
    return attributes.get(name);
  }

  public final void addAttribute(XMLAttribute attr) {
    attributes.put(attr.name(), attr);
  }

  public final <T> void setAttribute(String name, T value) {
    attributes.put(name, new XMLAttribute(name, value.toString()));
  }

  public final void removeAttribute(String name) {
    attributes.remove(name);
  }

  public void addChild(XMLTag child) {
    children.add(child);
  }

  public final String toXMLString() {
    StringBuilder sb = new StringBuilder();
    sb.append("<").append(name);
    for (XMLAttribute attr : attributes.values()) {
      sb.append(" ").append(attr.toString());
    }
    if (children.isEmpty() && content.isEmpty()) {
      sb.append("/>");
      return sb.toString();
    }
    sb.append(">");

    content.ifPresent(sb::append);
    for (XMLTag child : children) {
      sb.append(child.toXMLString());
    }
    sb.append("</").append(name).append(">");
    return sb.toString();
  }
}
