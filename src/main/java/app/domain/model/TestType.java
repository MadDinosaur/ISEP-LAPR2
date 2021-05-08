package app.domain.model;

import java.util.ArrayList;
import java.util.Objects;

public class TestType {

    private String designation;
    private CollectionMethod collectionMethod;
    private ArrayList<Category> categories;

    public TestType() {

    }

    public void setDesignation(String designation) {
        if (designation.equals("")) {
            throw new IllegalArgumentException("The designation introduced isn't valid.");
        } else {
            this.designation = designation;
        }
    }

    public void setCollectionMethod(CollectionMethod collectionMethod) {
        this.collectionMethod = collectionMethod;
    }

    public void setCategory(Category category) {
        this.categories.add(category);
    }

    public String getDesignation() {
        return designation;
    }

    public CollectionMethod getCollectionMethod() {
        return collectionMethod;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return (String.format("This test type's name is: %s\nIts collection method is the following: %s\nIt is under the following categories: %s", designation, collectionMethod, categories));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestType testType = (TestType) o;
        return Objects.equals(designation, testType.designation) && Objects.equals(collectionMethod, testType.collectionMethod) && Objects.equals(categories, testType.categories);
    }
}
