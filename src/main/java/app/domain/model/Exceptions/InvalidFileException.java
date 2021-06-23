package app.domain.model.exceptions;

public class InvalidFileException extends IllegalArgumentException {
    public InvalidFileException() {
        super("The imported file must be .csv");
    }
}
