package app.mappers.dto;

import app.domain.model.*;
import app.domain.store.SampleList;
import app.domain.store.TestParamList;

import java.util.ArrayList;
import java.util.List;

public class TestDTO {

    private Client client;
    private List<Category> listOfCategories;
    private String testCode;
    private String nhsCode;
    private String dateTimeRegister;
    private List<Parameter> listOfParameters = new ArrayList<>();
    private TestParamList testParamList;
    private SampleList sampleList = new SampleList();
    private TestDTO.StateOfTest stateOfTest;
    private Report report;
    private String dateTimeReport;
    private String dateTimeResults;
    private String dateTimeValidation;

    private enum StateOfTest{
        REGISTERED,
        SAMPLES_COLLECTED,
        SAMPLES_ANALYZED,
        DIAGNOSIS_MADE,
        VALIDATED
    }

    public TestDTO(Client client, List<Category> listOfCategories, String testCode, String nhsCode, String dateTimeRegister, List<Parameter> listOfParameters, TestParamList testParamList, SampleList sampleList, Report report, String dateTimeReport, String dateTimeResults, String dateTimeValidation) {
        this.client = client;
        this.listOfCategories = listOfCategories;
        this.testCode = testCode;
        this.nhsCode = nhsCode;
        this.dateTimeRegister = dateTimeRegister;
        this.listOfParameters = listOfParameters;
        this.testParamList = testParamList;
        this.sampleList = sampleList;
        this.report = report;
        this.dateTimeReport = dateTimeReport;
        this.dateTimeResults = dateTimeResults;
        this.dateTimeValidation = dateTimeValidation;
    }

    public TestDTO(String nhsCode, String dateTimeRegister, String dateTimeReport, String dateTimeResults) {
        this.nhsCode = nhsCode;
        this.dateTimeRegister = dateTimeRegister;
        this.dateTimeReport = dateTimeReport;
        this.dateTimeResults = dateTimeResults;
    }

    public Client getClient() {
        return client;
    }

    public List<Category> getListOfCategories() {
        return listOfCategories;
    }

    public String getTestCode() {
        return testCode;
    }

    public String getNhsCode() {
        return nhsCode;
    }

    public String getDateTimeRegister() {
        return dateTimeRegister;
    }

    public List<Parameter> getListOfParameters() {
        return listOfParameters;
    }

    public TestParamList getTestParamList() {
        return testParamList;
    }

    public SampleList getSampleList() {
        return sampleList;
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

    @Override
    public String toString() {
        return "dateOfCreation=" + dateTimeRegister +
                ", dateTimeReport=" + dateTimeReport +
                ", dateTimeResults=" + dateTimeResults +
                ", dateTimeValidation=" + dateTimeValidation +
                '}';
    }
}
