package app.domain.model.Exceptions;

public class InvalidTestCodeException extends IllegalArgumentException {

    public InvalidTestCodeException() {
        super("This test code isn't associated to any of the tests available.");
    }

    public InvalidTestCodeException(String msg) {
        super(msg);
    }
}
