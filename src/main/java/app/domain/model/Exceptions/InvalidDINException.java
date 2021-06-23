package app.domain.model.exceptions;

public class InvalidDINException extends  IllegalArgumentException {

    public InvalidDINException() { super("Invalid doctor index number. Must have 6 digits!"); }

    public InvalidDINException(String message) { super(message); }
}
