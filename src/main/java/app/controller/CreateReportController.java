package app.controller;

import app.domain.model.*;
import app.domain.store.ReportStore;
import app.domain.store.TestParamList;
import app.domain.store.TestStore;
import app.mappers.TestMapper;
import app.mappers.dto.ReportDTO;
import app.mappers.dto.TestDTO;

import java.util.ArrayList;
import java.util.List;

public class CreateReportController {

    private Company company;
    private Test test;
    private Report report;
    private TestStore testStore;
    private TestParamList testParamList;
    private ReportStore reportStore;

    public CreateReportController() {
        this.company = App.getInstance().getCompany();
        this.testStore = company.getTestStore();
        this.reportStore = company.getReportStore();
    }

    public List<TestDTO> getTestsListReadyForReport() {
        List<Test> testListReadyForReport =  company.getTestStore().getTestsListReadyForReport();
        TestMapper testMapper = new TestMapper(testListReadyForReport);
        return testMapper.toDtoList();
    }

    public List<TestParameterResult> getTestParametersResultsByCode(String testCode) {
        this.test = testStore.getTestByCode(testCode);
        testParamList = test.getTestParamList();
        return testParamList.getTestParametersResults();
    }

    public List<ReferenceValue> getTestParametersReferenceValues() {
        List<ReferenceValue> referenceValueList = new ArrayList<>();
        for (TestParameter testParameter : testParamList.getTestParameters()) {
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
