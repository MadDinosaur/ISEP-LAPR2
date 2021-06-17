package app.domain.store;

import app.domain.model.*;
import app.domain.model.Exceptions.InvalidTestCodeException;
import app.domain.model.Exceptions.InvalidTestException;
import app.domain.model.Exceptions.TestDoesntExistException;
import app.domain.model.Exceptions.UnregisteredBarcodeException;

import java.util.ArrayList;
import java.util.List;

public class TestStore {
    private final List<Test> tests = new ArrayList<>();

    public boolean addTest(Test test) {
        validateTest(test);
        return this.tests.add(test);
    }

    public boolean validateTest(Test test1) {
        for (Test test2 : tests) {
            if (test1.equals(test2)) {
                throw new InvalidTestException();
            }
        }
            return true;
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

    public List<Test> getListPositiveCovid(){
        List<Test> positiveCovid = new ArrayList<>();
        for (Test test : tests){
            if (test.isValidated()){
                if(test.getTestType().getCode().equals("Covid")){
                    for (TestParameterResult parameterResult: test.getTestParamResults()) {
                        if(parameterResult.getValue() > 1.4){
                            positiveCovid.add(test);
                        }

                    }
                }
            }
        }
        return  positiveCovid;
    }
    public int getNumberOfPositiveCasesPerDay(String dayOfTest){
        List<Test> listOfPositiveTests = getListPositiveCovid();
        int numberOfCases=0;
        for (Test test : listOfPositiveTests){
            String date = test.getDateTimeRegister();
            String[] dateComponents = date.split(" ");
            date = dateComponents[0];
            if(dayOfTest.equalsIgnoreCase(date)){
                numberOfCases++;
            }
        }
        return numberOfCases;
    }

    public double getNumberOfTestsPerformed(String dayOfTest){
        double numberOfTestsMade = 0;
        for (Test test : tests){
            String date = test.getDateTimeRegister();
            String[] dateComponents = date.split(" ");
            date = dateComponents[0];
            if(dayOfTest.equalsIgnoreCase(date)){
                numberOfTestsMade++;
            }
        }
        return numberOfTestsMade;
    }
    public double getAverageAgeOfClient(String dayOfTest){
        double sumAge = 0;
        int numberOfClient = 0;
        for (Test test : tests){
            String date = test.getDateTimeRegister();
            String[] dateComponents = date.split(" ");
            date = dateComponents[0];
            if(dayOfTest.equalsIgnoreCase(date)){
                sumAge = test.getClient().getAgeInYears();
                numberOfClient++;
            }
        }
        return sumAge/numberOfClient;
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

    public TestParameterResult createTestParameterResult(Test test, String paramCode, String result, String metric) {
        return test.createTestParameterResult(paramCode, result, metric);
    }

    /**
     * Returns a test by its code
     * @param testCode Test's code
     * @return Test
     */
    public Test getTestByCode(String testCode) {
        for (Test t : tests) {
            if (t.getTestCode().equals(testCode)) {
                return t;
            }
        }
        throw new InvalidTestCodeException();
    }

    /**
     * Saves the report in the given test
     * @param test Test
     * @param report Report
     */
    public void saveReport(Test test, Report report) {
        test.addReport(report);
    }

    public void validateTest(String nhsCode){
        for(Test test: tests){
            if (test.getNhsCode().equals(nhsCode)) {
                test.validateTest();
            }
        }
    }

    public boolean validadeTestCode(String code){
        for (Test test : tests){
            if (test.getNhsCode().equals(code)){
                return false;
            }
        }
        return true;
    }

    public Test findTestThroughNhsCode(String nhsCode){
        for(Test test: tests){
            if(test.getNhsCode().equals(nhsCode)){
                return test;
            }
        } throw new TestDoesntExistException();
    }

    public List<Test> getTestsByClient(Client client) {
        List<Test> clientTests = new ArrayList<>();
        for (Test test : tests)
            if(test.getClient().equals(client))
                clientTests.add(test);
        return clientTests;
    }

    public List<Test> getValidatedTestsByClient(Client client) {
        List<Test> clientTests = new ArrayList<>();
        for (Test test : tests)
            if(test.getClient().equals(client) && test.isValidated())
                clientTests.add(test);
        return clientTests;
    }

    public int getNumberOfTestsWaitingForResults() {
        return getRegisteredTests().size();
    }

    public int getNumberOfTestsWaitingForReport() {
        return getTestsListReadyForReport().size();
    }
}
