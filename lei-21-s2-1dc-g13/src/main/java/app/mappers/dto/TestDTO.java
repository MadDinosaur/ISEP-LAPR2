package app.mappers.dto;

import app.domain.model.Category;
import app.domain.model.Client;
import app.domain.model.Parameter;
import app.domain.model.Report;
import app.domain.store.SampleList;
import app.domain.store.TestParamList;

import java.util.ArrayList;
import java.util.List;

public class TestDTO {

    private Client client;
    private List<Category> listOfCategories;
    private String testCode;
    private String testType;
    private String nhsCode;
    private String dateTimeRegister;
    private String dateTimeSamples;
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

    public TestDTO(Client client, List<Category> listOfCategories, String testCode, String testType, String nhsCode, String dateTimeRegister, String dateTimeSamples, List<Parameter> listOfParameters, TestParamList testParamList, SampleList sampleList, Report report, String dateTimeReport, String dateTimeResults, String dateTimeValidation) {
        this.client = client;
        this.listOfCategories = listOfCategories;
        this.testCode = testCode;
        this.testType = testType;
        this.nhsCode = nhsCode;
        this.dateTimeRegister = dateTimeRegister;
        this.listOfParameters = listOfParameters;
        this.testParamList = testParamList;
        this.sampleList = sampleList;
        this.report = report;
        this.dateTimeSamples = dateTimeSamples;
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

    public String getTestType() {
        return testType;
    }

    public String getNhsCode() {
        return nhsCode;
    }

    public String getDateTimeRegister() {
        return dateTimeRegister;
    }

    public String getDateTimeSamples() {
        return dateTimeSamples;
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
        return String.format("Date: %s, Test Type: %s, Test no.: %s, NHS no.: %s", dateTimeRegister, testType, testCode, nhsCode);
    }
}
