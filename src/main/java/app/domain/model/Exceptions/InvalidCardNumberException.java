package app.domain.model.exceptions;

public class InvalidCardNumberException extends IllegalArgumentException {
    public InvalidCardNumberException() { super("Invalid ID!"); }

    public InvalidCardNumberException(String message) { super(message); }
}
