package app.mappers.dto;

import app.domain.model.Category;
import app.domain.model.Client;
import app.domain.model.Parameter;
import app.domain.store.SampleList;
import app.domain.store.TestParamList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDTO {

    private Client client;
    private List<Category> listOfCategories;
    private String testCode;
    private String nhsCode;
    private Date dateOfCreation;
    private List<Parameter> listOfParameters = new ArrayList<>();
    private TestParamList testParamList;
    private SampleList sampleList = new SampleList();
    private TestDTO.StateOfTest stateOfTest;

    private enum StateOfTest{
        REGISTERED,
        SAMPLES_COLLECTED,
        SAMPLES_ANALYZED,
        DIAGNOSIS_MADE,
        VALIDATED
    }

    public TestDTO() {
        this.client = null;
        this.listOfCategories = null;
        this.testCode = null;
        this.nhsCode = null;
        this.dateOfCreation = null;
        this.listOfParameters = null;
        this.testParamList = null;
        this.sampleList = null;
        this.stateOfTest = null;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Category> getListOfCategories() {
        return listOfCategories;
    }

    public void setListOfCategories(List<Category> listOfCategories) {
        this.listOfCategories = listOfCategories;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getNhsCode() {
        return nhsCode;
    }

    public void setNhsCode(String nhsCode) {
        this.nhsCode = nhsCode;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public List<Parameter> getListOfParameters() {
        return listOfParameters;
    }

    public void setListOfParameters(List<Parameter> listOfParameters) {
        this.listOfParameters = listOfParameters;
    }

    public TestParamList getTestParamStore() {
        return testParamList;
    }

    public void setTestParamStore(TestParamList testParamStore) {
        this.testParamList = testParamStore;
    }

    public SampleList getSampleStore() {
        return sampleList;
    }

    public void setSampleStore(SampleList sampleStore) {
        this.sampleList = sampleStore;
    }

    public StateOfTest getStateOfTest() {
        return stateOfTest;
    }

    public void setStateOfTest(StateOfTest stateOfTest) {
        this.stateOfTest = stateOfTest;
    }
}
