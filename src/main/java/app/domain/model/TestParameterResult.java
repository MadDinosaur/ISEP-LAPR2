package app.domain.model;

import app.domain.model.Exceptions.InvalidMetricException;
import app.domain.model.Exceptions.InvalidReferenceValueException;
import app.domain.model.Exceptions.InvalidSampleValueException;

import java.util.Date;

public class TestParameterResult {
    private Date createdAt;
    private int value;
    private String metric;
    private ReferenceValue refValue;

    public TestParameterResult(int value, String metric, ReferenceValue refValue) {
        validateValue(value);
        validateMetric(metric);
        validateRefValue(refValue);
        this.value = value;
        this.metric = metric;
        this.refValue = refValue;
        this.createdAt = new Date();
    }

    private void validateValue(int value) {
        if (value < 0) throw new InvalidSampleValueException();
    }

    private void validateMetric(String metric) {
        if (metric.isEmpty() || metric.equals(" ")) throw new InvalidMetricException();
    }

    private void validateRefValue(ReferenceValue refValue) {
        if (refValue == null) throw new InvalidReferenceValueException();
    }

    public Date getCreationDate() {
        return createdAt;
    }

    public int getValue() {
        return value;
    }

    public String getMetric() {
        return metric;
    }

    public ReferenceValue getRefValue() {
        return refValue;
    }
}
