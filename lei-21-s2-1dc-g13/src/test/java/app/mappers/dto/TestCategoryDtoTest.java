package app.mappers.dto;

import app.domain.model.Category;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class TestCategoryDtoTest extends TestCase {
    Category categoryTest = new Category("Hemogram", "HEM00", "Hemogram Description", "toma");

    TestCategoryDto testCategoryDto = new TestCategoryDto(categoryTest);

    @Test
    public void testTestGetName() {
        Assert.assertEquals(testCategoryDto.getName(),"Hemogram");
    }
}