package app.domain.model;

import app.domain.model.Exceptions.InvalidMetricException;
import app.domain.model.Exceptions.InvalidReferenceValueException;
import app.domain.model.Exceptions.InvalidSampleValueException;

import java.util.Date;

public class TestParameterResult {
    private Date createdAt;
    private Double value;
    private String metric;
    private ReferenceValue refValue;

    public TestParameterResult(String value, String metric, ReferenceValue refValue) {
        validateMetric(metric);
        validateRefValue(refValue);
        this.value = validateValue(value);
        this.metric = metric;
        this.refValue = refValue;
        this.createdAt = new Date();
    }

    private Double validateValue(String value) {
        Double parsedValue;

        try {
            parsedValue = Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            throw new InvalidSampleValueException("Result must be a number!");
        }
        if (parsedValue < 0) throw new InvalidSampleValueException();

        return parsedValue;
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

    public Double getValue() {
        return value;
    }

    public String getMetric() {
        return metric;
    }

    public ReferenceValue getRefValue() {
        return refValue;
    }

    @Override
    public String toString() {
        return String.format("Result: %.2f%s    Min. Ref. Value: %.2f%s    Max. Ref. Value: %.2f%s", value, metric, refValue.getMinValue(), refValue.getMetric(), refValue.getMaxValue(), refValue.getMaxValue());
    }
}
