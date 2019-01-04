public class IncorrectInputTypeException extends Exception {
    private final static String DEFAULT_MESSAGE = "Incorrect input type";

    public IncorrectInputTypeException() {
        super(DEFAULT_MESSAGE);
    }

    public IncorrectInputTypeException(String message) {
        super(message);
    }
}
