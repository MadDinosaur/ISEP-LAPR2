package app.domain.model;

import app.domain.adapter.ExternalModule;

public class TestParameter {
    Parameter param;
    TestParameterResult result;

    public boolean createTestParameterResult(int value, String metric, ReferenceValue refValue) {
        try {
            this.result = new TestParameterResult(value, metric, refValue);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
