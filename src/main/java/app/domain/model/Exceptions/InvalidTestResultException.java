package app.domain.model.exceptions;

public class InvalidTestResultException extends IllegalArgumentException {
    public InvalidTestResultException(String s) {
        super(s);
    }
}
