package app.controller;

import app.domain.model.Category;
import app.domain.model.CollectionMethod;
import app.domain.model.Company;
import app.domain.model.TestType;

import java.util.List;

public class CreateTestTypeController {

    /**
     * Company
     */
    private Company company;

    /**
     * Test type
     */
    private TestType tt;

    /**
     * Collection method
     */
    private CollectionMethod cm;

    /**
     * Constructor for this controller
     */
    public CreateTestTypeController() {
        this.company = App.getInstance().getCompany();
    }


    /**
     * Creates a test type
     */
    public void createTestType() {
        this.tt = this.company.getTestTypeStore().createTestType();
    }


    /**
     * Creates a collection method
     */
    public void createCollectionMethod() {
        this.cm = this.company.getTestTypeStore().createCollectionMethod();
    }


    /**
     * Setter for the code
     * @param code code
     */
    public void setCode(String code) {
        this.tt.setCode(code);
    }

    /**
     * Setter for the collection method
     * @param description description
     */
    public void setCollectionMethod(String description) {
        this.cm.setDescriptionCollectionMethod(description);
    }


    /**
     * Setter for the collection method to the test type
     */
    public void setCollectionMethodToTestType() {
        this.tt.setCollectionMethod(this.cm);
    }

    /**
     * Saves the test type
     * @return true or false
     */
    public boolean saveTestType() {
        return this.company.getTestTypeStore().addTestType(tt);
    }

    /**
     * Displays the list of categories
     */
    public void displayCategoryList() {
        List<Category> list = this.company.getCategoryList();
        int index = 0;
        for (Category category : list) {
            System.out.printf("%s - %s\n", index, category.getCategoryName());
            index++;
        }
    }

    /**
     * Getter for the size of the category list
     * @return size of the category list
     */
    public int getCategoryListSize() {
        List<Category> list = this.company.getCategoryList();
        return list.size();
    }

    /**
     * Setter for the categories to the test type
     * @param index index
     */
    public void setCategoriesToTestType(int index) {
        this.tt.setCategory(this.company.getCategoryList().get(index));
    }

    /**
     * Setter for the description of the test type
     * @param description description
     */
    public void setDescription(String description) {
        this.tt.setDescription(description);
    }
}
