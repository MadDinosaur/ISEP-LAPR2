package app.domain.model;


import app.domain.model.Exceptions.TestAlreadyValidatedException;
import app.domain.store.SampleList;
import app.domain.store.TestParamList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private Client client;
    private List<Category> listOfCategories;
    private final List<Parameter> listOfParameters = new ArrayList<>();
    private TestType testType;
    private String testCode;
    private String nhsCode;
    private TestParamList testParamList;
    private SampleList sampleList = new SampleList();
    private StateOfTest stateOfTest;
    private Report report;
    private final String dateTimeRegister;
    private String dateTimeReport;
    private String dateTimeResults;
    private String dateTimeValidation;

    /**
     * State of the test
     */
    enum StateOfTest{
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
        dateTimeRegister = getDateTime();
    }

    public Test(ClinicalAnalysisLaboratory clinicalAnalysisLaboratory, Client client, String testCode, String nhsCode, TestType testType, List<Category> categoryList, TestParamList testParamList, String dateTimeRegister, String dateTimeResults, String dateTimeReport, String dateTimeValidation) {
        /**
         * Instance of current client
         */
        this.client = client;
        this.testCode = testCode;
        this.nhsCode = nhsCode;
        this.testType = testType;
        this.listOfCategories = categoryList;
        this.testParamList = testParamList;
        this.dateTimeRegister = dateTimeRegister;
        this.dateTimeResults = dateTimeResults;
        this.dateTimeReport = dateTimeReport;
        this.dateTimeValidation = dateTimeValidation;
        this.stateOfTest = StateOfTest.VALIDATED;
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

    public String getDateTimeRegister() {
        return dateTimeRegister;
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

    public String getDateTimeReport() {
        return dateTimeReport;
    }

    public String getDateTimeResults() {
        return dateTimeResults;
    }


    public String getDateTimeValidation() {
        return dateTimeValidation;
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

    public List<TestParameterResult> getTestParamResults() {
        return testParamList.getTestParametersResults();
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
        this.dateTimeReport = getDateTime();
    }

    /**
     * Validates test
     */
    public void validateTest(){
        if(!stateOfTest.equals(StateOfTest.VALIDATED)) {
            this.stateOfTest = StateOfTest.VALIDATED;
            this.dateTimeValidation = getDateTime();
        }else throw new TestAlreadyValidatedException("Test was already validated");
    }

    /**
     * Returns the current date
     * @return LocalDate
     */
    public static String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(dtf);
    }

    /**
     * Returns the current time
     * @return LocalTime
     */

    public void setStateOfTestToSamplesAnalyzed() {
        this.stateOfTest = StateOfTest.SAMPLES_ANALYZED;
        this.dateTimeResults = getDateTime();
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
        return String.format("registration date: " + dateTimeRegister + "; results date: " + dateTimeResults + "; diagnosis date: " + dateTimeReport + ".");
    }


}



