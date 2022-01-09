package app.domain.model.exceptions;

public class EmptyListException extends IllegalArgumentException {

    public EmptyListException() {
        super("There are no tests in the list!");
    }

    public EmptyListException(String msg) {
        super(msg);
    }
}
