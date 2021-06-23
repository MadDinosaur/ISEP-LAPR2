package app.domain.model.exceptions;

public class InvalidEmailException extends  IllegalArgumentException {

    public InvalidEmailException() { super("Invalid email!"); }

    public InvalidEmailException(String message) { super(message); }
}
