package app.domain.model.Exceptions;

public class InvalidTestException extends IllegalArgumentException {
    public InvalidTestException() {
        super("This test already exists on the system.");
    }
}
