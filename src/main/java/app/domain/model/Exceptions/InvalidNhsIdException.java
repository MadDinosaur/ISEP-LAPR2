package app.domain.model.exceptions;

public class InvalidNhsIdException extends IllegalArgumentException {

    public InvalidNhsIdException() {}

    public InvalidNhsIdException(String message) { super(message); }

}
