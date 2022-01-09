package app.domain.model.exceptions;

public class InvalidCodeException extends IllegalArgumentException {

    public InvalidCodeException() { super("Invalid code!"); }

    public InvalidCodeException(String message) { super(message); }

}