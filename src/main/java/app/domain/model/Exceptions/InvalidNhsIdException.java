package app.domain.model.Exceptions;

public class InvalidNhsIdException extends IllegalArgumentException {

    public InvalidNhsIdException() {}

    public InvalidNhsIdException(String message) { super(message); }

}
