package app.domain.model.Exceptions;

public class InvalidReferenceValueException extends  IllegalArgumentException {

    public InvalidReferenceValueException() { super("Invalid reference value returned by external module!"); }

    public InvalidReferenceValueException(String message) { super(message); }
}
