package app.domain.model.exceptions;

public class InvalidSampleValueException extends  IllegalArgumentException {

    public InvalidSampleValueException() { super("Invalid test result value!"); }

    public InvalidSampleValueException(String message) { super(message); }
}
