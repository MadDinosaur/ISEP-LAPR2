package app.controller;

import app.domain.model.*;
import app.domain.store.ReportStore;
import app.domain.store.TestParamStore;
import app.domain.store.TestStore;
import app.mappers.TestListReadyForReportMapper;
import app.mappers.dto.ReportDTO;
import app.mappers.dto.TestListReadyForReportDTO;

import java.util.ArrayList;
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

    public List<ReferenceValue> getTestParametersReferenceValues() {
        List<ReferenceValue> referenceValueList = new ArrayList<>();
        for (TestParameter testParameter : testParamStore.getTestParameters()) {
            referenceValueList.add(testParameter.getReferenceValue());
        }
        return referenceValueList;
    }

    public void createReport(ReportDTO reportDTO) {
        this.report = reportStore.createReport(reportDTO);
    }

    public void saveReport() {
        this.testStore.saveReport(this.test, this.report);
    }

}
