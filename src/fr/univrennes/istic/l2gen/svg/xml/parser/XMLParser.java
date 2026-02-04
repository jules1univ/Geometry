package fr.univrennes.istic.l2gen.svg.xml.parser;

import fr.univrennes.istic.l2gen.svg.xml.model.XMLAttribute;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;

public class XMLParser {
    private String source;
    private int index;

    public XMLParser(String xml) {
        this.source = xml;
        this.index = 0;
    }

    public XMLTag parse() throws XMLParseException {
        this.skipWhitespace();
        return this.parseTag();
    }

    private XMLTag parseTag() throws XMLParseException {
        if (!this.expect('<')) {
            throw new XMLParseException("Expected '<' at position " + index);
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
            index++;
            if (!this.expect('>')) {
                throw new XMLParseException("Expected '>' at position " + index);
            }
            return tag;
        }

        if (!this.expect('>')) {
            throw new XMLParseException("Expected '>' at position " + index);
        }

        StringBuilder content = new StringBuilder();
        while (index < source.length()) {
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
            throw new XMLParseException("Expected '<' at position " + index);
        }
        if (!this.expect('/')) {
            throw new XMLParseException("Expected '/' at position " + index);
        }
        String closingTagName = parseTagName();
        if (!closingTagName.equals(tagName)) {
            throw new XMLParseException("Mismatched closing tag. Expected </" + tagName +
                    "> but found </" + closingTagName + "> at position " + index);
        }
        if (!this.expect('>')) {
            throw new XMLParseException("Expected '>' at position " + index);
        }

        return tag;
    }

    private String parseTagName() throws XMLParseException {
        StringBuilder name = new StringBuilder();
        while (index < source.length()) {
            char c = source.charAt(index);
            if (Character.isWhitespace(c) || c == '>' || c == '/') {
                break;
            }
            name.append(c);
            index++;
        }
        if (name.length() == 0) {
            throw new XMLParseException("Expected tag name at position " + index);
        }
        return name.toString();
    }

    private XMLAttribute parseAttribute() throws XMLParseException {
        String attrName = parseAttributeName();
        this.skipWhitespace();

        if (!this.expect('=')) {
            throw new XMLParseException("Expected '=' after attribute name at position " + index);
        }

        this.skipWhitespace();
        String attrValue = parseAttributeValue();

        return new XMLAttribute(attrName, attrValue);
    }

    private String parseAttributeName() throws XMLParseException {
        StringBuilder name = new StringBuilder();
        while (index < source.length()) {
            char c = source.charAt(index);
            if (Character.isWhitespace(c) || c == '=') {
                break;
            }
            name.append(c);
            index++;
        }
        if (name.length() == 0) {
            throw new XMLParseException("Expected attribute name at position " + index);
        }
        return name.toString();
    }

    private String parseAttributeValue() throws XMLParseException {
        char quote = source.charAt(index);
        if (quote != '"' && quote != '\'') {
            throw new XMLParseException("Expected quote at position " + index);
        }
        index++;
        StringBuilder value = new StringBuilder();
        while (index < source.length()) {
            char c = source.charAt(index);
            if (c == quote) {
                index++;
                return this.unescapeXML(value.toString());
            }
            value.append(c);
            index++;
        }
        throw new XMLParseException("Unclosed attribute value at position " + index);
    }

    private String parseTextContent() {
        StringBuilder content = new StringBuilder();
        while (index < source.length()) {
            char c = source.charAt(index);
            if (c == '<') {
                break;
            }
            content.append(c);
            index++;
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
        while (index < source.length() && Character.isWhitespace(source.charAt(index))) {
            index++;
        }
    }

    private void skipComment() throws XMLParseException {
        if (!this.expect("<!--")) {
            throw new XMLParseException("Expected '<!--' at position " + index);
        }
        while (index < source.length() - 2) {
            if (source.charAt(index) == '-' &&
                    source.charAt(index + 1) == '-' &&
                    source.charAt(index + 2) == '>') {
                index += 3;
                this.skipWhitespace();
                return;
            }
            index++;
        }
        throw new XMLParseException("Unclosed comment");
    }

    private void skipDeclaration() throws XMLParseException {
        while (index < source.length()) {
            if (source.charAt(index) == '>') {
                index++;
                this.skipWhitespace();
                return;
            }
            index++;
        }
        throw new XMLParseException("Unclosed declaration");
    }

    private boolean peek(char expected) {
        return index < source.length() && source.charAt(index) == expected;
    }

    private boolean peek(String expected) {
        if (index + expected.length() > source.length()) {
            return false;
        }
        return source.substring(index, index + expected.length()).equals(expected);
    }

    private boolean expect(char expected) {
        if (this.peek(expected)) {
            index++;
            return true;
        }
        return false;
    }

    private boolean expect(String expected) {
        if (this.peek(expected)) {
            index += expected.length();
            return true;
        }
        return false;
    }
}