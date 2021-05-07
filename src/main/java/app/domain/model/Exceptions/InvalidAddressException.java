package app.domain.model.Exceptions;

public class InvalidAddressException extends  IllegalArgumentException {

    public InvalidAddressException() { super("Invalid address!"); }

    public InvalidAddressException(String message) { super(message); }
}
