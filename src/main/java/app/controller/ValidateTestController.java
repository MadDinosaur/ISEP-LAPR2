package app.controller;

import app.domain.model.*;
import app.domain.store.*;

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

    public void toDTO(){

    }


}
