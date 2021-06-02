package app.mappers.dto;

import app.domain.model.*;
import app.domain.store.SampleList;
import app.domain.store.TestParamList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDTO {

    private Client client;
    private List<Category> listOfCategories;
    private String testCode;
    private String nhsCode;
    private LocalDate dateOfCreation;
    private List<Parameter> listOfParameters = new ArrayList<>();
    private TestParamList testParamList;
    private SampleList sampleList = new SampleList();
    private TestDTO.StateOfTest stateOfTest;
    private Report report;
    private LocalDate dateReport;
    private LocalTime timeReport;
    private LocalDate dateResults;
    private LocalTime timeResults;
    private LocalDate dateValidation;
    private LocalTime timeValidation;

    private enum StateOfTest{
        REGISTERED,
        SAMPLES_COLLECTED,
        SAMPLES_ANALYZED,
        DIAGNOSIS_MADE,
        VALIDATED
    }

    public TestDTO(Client client, List<Category> listOfCategories, String testCode, String nhsCode, LocalDate dateOfCreation, List<Parameter> listOfParameters, TestParamList testParamList, SampleList sampleList, Report report, LocalDate dateReport, LocalTime timeReport, LocalDate dateResults, LocalTime timeResults, LocalDate dateValidation, LocalTime timeValidation) {
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

    public TestDTO(String nhsCode, LocalDate dateOfCreation, LocalDate dateReport, LocalTime timeReport, LocalDate dateResults, LocalTime timeResults) {
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

    public LocalDate getDateOfCreation() {
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
