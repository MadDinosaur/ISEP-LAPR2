package app.domain.model.exceptions;

public class InvalidCategoryException extends IllegalArgumentException {

    public InvalidCategoryException() {
       super("This category was already selected!!");
    }

    public InvalidCategoryException(String msg) {
        super(msg);
    }
}
