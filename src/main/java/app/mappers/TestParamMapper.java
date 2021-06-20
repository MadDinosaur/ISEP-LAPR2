package app.mappers;

import app.domain.model.Test;
import app.domain.model.TestParameter;
import app.mappers.dto.TestParameterDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestParamMapper {
    private List<TestParameter> testParams;

    public TestParamMapper(List<TestParameter> testParams) {
        this.testParams = testParams;
    }

    public TestParameterDTO toDTO(TestParameter testParameter) {
        String parameter = "NA", resultValue = "NA", resultMetric = "NA", refValueMin = "NA", refValueMetric = "NA", refValueMax = "NA";

        if (testParameter.getParameter() != null)
        parameter = testParameter.getParameter().getParameterName();

        if (testParameter.getResult() != null) {
            resultValue = ""+ testParameter.getResult().getValue();
            resultMetric = testParameter.getResult().getMetric();
        }

        if (testParameter.getReferenceValue() != null) {
            refValueMin = testParameter.getReferenceValue().getMinValue().toString();
            refValueMetric = testParameter.getReferenceValue().getMetric();
            refValueMax = testParameter.getReferenceValue().getMaxValue().toString();
        }

        return new TestParameterDTO(parameter, resultValue, resultMetric, refValueMin, resultMetric, refValueMax);
    }

    public List<TestParameterDTO> toDTOList() {
        List<TestParameterDTO> listDTO = new ArrayList<>();

        for (TestParameter testParam : testParams)
            listDTO.add(toDTO(testParam));

        return listDTO;
    }
}
