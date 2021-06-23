package app.domain.model.exceptions;

public class InvalidAddressException extends  IllegalArgumentException {

    public InvalidAddressException() { super("Invalid address!"); }

    public InvalidAddressException(String message) { super(message); }
}
