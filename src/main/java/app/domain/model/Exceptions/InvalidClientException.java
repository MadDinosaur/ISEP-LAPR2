package app.domain.model.Exceptions;

public class InvalidClientException extends IllegalArgumentException {

    public InvalidClientException() {
        super("There's no client with such nhsID.");
    }
}
