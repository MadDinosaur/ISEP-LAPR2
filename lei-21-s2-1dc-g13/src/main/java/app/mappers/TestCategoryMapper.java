package app.mappers;

import app.domain.model.Category;
import app.mappers.dto.TestCategoryDto;

import java.util.ArrayList;
import java.util.List;

public class TestCategoryMapper {

    public TestCategoryDto toDto(Category category){
        return new TestCategoryDto(category);
    }
    public List<TestCategoryDto> toDto(List<Category> categories){
        List<TestCategoryDto> testCategoryDtos = new ArrayList<>();
        for (Category category : categories){
            testCategoryDtos.add(this.toDto(category));
        }
        return testCategoryDtos;
    }
}
