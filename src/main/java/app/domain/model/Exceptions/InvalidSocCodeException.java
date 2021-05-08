package app.domain.model.Exceptions;

public class InvalidSocCodeException extends IllegalArgumentException {
    public InvalidSocCodeException() {
        super("Standard Occupational Classification must be four digits!");
    }
    InvalidSocCodeException(String message) {
        super(message);
    }
}
