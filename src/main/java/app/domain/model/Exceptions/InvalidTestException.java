package app.domain.model.exceptions;

public class InvalidTestException extends IllegalArgumentException {
    public InvalidTestException() {
        super("This test already exists on the system.");
    }
}
