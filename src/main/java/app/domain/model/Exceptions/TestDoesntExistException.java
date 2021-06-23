package app.domain.model.exceptions;

public class TestDoesntExistException extends IllegalArgumentException {

    public TestDoesntExistException() {
        super("This test doesn't exist!");
    }

    public TestDoesntExistException(String msg) {
        super(msg);
    }
}
