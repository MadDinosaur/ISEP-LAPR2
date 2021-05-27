package app.domain.model.Exceptions;

public class InvalidTextReportException extends IllegalArgumentException {
    public InvalidTextReportException() {
        super("The text for the report must contain a maximum of 400 words.")
    }

    public InvalidTextReportException(String msg) {
        super(msg);
    }
}
