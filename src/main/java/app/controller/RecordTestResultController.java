package app.controller;

import app.domain.model.*;
import app.mappers.ParamMapper;
import app.mappers.dto.ParamDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RecordTestResultController {
    Company company;
    Test test;
    Parameter param;
    TestParameterResult result;

    public RecordTestResultController(Company company) {
        this.company = company;
    }

    public List<String> getTestsWithCollectedSamples() {
        return company.getTestStore().getTestsWithCollectedSamples()
                .stream()
                .map(Test::toStringWithSamples)
                .collect(Collectors.toList());
    }

    public List<ParamDTO> getTestParameters(String barcode) {
        test = company.getTestStore().getTestBySampleBarcode(barcode);

        ParamMapper map = new ParamMapper();
        return map.toDto(company.getTestStore().getTestParameters(test));
    }

    public void createTestParameterResult(String paramCode, String result, String metric) {
        param = test.getTestParamList().getTestParameter(paramCode).getParameter();
        this.result = company.getTestStore().createTestParameterResult(test, paramCode, result, metric);
    }

    public boolean saveTestParameterResult() {
        return test.saveTestParameterResult(param, result);
    }
}
