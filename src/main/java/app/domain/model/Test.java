package app.domain.model;


import app.domain.model.Exceptions.TestAlreadyValidatedException;
import app.domain.store.SampleList;
import app.domain.store.TestParamList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Test {

    /**
     * Instance of current client
     */
    private Client client;
    private List<Category> listOfCategories;
    private final List<Parameter> listOfParameters = new ArrayList<>();
    private TestType testType;
    private String testCode;
    private String nhsCode;
    private String dateOfCreation;
    private TestParamList testParamList;
    private SampleList sampleList = new SampleList();
    private StateOfTest stateOfTest;
    private Report report;
    private String dateReport;
    private String timeReport;
    private String dateResults;
    private String timeResults;
    private String dateValidation;
    private String timeValidation;

    /**
     * State of the test
     */
    private enum StateOfTest{
        REGISTERED,
        SAMPLES_COLLECTED,
        SAMPLES_ANALYZED,
        REPORT_MADE,
        VALIDATED
    }

    /**
     * Creates a new instance of test
     * @param client Client to register test
     * @param testType Categories of tests to register
     * @param testCode Test Code of the Test
     * @param nhsCode Nhs Code of the Test
     */
    public Test(Client client, TestType testType,String testCode, String nhsCode){
        setClient(client);
        setTestType(testType);
        setTestParameters();
        setTestCode(testCode);
        setNhsCode(nhsCode);
        setListOfCategories(testType.getCategories());
        getParametersFromCategoriesToStore();
        stateOfTest = StateOfTest.REGISTERED;
        dateOfCreation = getDate();
    }

    /**
     * Sets the client and verifies if it is null
     */
    private void setClient(Client client) {
        if (client==null) {
            throw new IllegalArgumentException("Client is errored");
        }
        this.client = client;
    }

    private void setListOfCategories(List<Category> listOfCategories) {
        this.listOfCategories = listOfCategories;
    }
    private void setTestCode(String testCode) {
        this.testCode = testCode;
    }
    private void setNhsCode(String nhsCode) {
        this.nhsCode = nhsCode;
    }
    private void setTestType(TestType testType) {
        this.testType = testType;
    }

    /**
     * sets a samples list associated to a test, and then changes test' state to "SAMPLES_COLLECTED"
     * @param sampleList list of collected samples
     */
    public void setSampleList(SampleList sampleList) {
        this.sampleList = sampleList;
        stateOfTest = StateOfTest.SAMPLES_COLLECTED;
    }

    /**
     * Returns a Test Parameter List
     * @return TestParamList
     */
    public TestParamList getTestParamList() {
        return testParamList;
    }

    public SampleList getSampleList() {
        return sampleList;
    }

    public Client getClient() {
        return client;
    }

    public List<Category> getListOfCategories() {
        return listOfCategories;
    }

    public TestType getTestType() { return testType; }

    public String getNhsCode() {
        return nhsCode;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public List<Parameter> getListOfParameters() {
        return listOfParameters;
    }

    public StateOfTest getStateOfTest() {
        return stateOfTest;
    }

    public Report getReport() {
        return report;
    }

    public String getDateReport() {
        return dateReport;
    }

    public String getTimeReport() {
        return timeReport;
    }

    public String getDateResults() {
        return dateResults;
    }

    public String getTimeResults() {
        return timeResults;
    }

    public String getDateValidation() {
        return dateValidation;
    }

    public String getTimeValidation() {
        return timeValidation;
    }

    public boolean isRegistered() {
        return stateOfTest.equals(StateOfTest.REGISTERED);
    }

    public boolean hasCollectedSamples() {
        return stateOfTest.equals(StateOfTest.SAMPLES_COLLECTED);
    }

    public boolean hasAnalyzedSamples() {
        return stateOfTest.equals(StateOfTest.SAMPLES_ANALYZED);
    }

    public boolean hasDiagnosis() {
        return stateOfTest.equals(StateOfTest.REPORT_MADE);
    }

    public boolean isValidated() {
        return stateOfTest.equals(StateOfTest.VALIDATED);
    }

    private void getParametersFromCategoriesToStore(){
        for(int i = 0; i<this.listOfCategories.size();i++){
            Category category = listOfCategories.get(i);
            List<Parameter> listOfParametersOfCategory = category.getParameterList();
            for (int j = 0; j<listOfParametersOfCategory.size(); j++) {
                Parameter parameter = listOfParametersOfCategory.get(i);
                listOfParameters.add(parameter);
            }
        }
    }

    public Parameter getParameter(String paramCode) {
        for (Parameter param: listOfParameters)
            if (param.getParameterCode().equalsIgnoreCase(paramCode))
                return param;

        return null;
    }

    public String getTestCode() {
        return this.testCode;
    }
    private void setTestParameters() {
        this.testParamList = new TestParamList(testType);
        for(Parameter parameter: testType.getParameters())
            testParamList.createTestParam(parameter);
    }

    /**
     * Adds a report into the test
     * @param report Report
     */
    public void addReport(Report report) {
        this.report = report;
        this.stateOfTest = StateOfTest.REPORT_MADE;
        this.dateReport = getDate();
        this.timeReport = getTime();
    }

    /**
     * Validates test
     */
    public void validateTest(){
        if(!stateOfTest.equals(StateOfTest.VALIDATED)) {
            this.stateOfTest = StateOfTest.VALIDATED;
            this.dateValidation = getDate();
            this.timeValidation = getTime();
        }else throw new TestAlreadyValidatedException("Test was already validated");
    }

    /**
     * Returns the current date
     * @return LocalDate
     */
    public String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.now().format(dtf);
    }

    /**
     * Returns the current time
     * @return LocalTime
     */
    public String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.now().format(dtf);
    }

    public void setStateOfTestToSamplesAnalyzed() {
        this.stateOfTest = StateOfTest.SAMPLES_ANALYZED;
        this.timeResults = getTime();
        this.dateResults = getDate();
    }

    public void setStateOfTestToSamplesCollected() {
        this.stateOfTest = StateOfTest.SAMPLES_COLLECTED;
    }

    public void setStateOfTestToValidated(){
        this.stateOfTest = StateOfTest.VALIDATED;
    }

    public TestParameterResult createTestParameterResult(String paramCode, String result, String metric) {
        return testParamList.createTestParameterResult(paramCode, result, metric);
    }

    public boolean saveTestParameterResult(Parameter param, TestParameterResult result) {
        if (!testParamList.addTestParameterResult(param, result))
            return false;

        this.stateOfTest = StateOfTest.SAMPLES_ANALYZED;

        return true;
    }

    @Override
    public String toString() {
        return String.format("Test no. %s of type %s | Client: %s | Status %s\n", testCode, testType.getCode(), client.getName(), stateOfTest.toString());
    }

    public String toStringWithSamples() {
        StringBuilder samplesToString = new StringBuilder();
        for (Sample sample: getSampleList().getSampleList())
            samplesToString.append(sample + "    ");

        return String.format("%sSamples Collected no.: %s\n" , toString(), samplesToString.toString());
    }

    public String toStringWithDates(){
        return String.format("registration date: " + dateOfCreation + "; results date: " + dateResults + "/" + timeResults + "; diagnosis date: " + dateReport + "/" + timeReport + ".");
    }


}



