package app.domain.model;

import app.domain.adapter.ExternalModule;

public class TestParameter {
    private Parameter param;
    private TestParameterResult result;

    public boolean createTestParameterResult(String value, String metric, ReferenceValue refValue) {
        try {
            this.result = new TestParameterResult(value, metric, refValue);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public Parameter getParameter() {
        return param;
    }

    public TestParameterResult getResult() {
        return result;
    }
}
