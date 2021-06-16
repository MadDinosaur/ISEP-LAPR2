package app.controller;

import app.domain.model.Company;
import app.domain.model.Report;
import app.domain.model.Test;
import app.domain.model.TestParameter;
import app.domain.store.ReportStore;
import app.domain.store.TestParamList;
import app.domain.store.TestStore;
import app.mappers.TestMapper;
import app.mappers.dto.ReportDTO;
import app.mappers.dto.TestDTO;

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

    /**
     * Returns the list of tests ready for a report
     * @return List<TestDTO>
     */
    public List<TestDTO> getTestsListReadyForReport() {
        List<Test> testListReadyForReport = company.getTestStore().getTestsListReadyForReport();
        TestMapper testMapper = new TestMapper(testListReadyForReport);
        return testMapper.toDtoList();
    }

    /**
     * Returns a list of parameters associated to the respective test
     * @param testCode Test's code
     * @return List<TestParameter>
     */
    public List<TestParameter> getTestParametersByCode(String testCode) {
        this.test = testStore.getTestByCode(testCode);
        testParamList = test.getTestParamList();
        return testParamList.getTestParameters();
    }

    /**
     * Creates a report with a report DTO
     * @param reportDTO reportDTO
     */
    public void createReport(ReportDTO reportDTO) {
        this.report = reportStore.createReport(reportDTO);
    }

    /**
     * Saves the report to the test
     */
    public void saveReport() {
        this.testStore.saveReport(this.test, this.report);
    }

}
