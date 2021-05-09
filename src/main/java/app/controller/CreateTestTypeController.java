package app.controller;

import app.domain.model.Category;
import app.domain.model.CollectionMethod;
import app.domain.model.Company;
import app.domain.model.Exceptions.InvalidCategoryException;
import app.domain.model.TestType;

import java.util.List;

public class CreateTestTypeController {
    private Company company;
    private TestType tt;
    private CollectionMethod cm;

    public CreateTestTypeController() {
        this.company = App.getInstance().getCompany();
    }


    public void createTestType() {
        this.tt = this.company.getTestTypeStore().createTestType();
    }

    public void createCollectionMethod() {
        this.cm = this.company.getTestTypeStore().createCollectionMethod();
    }

    public void setCode(String code) {
        this.tt.setCode(code);
    }

    public void setCollectionMethod(String description) {
        this.cm.setDescription(description);
    }

    public void setCollectionMethodToTestType() {
        this.tt.setCollectionMethod(this.cm);
    }

    public boolean saveTestType() {
        return this.company.getTestTypeStore().addTestType(tt);
    }

    public void displayCategoryList() {
        List<Category> list = this.company.getCategoryList();
        int index = 0;
        for (Category category : list) {
            System.out.printf("%s - %s\n", index, category.getDescription());
            index++;
        }
    }

    public int getCategoryListSize() {
        List<Category> list = this.company.getCategoryList();
        return list.size();
    }

    public void setCategoriesToTestType(int index) {
        this.tt.setCategory(this.company.getCategoryList().get(index));
    }


    public void setDescription(String description) {
        this.tt.setDescription(description);
    }
}
