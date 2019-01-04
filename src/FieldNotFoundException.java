public class FieldNotFoundException extends Exception {
    private final static String DEFAULT_MESSAGE = "FieldNotFoundException";

    public FieldNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public FieldNotFoundException(String message) {
        super(message + " is not found");
    }
}
