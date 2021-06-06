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
    private String dateOfCreation;
    private List<Parameter> listOfParameters = new ArrayList<>();
    private TestParamList testParamList;
    private SampleList sampleList = new SampleList();
    private TestDTO.StateOfTest stateOfTest;
    private Report report;
    private String dateReport;
    private String timeReport;
    private String dateResults;
    private String timeResults;
    private String dateValidation;
    private String timeValidation;

    private enum StateOfTest{
        REGISTERED,
        SAMPLES_COLLECTED,
        SAMPLES_ANALYZED,
        DIAGNOSIS_MADE,
        VALIDATED
    }

    public TestDTO(Client client, List<Category> listOfCategories, String testCode, String nhsCode, String dateOfCreation, List<Parameter> listOfParameters, TestParamList testParamList, SampleList sampleList, Report report, String dateReport, String timeReport, String dateResults, String timeResults, String dateValidation, String timeValidation) {
        this.client = client;
        this.listOfCategories = listOfCategories;
        this.testCode = testCode;
        this.nhsCode = nhsCode;
        this.dateOfCreation = dateOfCreation;
        this.listOfParameters = listOfParameters;
        this.testParamList = testParamList;
        this.sampleList = sampleList;
        this.report = report;
        this.dateReport = dateReport;
        this.timeReport = timeReport;
        this.dateResults = dateResults;
        this.timeResults = timeResults;
        this.dateValidation = dateValidation;
        this.timeValidation = timeValidation;
    }

    public TestDTO(String nhsCode, String dateOfCreation, String dateReport, String timeReport, String dateResults, String timeResults) {
        this.nhsCode = nhsCode;
        this.dateOfCreation = dateOfCreation;
        this.dateReport = dateReport;
        this.timeReport = timeReport;
        this.dateResults = dateResults;
        this.timeResults = timeResults;
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

    public String getDateOfCreation() {
        return dateOfCreation;
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

    @Override
    public String toString() {
        return "dateOfCreation=" + dateOfCreation +
                ", dateReport=" + dateReport +
                ", timeReport=" + timeReport +
                ", dateResults=" + dateResults +
                ", timeResults=" + timeResults +
                ", dateValidation=" + dateValidation +
                ", timeValidation=" + timeValidation +
                '}';
    }
}
