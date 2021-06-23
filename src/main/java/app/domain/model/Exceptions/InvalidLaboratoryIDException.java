package app.domain.model.exceptions;

public class InvalidLaboratoryIDException extends IllegalArgumentException {
    public InvalidLaboratoryIDException() { super("Invalid ID!"); }

    public InvalidLaboratoryIDException(String message) { super(message); }

}