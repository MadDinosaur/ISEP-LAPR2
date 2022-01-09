package app.domain.model.exceptions;

public class InvalidTestType extends IllegalArgumentException {

    public InvalidTestType() {
        super("This test type already exists on the list");
    }

    public InvalidTestType(String msg) {
        super(msg);
    }
}
