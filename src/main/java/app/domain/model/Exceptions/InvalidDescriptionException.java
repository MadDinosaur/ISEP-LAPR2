package app.domain.model.exceptions;

public class InvalidDescriptionException extends IllegalArgumentException {

    public InvalidDescriptionException() { super("Invalid description!"); }

    public InvalidDescriptionException(String message) { super(message); }

}