package fr.univrennes.istic.l2gen.svg.xml.parser;

import fr.univrennes.istic.l2gen.svg.xml.model.XMLAttribute;
import fr.univrennes.istic.l2gen.svg.xml.model.XMLTag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class XMLParser {
    private BufferedReader br;
    private int current;
    private int index;

    public XMLParser(BufferedReader reader) throws IOException {
        this.br = reader;
        this.index = 0;
        this.current = reader.read();
    }

    public XMLParser(String xml) throws IOException {
        this(new BufferedReader(new StringReader(xml)));
    }

    public XMLParser(Reader reader) throws IOException {
        this(new BufferedReader(reader));
    }

    public XMLTag parse() throws XMLParseException, IOException {
        this.skipWhitespace();
        return this.parseTag();
    }

    private XMLTag parseTag() throws XMLParseException, IOException {
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
            advance();
            if (!this.expect('>')) {
                throw new XMLParseException("Expected '>' at position " + index);
            }
            return tag;
        }

        if (!this.expect('>')) {
            throw new XMLParseException("Expected '>' at position " + index);
        }

        StringBuilder content = new StringBuilder();
        while (current != -1) {
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
                tag.appendChild(child);
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

    private String parseTagName() throws XMLParseException, IOException {
        StringBuilder name = new StringBuilder();
        while (current != -1) {
            char c = (char) current;
            if (Character.isWhitespace(c) || c == '>' || c == '/') {
                break;
            }
            name.append(c);
            advance();
        }
        if (name.length() == 0) {
            throw new XMLParseException("Expected tag name at position " + index);
        }
        return name.toString();
    }

    private XMLAttribute parseAttribute() throws XMLParseException, IOException {
        String attrName = parseAttributeName();
        this.skipWhitespace();

        if (!this.expect('=')) {
            throw new XMLParseException("Expected '=' after attribute name at position " + index);
        }

        this.skipWhitespace();
        String attrValue = parseAttributeValue();

        return new XMLAttribute(attrName, attrValue);
    }

    private String parseAttributeName() throws XMLParseException, IOException {
        StringBuilder name = new StringBuilder();
        while (current != -1) {
            char c = (char) current;
            if (Character.isWhitespace(c) || c == '=') {
                break;
            }
            name.append(c);
            advance();
        }
        if (name.length() == 0) {
            throw new XMLParseException("Expected attribute name at position " + index);
        }
        return name.toString();
    }

    private String parseAttributeValue() throws XMLParseException, IOException {
        if (current == -1) {
            throw new XMLParseException("Expected quote at position " + index);
        }
        char quote = (char) current;
        if (quote != '"' && quote != '\'') {
            throw new XMLParseException("Expected quote at position " + index);
        }
        advance();
        StringBuilder value = new StringBuilder();
        while (current != -1) {
            char c = (char) current;
            if (c == quote) {
                advance();
                return this.unescapeXML(value.toString());
            }
            value.append(c);
            advance();
        }
        throw new XMLParseException("Unclosed attribute value at position " + index);
    }

    private String parseTextContent() throws IOException {
        StringBuilder content = new StringBuilder();
        while (current != -1) {
            char c = (char) current;
            if (c == '<') {
                break;
            }
            content.append(c);
            advance();
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

    private void skipWhitespace() throws IOException {
        while (current != -1 && Character.isWhitespace((char) current)) {
            advance();
        }
    }

    private void skipComment() throws XMLParseException, IOException {
        if (!this.expect("<!--")) {
            throw new XMLParseException("Expected '<!--' at position " + index);
        }
        int dashCount = 0;
        while (current != -1) {
            char c = (char) current;
            if (dashCount == 2 && c == '>') {
                advance();
                this.skipWhitespace();
                return;
            }
            if (c == '-') {
                dashCount++;
            } else {
                dashCount = 0;
            }
            advance();
        }
        throw new XMLParseException("Unclosed comment");
    }

    private void skipDeclaration() throws XMLParseException, IOException {
        while (current != -1) {
            if ((char) current == '>') {
                advance();
                this.skipWhitespace();
                return;
            }
            advance();
        }
        throw new XMLParseException("Unclosed declaration");
    }

    private boolean peek(char expected) {
        return current != -1 && (char) current == expected;
    }

    private boolean peek(String expected) throws IOException {
        if (current == -1) {
            return false;
        }

        br.mark(expected.length());
        boolean matches = true;

        for (int i = 0; i < expected.length(); i++) {
            int ch = (i == 0) ? current : br.read();
            if (ch == -1 || (char) ch != expected.charAt(i)) {
                matches = false;
                break;
            }
        }

        br.reset();
        return matches;
    }

    private boolean expect(char expected) throws IOException {
        if (this.peek(expected)) {
            advance();
            return true;
        }
        return false;
    }

    private boolean expect(String expected) throws IOException {
        if (this.peek(expected)) {
            for (int i = 0; i < expected.length(); i++) {
                advance();
            }
            return true;
        }
        return false;
    }

    private void advance() throws IOException {
        current = br.read();
        index++;
    }
}