package app.domain.model;

public class ReferenceValue {
    private final Double minValue;
    private final Double maxValue;
    private final String metric;

    public ReferenceValue(Double minValue, Double maxValue, String metric) {
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.metric = metric;
    }

    public Double getMinValue() {
        return minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public String getMetric() { return metric; }
}
