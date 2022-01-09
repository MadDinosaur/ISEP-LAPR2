package app.mappers.dto;

import app.domain.model.Category;

public class TestCategoryDto {
    private String name;

    public TestCategoryDto(Category name){
        this.name = name.getCategoryName();
    }

    public String getName() {
        return name;
    }
}
