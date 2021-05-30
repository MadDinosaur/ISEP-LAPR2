package app.controller;

import app.domain.model.*;
import app.domain.model.Exceptions.EmptyListException;
import app.domain.store.*;
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
     */
    public void getListTestsWithReport(){
        this.currTestStore = company.getTestStore();
        this.listTestsWithReport = currTestStore.getListTestsWithReport();
    }

    /**
     *
     * @return
     */
    public List<TestDTO> toDTO(){
        TestMapper testMapper = new TestMapper(listTestsWithReport);
        return testMapper.toDtoListValidation();
    }

    public void validateTest(String nhsCode){
        currTestStore.validateTest(nhsCode);
    }

    public void sendNotification(String nhsCode) throws IOException {
        company.sendNotification(currTestStore.findTestThroughNhsCode(nhsCode).getClient(), "Your results are now available in the " + company.getDesignation() + "'s applications.");
    }

}
