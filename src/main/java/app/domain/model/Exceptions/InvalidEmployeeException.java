package app.domain.model.exceptions;

public class InvalidEmployeeException extends  IllegalArgumentException {

    public InvalidEmployeeException() { super("Employee already exists!"); }

    public InvalidEmployeeException(String message) { super(message); }
}
