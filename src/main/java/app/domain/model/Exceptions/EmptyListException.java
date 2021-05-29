package app.domain.model.Exceptions;

public class EmptyListException extends IllegalArgumentException {

    public EmptyListException() {
        super("There are no tests in the list!");
    }

    public EmptyListException(String msg) {
        super(msg);
    }
}
