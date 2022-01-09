package app.domain.model.exceptions;

public class InvalidSocCodeException extends IllegalArgumentException {
    public InvalidSocCodeException() {
        super("Standard Occupational Classification must be four digits!");
    }
    InvalidSocCodeException(String message) {
        super(message);
    }
}
