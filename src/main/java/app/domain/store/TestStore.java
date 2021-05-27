package app.domain.store;

import app.domain.model.Exceptions.UnregisteredBarcodeException;
import app.domain.model.Parameter;
import app.domain.model.Test;
import app.domain.model.TestParameter;

import java.util.ArrayList;
import java.util.List;

public class TestStore {
    private List<Test> tests;

    public List<Test> getRegisteredTests() {
        List<Test> readyForCollection = new ArrayList<>();
        for (Test test: tests)
            if (test.isRegistered())
                readyForCollection.add(test);

        return readyForCollection;
    }

    public List<Test> getTestsWithCollectedSamples() {
        List<Test> readyForAnalysis = new ArrayList<>();
        for (Test test: tests)
            if (test.hasCollectedSamples())
                readyForAnalysis.add(test);

        return readyForAnalysis;
    }

    public List<Test> getTestsWithAnalysedSamples() {
        List<Test> readyForDiagnosis = new ArrayList<>();
        for (Test test: tests)
            if (test.hasAnalyzedSamples())
                readyForDiagnosis.add(test);

        return readyForDiagnosis;
    }

    public List<Test> getListTestsWithReport() {
        List<Test> readyForValidation = new ArrayList<>();
        for (Test test: tests)
            if (test.hasDiagnosis())
                readyForValidation.add(test);

        return readyForValidation;
    }

    public List<Test> getTestsValidated() {
        List<Test> validated = new ArrayList<>();
        for (Test test: tests)
            if (test.isValidated())
                validated.add(test);

        return validated;
    }

    public Test getTestBySampleBarcode(int barcode) {
        for (Test test: tests)
            if(test.getSampleStore().existsSample(barcode))
                return test;

        throw new UnregisteredBarcodeException();
    }

    public List<Parameter> getTestParameters(Test test) {
        return test.getTestParamStore().getParameters();
    }

    public boolean createTestParameterResult(Test test, String paramCode, String result, String metric) {
        return test.getTestParamStore().createTestParameterResult(paramCode, result, metric);
    }
}
