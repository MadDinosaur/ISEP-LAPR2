package app.domain.model.Exceptions;

public class InvalidFileException extends IllegalArgumentException {
    public InvalidFileException() {
        super("The imported file must be .csv");
    }
}
