package app.domain.model;

import app.domain.model.Exceptions.InvalidTestResultException;

public class TestParameter {
    private final Parameter param;
    private TestParameterResult result;

    public TestParameter(Parameter param) {
        if (param != null) {
            this.param = param;
        }else throw new IllegalArgumentException("Parameter is null.");
    }

    public TestParameterResult createTestParameterResult(String value, String metric, ReferenceValue refValue) {
       if(value != null) {
           if(metric != null) {
               if(refValue != null){
                return new TestParameterResult(value, metric, refValue);
               }else throw new IllegalArgumentException("Reference value is null.");
           }else throw new IllegalArgumentException("Metric is null");
       }else throw new IllegalArgumentException("Value is null.");
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
