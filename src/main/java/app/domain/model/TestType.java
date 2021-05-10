package app.domain.model;

import app.domain.model.Exceptions.InvalidCategoryException;
import app.domain.model.Exceptions.InvalidCodeException;
import app.domain.model.Exceptions.InvalidDescriptionException;

import java.util.ArrayList;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class TestType {

    private String code;
    private String description;
    private CollectionMethod collectionMethod;
    private ArrayList<Category> categories = new ArrayList<>();

    public TestType(String code) {
        this.code = code;
    }

    public TestType(String code, String description, CollectionMethod collectionMethod, ArrayList<Category> categories) {
        this.code = code;
        this.description = description;
        this.collectionMethod = collectionMethod;
        this.categories = categories;
    }

    public TestType() {

    }

    public void setCode(String code) {
        if (!validateCode(code)) {
            throw new InvalidCodeException();
        } else {
            this.code = code;
        }
    }

    public void setCollectionMethod(CollectionMethod collectionMethod) {
        this.collectionMethod = collectionMethod;
    }

    public void setCategory(Category category) {
        if (!validateCategories(category)) {
            throw new InvalidCategoryException();
        } else {
            this.categories.add(category);
        }
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestType testType = (TestType) o;
        return Objects.equals(code, testType.code) && Objects.equals(description, testType.description) && Objects.equals(collectionMethod, testType.collectionMethod) && Objects.equals(categories, testType.categories);
    }

    public boolean validateCode(String code) {
        if (code.equals("") || !StringUtils.isAlphanumeric(code) || code.length() > 5 || code.length() < 5) {
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



    public boolean validateCategories(Category category) {
        if (this.categories.contains(category)) {
            return false;
        } else {
            return true;
        }
    }
}
