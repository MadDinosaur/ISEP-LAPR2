package app.domain.model.exceptions;

public class TestAlreadyValidatedException extends IllegalArgumentException {

    public TestAlreadyValidatedException() { super("Test already validated!"); }

    public TestAlreadyValidatedException(String msg) { super(msg); }
}
