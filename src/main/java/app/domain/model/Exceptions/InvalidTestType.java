package app.domain.model.Exceptions;

public class InvalidTestType extends IllegalArgumentException {

    public InvalidTestType() {
        super("This testtype already exists on the list");
    }

    public InvalidTestType(String msg) {
        super(msg);
    }
}
