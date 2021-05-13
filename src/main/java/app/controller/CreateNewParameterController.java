package app.controller;

import app.domain.model.Category;
import app.domain.model.Company;
import app.domain.model.Parameter;

import java.util.Iterator;
import java.util.List;

public class CreateNewParameterController {


    /**
     * Instance of company
     */
    private Company company;

    /**
     * Instance of the parameter's category
     */
    private Category parameterCategory;

    /**
     * Instance of the parameter
     */
    private Parameter parameter;


    /**
     * Empty constructor of CreateNewParameterController class
     */
    public CreateNewParameterController() {
        this.company = (App.getInstance().getCompany());
        this.parameterCategory = null;
        this.parameter = null;
    }

    /**
     * Constructor of CreateNewParameterController class with a company parameter
     * @param company
     */
    public CreateNewParameterController(Company company) {
        this.company = company;
        this.parameterCategory = null;
        this.parameter = null;
    }

    /**
     * Setter for the parameter's category
     * @param pc
     */
    public void setCategory(Category pc){
        this.parameterCategory = pc;
    }

    /**
     * Method that creates the new parameter
     * @param shortName
     * @param code
     * @param description
     */
    public void createNewParameter(String shortName, String code, String description) {
        this.parameter = this.parameterCategory.createNewParameter(shortName, code, description);
    }

    /**
     * Method that save the created parameter
     * @return
     */
    public boolean saveParameter() {
        this.parameterCategory.saveParameter(parameter);
        return true;
    }

    /**
     * Getter for the parameter categories available in the system
     * @return the system's parameter category list
     */
    public List<Category> getCategoryList(){
        return company.getCategoryList();
    }

    /**
     * Getter for the parameter's category
     * @return
     */
    public Category getCategory(){
        return parameterCategory;
    }
}
