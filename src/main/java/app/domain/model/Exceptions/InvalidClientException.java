package app.domain.model.exceptions;

public class InvalidClientException extends IllegalArgumentException {

    public InvalidClientException() {
        super("There's no client with such nhsID.");
    }

    public InvalidClientException(String message) {
        super(message);
    }
}
