package fr.univrennes.istic.l2gen.svg.xml.model;

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

  public final boolean hasAttribute(String name) {
    return attributes.containsKey(name);
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

  public void appendChild(XMLTag child) {
    children.add(child);
  }

  public void removeChild(XMLTag child) {
    children.remove(child);
  }

  public int getChildrenCount() {
    return children.size();
  }

  public Iterable<XMLTag> getChildren() {
    return children;
  }

  public XMLTag getChildren(int index) {
    if (index < 0 || index >= children.size()) {
      return null;
    }

    return children.get(index);
  }

  public XMLTag getFirstChildByName(String tagName) {
    for (XMLTag child : children) {
      if (child.name.equals(tagName)) {
        return child;
      }
    }
    return null;
  }

  public XMLTag getFirstChild() {
    if (this.children.size() <= 0) {
      return null;
    }
    return this.children.get(0);
  }

  @Override
  public final String toString() {
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
      sb.append(child.toString());
    }
    sb.append("</").append(name).append(">");
    return sb.toString();
  }
}
