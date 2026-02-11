package fr.univrennes.istic.l2gen.visustats.csv.parser;

import fr.univrennes.istic.l2gen.visustats.csv.model.CSVRow;
import fr.univrennes.istic.l2gen.visustats.csv.model.CSVTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    private char delimiter;
    private char quoteChar;
    private boolean hasHeaders;
    private boolean trimWhitespace;

    public CSVParser() {
        this(',', '"', false, true);
    }

    public CSVParser(char delimiter, char quoteChar, boolean hasHeaders, boolean trimWhitespace) {
        this.delimiter = delimiter;
        this.quoteChar = quoteChar;
        this.hasHeaders = hasHeaders;
        this.trimWhitespace = trimWhitespace;
    }

    public CSVParser withDelimiter(char delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    public CSVParser withQuoteChar(char quoteChar) {
        this.quoteChar = quoteChar;
        return this;
    }

    public CSVParser withHeaders(boolean hasHeaders) {
        this.hasHeaders = hasHeaders;
        return this;
    }

    public CSVParser withTrimWhitespace(boolean trimWhitespace) {
        this.trimWhitespace = trimWhitespace;
        return this;
    }

    public CSVTable parse(File file) throws CSVParseException {
        try (FileReader reader = new FileReader(file)) {
            return parse(reader);
        } catch (IOException e) {
            throw new CSVParseException("Error reading file: " + file.getAbsolutePath(), e);
        }
    }

    public CSVTable parse(String csvData) throws CSVParseException {
        try (StringReader reader = new StringReader(csvData)) {
            return parse(reader);
        }
    }

    public CSVTable parse(Reader reader) throws CSVParseException {
        CSVTable table = new CSVTable();

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            int lineNumber = 0;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    CSVRow row = parseLine(line);

                    if (firstLine && hasHeaders) {
                        table.setHeaders(row);
                        firstLine = false;
                    } else {
                        table.addRow(row);
                        firstLine = false;
                    }
                } catch (CSVParseException e) {
                    throw new CSVParseException(e.getMessage(), lineNumber);
                }
            }

        } catch (IOException e) {
            throw new CSVParseException("Error reading CSV data", e);
        }

        return table;
    }

    private CSVRow parseLine(String line) throws CSVParseException {
        CSVRow row = new CSVRow();
        StringBuilder field = new StringBuilder();
        boolean inQuotes = false;
        boolean fieldStarted = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == quoteChar) {
                if (inQuotes) {
                    if (i + 1 < line.length() && line.charAt(i + 1) == quoteChar) {
                        field.append(quoteChar);
                        i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    if (fieldStarted && field.length() > 0) {
                        throw new CSVParseException("Unexpected quote character in field");
                    }
                    inQuotes = true;
                }
                fieldStarted = true;
            } else if (c == delimiter && !inQuotes) {
                String value = field.toString();
                if (trimWhitespace) {
                    value = value.trim();
                }
                row.addCell(value);
                field.setLength(0);
                fieldStarted = false;
            } else {
                field.append(c);
                fieldStarted = true;
            }
        }

        if (inQuotes) {
            throw new CSVParseException("Unclosed quote in line");
        }

        String value = field.toString();
        if (trimWhitespace) {
            value = value.trim();
        }
        row.addCell(value);

        return row;
    }

    public static CSVParser semicolonDelimited() {
        return new CSVParser(';', '"', false, true);
    }

    public static CSVParser tabDelimited() {
        return new CSVParser('\t', '"', false, true);
    }

    public static CSVParser pipeDelimited() {
        return new CSVParser('|', '"', false, true);
    }
}