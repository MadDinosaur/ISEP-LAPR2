package app.controller;

import app.domain.model.Category;
import app.domain.model.CollectionMethod;
import app.domain.model.Company;
import app.domain.model.TestType;

import java.util.List;

public class CreateTestTypeController {
    private Company company;
    private TestType tt;
    private CollectionMethod cm;


    public void createTestType() {
        this.tt = this.company.getTestTypeStore().createTestType();
    }

    public void createCollectionMethod() {
        this.cm = this.company.getTestTypeStore().createCollectionMethod();
    }

    public void setDesignation(String designation) {
        this.tt.setDesignation(designation);
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

    public void setCategoriesToTestType(int index) {
        this.tt.setCategory(this.company.getCategoryList().get(index));
    }

}
