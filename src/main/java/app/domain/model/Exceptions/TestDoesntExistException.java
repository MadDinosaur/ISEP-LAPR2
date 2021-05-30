package app.domain.model.Exceptions;

public class TestDoesntExistException extends IllegalArgumentException {

    public TestDoesntExistException() {
        super("This test doesn't exist!");
    }

    public TestDoesntExistException(String msg) {
        super(msg);
    }
}
