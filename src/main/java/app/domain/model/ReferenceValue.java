package app.domain.model;

public class ReferenceValue {
    private Double minValue;
    private Double maxValue;
    private String metric;

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
}
