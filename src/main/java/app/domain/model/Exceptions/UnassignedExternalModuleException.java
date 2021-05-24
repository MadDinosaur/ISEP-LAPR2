package app.domain.model.Exceptions;

public class UnassignedExternalModuleException extends  IllegalArgumentException {

    public UnassignedExternalModuleException() { super("There is no external reference value module assigned to this test type!"); }

    public UnassignedExternalModuleException(String message) { super(message); }
}
