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
        this(App.getInstance().getCompany());
    }

    public CreateNewParameterController(Company company) {
        this.company = company;
        this.pc = null;
        this.par = null;
        this.categoryList = company.getCategoryList();
    }

    public void setCategory(Category pc){
        this.pc = pc;
    }

    public boolean createNewParameter(String shortName, String code, String description) {
        this.par = this.pc.createNewParameter(shortName, code, description);
        return this.pc.validateParameter(par);
    }

    public boolean saveParameter() {
        return this.pc.saveParameter(par);
    }

    public List<Category> getCategoryList(){
        return company.getCategoryList();
    }

    public String getCategoryName() {
        Iterator<Category> iterator = company.getCategoryList().iterator();
        while (iterator.hasNext()) {
            Category cat = iterator.next();
            return cat.getName();
        }
        return null;
    }

    public Category getCategory(){
        return pc;
    }
}
