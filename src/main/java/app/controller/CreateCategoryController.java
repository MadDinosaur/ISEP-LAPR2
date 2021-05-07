package app.controller;

import app.domain.model.Category;
import app.domain.model.Company;

public class CreateCategoryController {

    private Company company;
    private Category pc;

    public CreateCategoryController() {
        this(App.getInstance().getCompany());
    }

    public CreateCategoryController(Company company) {
        this.company = company;
        this.pc = null;
    }
    public boolean createCategory(String code, String description, String nhsId) {
        this.pc = this.company.createParameterCategory(code, description, nhsId);
        return this.company.validateParameterCategory(pc);
    }
    public boolean saveCategory() {
        return this.company.saveParameterCategory(pc);
    }
}