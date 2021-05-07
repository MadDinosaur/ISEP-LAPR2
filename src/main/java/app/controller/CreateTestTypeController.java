package app.controller;

import app.domain.model.Category;
import app.domain.model.CollectionMethod;
import app.domain.model.Company;
import app.domain.model.TestType;
import app.domain.store.TestTypeStore;

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
    
    public void setCollectionMethodToTestType(CollectionMethod cm) {
        this.tt.setCollectionMethod(cm);
    }

    public boolean saveTestType() {
        return this.company.getTestTypeStore().addTestType(tt);
    }

    public void displayCategoryList() { //chamar o método do UI para dar display à categorylist

    }

    public void setCategoriesToTestType(Category category) {
        this.tt.setCategory(category);
    }

}
