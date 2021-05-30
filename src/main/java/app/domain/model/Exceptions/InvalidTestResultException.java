package app.domain.model.Exceptions;

public class InvalidTestResultException extends IllegalArgumentException {
    public InvalidTestResultException(String s) {
        super(s);
    }
}
