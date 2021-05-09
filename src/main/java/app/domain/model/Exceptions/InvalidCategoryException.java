package app.domain.model.Exceptions;

public class InvalidCategoryException extends IllegalArgumentException {

    public InvalidCategoryException() {
       super("This category was already selected!!");
    }

    public InvalidCategoryException(String msg) {
        super(msg);
    }
}
