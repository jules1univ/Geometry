package fr.univrennes.istic.l2gen.svg.xml.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Représente une balise XML avec des attributs, du contenu textuel et des
 * éléments enfants.
 * Fournit méthodes pour manipuler la structure XML et convertir en chaîne de
 * caractères.
 */
public class XMLTag {
  protected String name;
  protected Optional<String> content;

  private Map<String, XMLAttribute> attributes = new HashMap<>();
  protected List<XMLTag> children = new ArrayList<>();

  /**
   * Constructeur avec le nom de la balise.
   * 
   * @param name le nom de la balise
   */
  public XMLTag(String name) {
    this.name = name;
    this.content = Optional.empty();
  }

  /**
   * Constructeur avec le nom et le contenu textuel de la balise.
   * 
   * @param name    le nom de la balise
   * @param content le contenu textuel initial
   */
  public XMLTag(String name, String content) {
    this.name = name;
    this.content = Optional.ofNullable(content);
  }

  /**
   * Retourne le nom de la balise.
   * 
   * @return le nom
   */
  public final String getTagName() {
    return name;
  }

  /**
   * Retourne le contenu textuel de la balise.
   * 
   * @return un Optional contenant le contenu ou vide
   */
  public final Optional<String> getTextContent() {
    return content;
  }

  /**
   * Définit le contenu textuel de la balise.
   * 
   * @param content le nouveau contenu (peut être null)
   */
  public final void setTextContent(String content) {
    this.content = Optional.ofNullable(content);
  }

  /**
   * Vérifie si cette balise possède un attribut donné.
   * 
   * @param name le nom de l'attribut
   * @return true si l'attribut existe, false sinon
   */
  public final boolean hasAttribute(String name) {
    return attributes.containsKey(name);
  }

  /**
   * Retourne un attribut par son nom.
   * 
   * @param name le nom de l'attribut
   * @return l'attribut ou null s'il n'existe pas
   */
  public final XMLAttribute getAttribute(String name) {
    return attributes.get(name);
  }

  /**
   * Ajoute un attribut à cette balise.
   * 
   * @param attr l'attribut à ajouter
   */
  public final void addAttribute(XMLAttribute attr) {
    attributes.put(attr.name(), attr);
  }

  /**
   * Ajoute un attribut avec une valeur de n'importe quel type.
   * 
   * @param <T>   le type de la valeur
   * @param name  le nom de l'attribut
   * @param value la valeur (sera convertie en String)
   */
  public final <T> void addAttribute(String name, T value) {
    attributes.put(name, new XMLAttribute(name, value.toString()));
  }

  /**
   * Supprime un attribut par son nom.
   * 
   * @param name le nom de l'attribut à supprimer
   */
  public final void removeAttribute(String name) {
    attributes.remove(name);
  }

  /**
   * Ajoute une balise enfant.
   * 
   * @param child la balise enfant à ajouter
   */
  public void appendChild(XMLTag child) {
    children.add(child);
  }

  /**
   * Supprime une balise enfant.
   * 
   * @param child la balise enfant à supprimer
   */
  public void removeChild(XMLTag child) {
    children.remove(child);
  }

  /**
   * Retourne le nombre d'éléments enfants.
   * 
   * @return le nombre d'enfants
   */
  public int getChildrenCount() {
    return children.size();
  }

  /**
   * Retourne une itération sur tous les enfants.
   * 
   * @return une itérable des enfants
   */
  public Iterable<XMLTag> getChildren() {
    return children;
  }

  /**
   * Retourne un enfant par son index.
   * 
   * @param index l'index de l'enfant
   * @return la balise enfant ou null si l'index est invalide
   */
  public XMLTag getChildren(int index) {
    if (index < 0 || index >= children.size()) {
      return null;
    }

    return children.get(index);
  }

  /**
   * Retourne le premier enfant avec le nom spécifié.
   * 
   * @param tagName le nom de la balise enfant recherchée
   * @return la première balise correspondante ou null
   */
  public XMLTag getFirstChildByName(String tagName) {
    for (XMLTag child : children) {
      if (child.name.equals(tagName)) {
        return child;
      }
    }
    return null;
  }

  /**
   * Retourne le premier enfant.
   * 
   * @return le premier enfant ou null si la liste est vide
   */
  public XMLTag getFirstChild() {
    if (this.children.size() <= 0) {
      return null;
    }
    return this.children.get(0);
  }

  /**
   * Retourne la représentation en chaîne au format XML.
   * 
   * @return la balise formatée en XML
   */
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
