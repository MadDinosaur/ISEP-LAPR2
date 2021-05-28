package app.controller;

import app.domain.model.TestParameterResult;
import app.domain.store.ReportStore;
import app.domain.store.TestParamStore;
import app.domain.store.TestStore;
import app.mappers.TestListReadyForReportMapper;
import app.mappers.dto.ReportDTO;
import app.domain.model.Company;
import app.domain.model.Report;
import app.domain.model.Test;
import app.mappers.dto.TestListReadyForReportDTO;

import java.util.List;

public class CreateReportController {

    private Company company;
    private Test test;
    private Report report;
    private TestStore testStore = company.getTestStore();
    private TestParamStore testParamStore = test.getTestParamStore();
    private ReportStore reportStore = company.getReportStore();

    public TestListReadyForReportDTO getTestsListReadyForReport() {
        List<Test> testListReadyForReport =  company.getTestStore().getTestsListReadyForReport();
        TestListReadyForReportMapper testListReadyForReportMapper = new TestListReadyForReportMapper();
        return testListReadyForReportMapper.toDTO(testListReadyForReport);
    }

    public List<TestParameterResult> getTestParametersResultsByCode(String testCode) {
        this.test = testStore.getTestByCode(testCode);
        return testParamStore.getTestParameterResults();
    }

    public void createReport(ReportDTO reportDTO) {
        this.report = reportStore.createReport(reportDTO);
    }

    public void saveReport() {

    }

}
