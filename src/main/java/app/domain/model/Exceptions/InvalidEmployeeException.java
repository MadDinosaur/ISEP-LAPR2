package app.domain.model.Exceptions;

public class InvalidEmployeeException extends  IllegalArgumentException {

    public InvalidEmployeeException() { super("Employee already exists!"); }

    public InvalidEmployeeException(String message) { super(message); }
}
