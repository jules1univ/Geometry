package fr.univrennes.istic.l2gen.svg.xml.parser;

import fr.univrennes.istic.l2gen.svg.xml.model.XMLAttribute;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;

public class XMLParser {
    private String xml;
    private int position;

    public XMLParser(String xml) {
        this.xml = xml;
        this.position = 0;
    }

    public XMLTag parse() throws XMLParseException {
        this.skipWhitespace();
        return this.parseTag();
    }

    private XMLTag parseTag() throws XMLParseException {
        if (!this.expect('<')) {
            throw new XMLParseException("Expected '<' at position " + position);
        }

        if (this.peek("!--")) {
            this.skipComment();
            return this.parseTag();
        }

        if (this.peek('?') || this.peek('!')) {
            this.skipDeclaration();
            return this.parseTag();
        }

        String tagName = parseTagName();
        XMLTag tag = new XMLTag(tagName);

        while (true) {
            this.skipWhitespace();
            if (this.peek('>') || this.peek('/')) {
                break;
            }
            XMLAttribute attr = parseAttribute();
            tag.addAttribute(attr);
        }

        this.skipWhitespace();

        if (this.peek('/')) {
            position++;
            if (!this.expect('>')) {
                throw new XMLParseException("Expected '>' at position " + position);
            }
            return tag;
        }

        if (!this.expect('>')) {
            throw new XMLParseException("Expected '>' at position " + position);
        }

        StringBuilder content = new StringBuilder();
        while (position < xml.length()) {
            this.skipWhitespace();

            if (this.peek('<')) {
                if (this.peek("</")) {
                    break;
                }

                if (content.length() > 0) {
                    tag.setTextContent(content.toString().trim());
                    content.setLength(0);
                }

                XMLTag child = this.parseTag();
                tag.addChild(child);
            } else {
                content.append(this.parseTextContent());
            }
        }

        if (content.length() > 0) {
            String contentStr = content.toString().trim();
            if (!contentStr.isEmpty()) {
                tag.setTextContent(contentStr);
            }
        }

        if (!this.expect('<')) {
            throw new XMLParseException("Expected '<' at position " + position);
        }
        if (!this.expect('/')) {
            throw new XMLParseException("Expected '/' at position " + position);
        }
        String closingTagName = parseTagName();
        if (!closingTagName.equals(tagName)) {
            throw new XMLParseException("Mismatched closing tag. Expected </" + tagName +
                    "> but found </" + closingTagName + "> at position " + position);
        }
        if (!this.expect('>')) {
            throw new XMLParseException("Expected '>' at position " + position);
        }

        return tag;
    }

    private String parseTagName() throws XMLParseException {
        StringBuilder name = new StringBuilder();
        while (position < xml.length()) {
            char c = xml.charAt(position);
            if (Character.isWhitespace(c) || c == '>' || c == '/') {
                break;
            }
            name.append(c);
            position++;
        }
        if (name.length() == 0) {
            throw new XMLParseException("Expected tag name at position " + position);
        }
        return name.toString();
    }

    private XMLAttribute parseAttribute() throws XMLParseException {
        String attrName = parseAttributeName();
        this.skipWhitespace();

        if (!this.expect('=')) {
            throw new XMLParseException("Expected '=' after attribute name at position " + position);
        }

        this.skipWhitespace();
        String attrValue = parseAttributeValue();

        return new XMLAttribute(attrName, attrValue);
    }

    private String parseAttributeName() throws XMLParseException {
        StringBuilder name = new StringBuilder();
        while (position < xml.length()) {
            char c = xml.charAt(position);
            if (Character.isWhitespace(c) || c == '=') {
                break;
            }
            name.append(c);
            position++;
        }
        if (name.length() == 0) {
            throw new XMLParseException("Expected attribute name at position " + position);
        }
        return name.toString();
    }

    private String parseAttributeValue() throws XMLParseException {
        char quote = xml.charAt(position);
        if (quote != '"' && quote != '\'') {
            throw new XMLParseException("Expected quote at position " + position);
        }
        position++;
        StringBuilder value = new StringBuilder();
        while (position < xml.length()) {
            char c = xml.charAt(position);
            if (c == quote) {
                position++;
                return this.unescapeXML(value.toString());
            }
            value.append(c);
            position++;
        }
        throw new XMLParseException("Unclosed attribute value at position " + position);
    }

    private String parseTextContent() {
        StringBuilder content = new StringBuilder();
        while (position < xml.length()) {
            char c = xml.charAt(position);
            if (c == '<') {
                break;
            }
            content.append(c);
            position++;
        }
        return this.unescapeXML(content.toString());
    }

    private String unescapeXML(String text) {
        return text.replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&amp;", "&")
                .replace("&quot;", "\"")
                .replace("&apos;", "'");
    }

    private void skipWhitespace() {
        while (position < xml.length() && Character.isWhitespace(xml.charAt(position))) {
            position++;
        }
    }

    private void skipComment() throws XMLParseException {
        if (!this.expect("<!--")) {
            throw new XMLParseException("Expected '<!--' at position " + position);
        }
        while (position < xml.length() - 2) {
            if (xml.charAt(position) == '-' &&
                    xml.charAt(position + 1) == '-' &&
                    xml.charAt(position + 2) == '>') {
                position += 3;
                this.skipWhitespace();
                return;
            }
            position++;
        }
        throw new XMLParseException("Unclosed comment");
    }

    private void skipDeclaration() throws XMLParseException {
        while (position < xml.length()) {
            if (xml.charAt(position) == '>') {
                position++;
                this.skipWhitespace();
                return;
            }
            position++;
        }
        throw new XMLParseException("Unclosed declaration");
    }

    private boolean peek(char expected) {
        return position < xml.length() && xml.charAt(position) == expected;
    }

    private boolean peek(String expected) {
        if (position + expected.length() > xml.length()) {
            return false;
        }
        return xml.substring(position, position + expected.length()).equals(expected);
    }

    private boolean expect(char expected) {
        if (this.peek(expected)) {
            position++;
            return true;
        }
        return false;
    }

    private boolean expect(String expected) {
        if (this.peek(expected)) {
            position += expected.length();
            return true;
        }
        return false;
    }
}