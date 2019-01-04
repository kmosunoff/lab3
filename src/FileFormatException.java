public class FileFormatException extends Exception {
    private final static String DEFAULT_MESSAGE = "Incorrect file format";

    public FileFormatException() {
        super(DEFAULT_MESSAGE);
    }

    public FileFormatException(String message) {
        super(DEFAULT_MESSAGE + " at line " + message);
    }
}
