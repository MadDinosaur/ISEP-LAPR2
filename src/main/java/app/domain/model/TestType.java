package app.domain.model;

import app.domain.model.Exceptions.InvalidCodeException;
import app.domain.model.Exceptions.InvalidDescriptionException;

import java.util.ArrayList;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class TestType {

    private String description;
    private String code;
    private CollectionMethod collectionMethod;
    private ArrayList<Category> categories;

    public TestType() {

    }

    public void setCode(String designation) {
        if (!validateCode(designation)) {
            throw new InvalidCodeException("The designation introduced isn't valid.");
        } else {
            this.code = designation;
        }
    }

    public void setCollectionMethod(CollectionMethod collectionMethod) {
        this.collectionMethod = collectionMethod;
    }

    public void setCategory(Category category) {
        this.categories.add(category);
    }

    public String getCode() {
        return code;
    }

    public CollectionMethod getCollectionMethod() {
        return collectionMethod;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return (String.format("This test type's name is: %s\nIts collection method is the following: %s\nIt is under the following categories: %s", code, collectionMethod, categories));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestType testType = (TestType) o;
        return Objects.equals(code, testType.code) && Objects.equals(collectionMethod, testType.collectionMethod) && Objects.equals(categories, testType.categories);
    }

    public boolean validateCode(String designation) {
        if (designation.equals("") || !StringUtils.isAlphanumeric(designation) || designation.length() > 5) {
            return false;
        } else {
            return true;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (!validateDescription(description)) {
            throw new InvalidDescriptionException();
        } else {
            this.description = description;
        }
    }

    public boolean validateDescription(String description) {
        if(description.equals("") || description.length() > 15) {
            return false;
        } else {
            return true;
        }
    }
}
