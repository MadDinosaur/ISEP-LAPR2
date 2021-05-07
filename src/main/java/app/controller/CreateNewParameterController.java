package app.controller;

import app.domain.model.Category;
import app.domain.model.Company;
import app.domain.model.Parameter;

import java.util.ArrayList;

/**
 *
 * @author Diogo Gaspar <1200966@isep.ipp.pt>
 */
public class CreateNewParameterController {
    private Company company;
    private Category cat;
    private Parameter pc;


    public CreateParameterController() {
        this(App.getInstance().getCompany());
    }

    public CreateParameterController(Company company) {
        this.company = company;
        this.pc = null;
    }

    public boolean createParameter(String code, String description, String nhsId) {
        this.pc = this.
        return this.category.validateParameterCategory(pc);
    }
    public boolean saveParameterCategory() {
        return this.company.saveParameterCategory(pc);
    }

    public ArrayList<Category> getCategoryList(){
        ArrayList<Category> currentCategotyList;
        currentCategotyList = .getCategoryList();
        return currentCategotyList;
    }

}
