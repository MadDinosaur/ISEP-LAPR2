package app.domain.model.exceptions;

public class InvalidDateException extends  IllegalArgumentException {

    public InvalidDateException() { super("Invalid date!"); }

    public InvalidDateException(String message) { super(message); }
}
