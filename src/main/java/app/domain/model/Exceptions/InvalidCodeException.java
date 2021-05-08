package app.domain.model.Exceptions;

public class InvalidCodeException extends IllegalArgumentException {

    public InvalidCodeException() { super("Invalid code!"); }

    public InvalidCodeException(String message) { super(message); }

}