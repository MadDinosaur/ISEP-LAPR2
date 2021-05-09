package app.controller;

import app.domain.model.Category;
import app.domain.model.Company;
import app.domain.model.Parameter;

import java.util.Iterator;
import java.util.List;

public class CreateNewParameterController {

    private Company company;
    private Category pc;
    private Parameter par;
    private List<Category> categoryList;

    public CreateNewParameterController() {
        this.company = (App.getInstance().getCompany());
        this.pc = null;
        this.par = null;
        this.categoryList = company.getCategoryList();
    }

    public CreateNewParameterController(Company company) {
        this.company = company;
        this.pc = null;
        this.par = null;
        this.categoryList = this.company.getCategoryList();
    }

    public void setCategory(Category pc){
        this.pc = pc;
    }

    public void createNewParameter(String shortName, String code, String description) {
        this.par = this.pc.createNewParameter(shortName, code, description);
    }

    public boolean saveParameter() {
        this.pc.saveParameter(par);
        return true;
    }

    public List<Category> getCategoryList(){
        return company.getCategoryList();
    }

    public Category getCategory(){
        return pc;
    }
}
