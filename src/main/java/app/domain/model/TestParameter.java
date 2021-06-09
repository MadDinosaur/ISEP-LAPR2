package app.domain.model;

import app.domain.model.Exceptions.InvalidReferenceValueException;
import app.domain.model.Exceptions.InvalidTestResultException;

public class TestParameter {
    private Parameter param;
    private TestParameterResult result;

    public TestParameter(Parameter param) {
        this.param = param;
    }

    public TestParameterResult createTestParameterResult(String value, String metric, ReferenceValue refValue) {
       return new TestParameterResult(value, metric, refValue);
    }

    public boolean addTestParameterResult(TestParameterResult result) {
        if(this.result != null || result == null) return false;

        this.result = result;
        return true;
    }

    public ReferenceValue getReferenceValue() {
        return result.getRefValue();
    }

    public Parameter getParameter() {
        return param;
    }

    public TestParameterResult getResult() {
        if (result == null) throw new InvalidTestResultException("Test results haven't been registered yet");
        return result;
    }
}
