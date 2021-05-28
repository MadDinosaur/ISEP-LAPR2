package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.Test;
import app.mappers.ParamMapper;
import app.mappers.dto.ParamDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RecordTestResultController {
    Company company;
    Test test;

    public RecordTestResultController(Company company) {
        this.company = company;
    }

    public List<String> getTestsWithCollectedSamples() {
        return company.getTestStore().getTestsWithCollectedSamples()
                .stream()
                .map(Test::toString)
                .collect(Collectors.toList());
    }

    public List<ParamDTO> getTestParameters(String barcode) {
        int convertedBarcode;

        try { convertedBarcode = Integer.parseInt(barcode); }
        catch (NumberFormatException ex) {
            throw new NumberFormatException("Barcode must be a number!");
        }

        test = company.getTestStore().getTestBySampleBarcode(convertedBarcode);

        ParamMapper map = new ParamMapper();
        return map.toDTO(company.getTestStore().getTestParameters(test));
    }

    public boolean createTestParameterResult(String paramCode, String result, String metric) {
        return company.getTestStore().createTestParameterResult(test, paramCode, result, metric);
    }

    public boolean saveTestParameterResult() {
        System.out.println("Not implemented yet");//not implemented yet
        return false;
    }
}
