package app.domain.model.Exceptions;

public class InvalidPhoneNumberException extends IllegalArgumentException {

    public InvalidPhoneNumberException() { super("Invalid phone number!"); }

    public InvalidPhoneNumberException(String message) { super(message); }

}
