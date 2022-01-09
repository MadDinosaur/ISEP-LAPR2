package app.controller;

import app.domain.model.Company;
import app.domain.model.exceptions.EmptyListException;
import app.domain.model.Test;
import app.domain.store.TestStore;
import app.mappers.TestMapper;
import app.mappers.dto.TestDTO;

import java.io.IOException;
import java.util.List;

public class ValidateTestController {

    /**
     * Instance of current company
     */
    private Company company;

    /**
     *
     */
    private TestStore currTestStore;

    /**
     * List of tests with report currently available
     */
    private List<Test> listTestsWithReport;

    /**
     * Empty constructor of ValidateTestController
     */
    public ValidateTestController() {
        this.company = (App.getInstance().getCompany());
    }

    /**
     * Constructor of ValidateTestController with one parameter
     * @param company
     */
    public ValidateTestController(Company company) {
        this.company = company;
    }

    /**
     * Method that gets the list of test that are ready to validate
     * @return
     */
    public List<Test> getListTestsWithReport(){
        this.currTestStore = company.getTestStore();
        if(!currTestStore.getListTestsWithReport().isEmpty()) {
            return currTestStore.getListTestsWithReport();
        }else throw new EmptyListException();
    }

    /**
     *
     * @return
     */
    public List<TestDTO> toDTO() {
        List<TestDTO> listTestsWithReportDTO = null;
        if (listTestsWithReport != null){
            TestMapper testMapper = new TestMapper(listTestsWithReport);
            for(Test test : listTestsWithReport) {
                listTestsWithReportDTO.add(testMapper.toDTO(test));
            }
        }else throw new EmptyListException("This list is empty!");
        return listTestsWithReportDTO;
    }

    public void validateTest(String nhsCode){
        currTestStore.validateTest(nhsCode);
    }

    public void sendNotification(String nhsCode) throws IOException {
        company.sendNotification(currTestStore.findTestThroughNhsCode(nhsCode).getClient(), "Your results are now available in the " + company.getDesignation() + "'s applications.");
    }

}