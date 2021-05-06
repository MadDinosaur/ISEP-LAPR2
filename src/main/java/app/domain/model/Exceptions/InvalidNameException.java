package app.domain.model.Exceptions;

public class InvalidNameException extends IllegalArgumentException {

    public InvalidNameException() { super("Invalid name!"); }

    public InvalidNameException(String message) { super(message); }

}