package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.Test;

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

    public List<String> getTestParameters(String barcode) {
        int convertedBarcode;

        try { convertedBarcode = Integer.parseInt(barcode); }
        catch (NumberFormatException ex) {
            throw new NumberFormatException("Barcode must be a number!");
        }

        test = company.getTestStore().getTestBySampleBarcode(convertedBarcode);
        return company.getTestStore().getTestParameters(test)
                .stream()
                .map(Parameter::toString)
                .collect(Collectors.toList());
    }

    public boolean createTestParameterResult(String paramCode, String result, String metric) {
        return company.getTestStore().createTestParameterResult(test, paramCode, result, metric);
    }

    public boolean saveTestParameterResult() {
        System.out.println("Not implemented yet");//not implemented yet
        return false;
    }
}
