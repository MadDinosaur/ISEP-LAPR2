package app.domain.model.Exceptions;

public class TestAlreadyValidatedException extends IllegalArgumentException {

    public TestAlreadyValidatedException() { super("Test already validated!"); }

    public TestAlreadyValidatedException(String msg) { super(msg); }
}
