package fr.univrennes.istic.l2gen.visustats.csv.parser;

public class CSVParseException extends Exception {

    public CSVParseException(String message) {
        super(message);
    }

    public CSVParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVParseException(String message, int lineNumber) {
        super(message + " at line " + lineNumber);
    }
}