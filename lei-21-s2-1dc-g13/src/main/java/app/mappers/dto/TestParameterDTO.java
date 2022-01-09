package app.mappers.dto;

public class TestParameterDTO {
    private String parameter;
    private String resultValue;
    private String resultMetric;
    private String refValueMin;
    private String refValueMetric;
    private String refValueMax;

    public TestParameterDTO(String parameter, String resultValue, String resultMetric, String refValueMin, String refValueMetric, String refValueMax) {
        this.parameter = parameter;
        this.resultValue = resultValue;
        this.resultMetric = resultMetric;
        this.refValueMin = refValueMin;
        this.refValueMetric = refValueMetric;
        this.refValueMax = refValueMax;
    }


    public String getParameter() {
        return parameter;
    }

    public String getResultValue() {
        return resultValue;
    }

    public String getResultMetric() {
        return resultMetric;
    }

    public String getRefValueMin() {
        return refValueMin;
    }

    public String getRefValueMetric() {
        return refValueMetric;
    }

    public String getRefValueMax() {
        return refValueMax;
    }
}
