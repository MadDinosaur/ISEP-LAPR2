package app.domain.model;


import app.domain.model.Exceptions.TestAlreadyValidatedException;
import app.domain.store.SampleList;
import app.domain.store.TestParamList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

    /**
     * Instance of current client
     */
    private Client client;
    private List<Category> listOfCategories;
    private TestType testType;
    private String testCode;
    private String nhsCode;
    private Date dateOfCreation;
    private List<Parameter> listOfParameters = new ArrayList<>();
    private TestParamList testParamList;
    private SampleList sampleList = new SampleList();
    private StateOfTest stateOfTest;
    private Report report;
    private LocalDate dateReport;
    private LocalTime timeReport;
    private LocalDate dateResults;
    private LocalTime timeResults;
    private LocalDate dateValidation;
    private LocalTime timeValidation;

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
        setTestCode(testCode);
        setNhsCode(nhsCode);
        setListOfCategories(testType.getCategories());
        getParametersFromCategoriesToStore();
        stateOfTest = StateOfTest.REGISTERED;

        testParamList = new TestParamList(testType);
    }

    private void setClient(Client client) {
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
    public void setTestType(TestType testType) {
        this.testType = testType;
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

    public String getNhsCode() {
        return nhsCode;
    }

    public Date getDateOfCreation() {
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

    public LocalDate getDateReport() {
        return dateReport;
    }

    public LocalTime getTimeReport() {
        return timeReport;
    }

    public LocalDate getDateResults() {
        return dateResults;
    }

    public LocalTime getTimeResults() {
        return timeResults;
    }

    public LocalDate getDateValidation() {
        return dateValidation;
    }

    public LocalTime getTimeValidation() {
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

    public String getTestCode() {
        return this.testCode;
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

    public Boolean validateTest(){
        if(!stateOfTest.equals("VALIDATED")) {
            this.stateOfTest = StateOfTest.VALIDATED;
            this.dateValidation = getDate();
            this.timeValidation = getTime();
            return true;
        }else throw new TestAlreadyValidatedException("Test was already validated");
    }

    /**
     * Returns the current date
     * @return LocalDate
     */
    public LocalDate getDate() {
        return LocalDate.now();
    }

    /**
     * Returns the current time
     * @return LocalTime
     */
    public LocalTime getTime() {
        return LocalTime.now();
    }

    public void setStateOfTestToSamplesAnalyzed() {
        this.stateOfTest = StateOfTest.SAMPLES_ANALYZED;
    }

}

