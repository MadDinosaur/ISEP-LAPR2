package app.DTO;

import app.domain.model.Category;
import app.domain.model.CollectionMethod;

import java.util.ArrayList;
import java.util.List;

public class TestTypeDto {
    private String code;
    private String description;
    private CollectionMethod collectionMethod;
    private List<Category> categories = new ArrayList<>();

    public TestTypeDto(String code, String description, CollectionMethod collectionMethod, List<Category> categories) {
        this.code = code;
        this.description = description;
        this.collectionMethod = collectionMethod;
        this.categories = categories;
    }
    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public CollectionMethod getCollectionMethod() {
        return collectionMethod;
    }
    public List<Category> getCategories() {
        return categories;
    }
}
