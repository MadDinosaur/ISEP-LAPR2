package app.domain.model.Exceptions;

public class InvalidTINException extends IllegalArgumentException {

    public InvalidTINException() { super("Invalid TIN!"); }

    public InvalidTINException(String message) { super(message); }

}
