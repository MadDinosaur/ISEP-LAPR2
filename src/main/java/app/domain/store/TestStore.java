package app.domain.store;

import app.domain.model.Client;
import app.domain.model.Exceptions.InvalidTestCodeException;
import app.domain.model.Exceptions.UnregisteredBarcodeException;
import app.domain.model.Parameter;
import app.domain.model.Report;
import app.domain.model.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestStore {
    private List<Test> tests = new ArrayList<>();

    public boolean addTest(Test test) {
        return this.tests.add(test);
    }

    public List<Test> getRegisteredTests() {
            List<Test> readyForCollection = new ArrayList<>();
            for (Test test : tests)
                if (test.isRegistered())
                    readyForCollection.add(test);

            return readyForCollection;
    }

    public List<Test> getTestsWithCollectedSamples() {
        List<Test> readyForAnalysis = new ArrayList<>();
        for (Test test : tests)
            if (test.hasCollectedSamples())
                readyForAnalysis.add(test);

        return readyForAnalysis;
    }

    /**
     * Returns a list of tests which are on the SAMPLES_ANALYZED state
     * @return List<Test>
     */
    public List<Test> getTestsListReadyForReport() {
        List<Test> readyForDiagnosis = new ArrayList<>();
        for (Test test : tests)
            if (test.hasAnalyzedSamples())
                readyForDiagnosis.add(test);

        return readyForDiagnosis;
    }

    public List<Test> getListTestsWithReport() {
        List<Test> readyForValidation = new ArrayList<>();
        for (Test test : tests)
            if (test.hasDiagnosis())
                readyForValidation.add(test);

        return readyForValidation;
    }

    public List<Test> getTestsValidated() {
        List<Test> validated = new ArrayList<>();
        for (Test test : tests)
            if (test.isValidated())
                validated.add(test);

        return validated;
    }

    public Test getTestBySampleBarcode(String barcode) {
        for (Test test : tests)
            if (test.getSampleList().existsSample(barcode))
                return test;

        throw new UnregisteredBarcodeException();
    }

    public List<Parameter> getTestParameters(Test test) {
        return test.getTestParamList().getParameters();
    }

    public boolean createTestParameterResult(Test test, String paramCode, String result, String metric) {
        return test.getTestParamList().createTestParameterResult(paramCode, result, metric);
    }

    /**
     * Returns a test by its code
     * @param testCode Test's code
     * @return Test
     */
    public Test getTestByCode(String testCode) {
        Test test1 = null;
        for (Test t : tests) {
            if (t.getTestCode().equals(testCode)) {
                test1 = t;
            }
        }
        if (test1 != null) {
            return test1;
        } else {
            throw new InvalidTestCodeException();
        }
    }

    /**
     * Saves the report in the given test
     * @param test Test
     * @param report Report
     */
    public void saveReport(Test test, Report report) {
        test.addReport(report);
    }

    public boolean validateTest(String nhsCode){
        Iterator<Test> testIterator = tests.iterator();
        Test test;
        while(testIterator.hasNext()){
            test = (Test) testIterator;
            if(test.getNhsCode() == nhsCode){
                test.validateTest();
            }
        }
        return true;
    }

}
