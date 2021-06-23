package app.domain.model.exceptions;

public class UnregisteredParameterException extends  IllegalArgumentException {

    public UnregisteredParameterException() { super("Invalid parameter. Parameter code does not exit!"); }

    public UnregisteredParameterException(String message) { super(message); }
}
