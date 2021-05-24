package app.domain.model.Exceptions;

public class InvalidMetricException extends  IllegalArgumentException {

    public InvalidMetricException() { super("Invalid test result metric!"); }

    public InvalidMetricException(String message) { super(message); }
}
