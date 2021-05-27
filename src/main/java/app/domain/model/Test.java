package app.domain.model;


import app.domain.store.SampleStore;
import app.domain.store.TestParamStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    private Client client;
    private List<Category> listOfCategories;
    private String testCode;
    private String nhsCode;
    private Date dateOfCreation;
    private List<Parameter> listOfParameters = new ArrayList<>();
    private TestParamStore testParamStore;
    private SampleStore sampleStore = new SampleStore();
    private StateOfTest stateOfTest;
    private Report report;

    private enum StateOfTest{
        REGISTERED,
        SAMPLES_COLLECTED,
        SAMPLES_ANALYZED,
        REPORT_MADE,
        VALIDATED
    }

    public Test(Client client, List<Category> listOfCategories,String testCode, String nhsCode){
        setClient(client);
        setListOfCategories(listOfCategories);
        setTestCode(testCode);
        setNhsCode(nhsCode);
        getParametersFromCategoriesToStore();
        stateOfTest = StateOfTest.REGISTERED;
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

    public TestParamStore getTestParamStore() {
        return testParamStore;
    }

    public SampleStore getSampleStore() {
        return sampleStore;
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
                //a testParamStore não tem metodo de add para a store ainda
            }
        }
    }

    public String getTestCode() {
        return this.testCode;
    }

    public void addReport(Report report) {
        this.report = report;
        this.stateOfTest = StateOfTest.REPORT_MADE;
    }

}

