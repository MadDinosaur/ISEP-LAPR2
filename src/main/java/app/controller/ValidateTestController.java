package app.controller;

import app.domain.model.*;
import app.domain.model.Exceptions.EmptyListException;
import app.domain.store.*;
import app.mappers.TestMapper;
import app.mappers.dto.TestDTO;

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

    private List<TestDTO> listTestsWithReportDTO;

    /**
     * Empty constructor of ValidateTestController
     */
    public ValidateTestController() {
        this.company = (App.getInstance().getCompany());
        this.currTestStore = this.company.getTestStore();
    }

    /**
     * Constructor of ValidateTestController with one parameter
     * @param company
     */
    public ValidateTestController(Company company) {
        this.company = company;
        this.currTestStore = company.getTestStore();
    }

    public void getListTestsWithReport(){
        this.listTestsWithReport = currTestStore.getListTestsWithReport();
    }

    public List<TestDTO> toDTO(){
        if(!listTestsWithReport.equals(null)) {
            TestMapper testMapper = new TestMapper(listTestsWithReport);
            return testMapper.toDtoList();
        }else throw new EmptyListException("This list is empty!");
    }

    public Boolean validateTest(String nhsCode){
        return true;
    }


}
