package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class CategoryTest {
    String categoryName = "name";
    String categoryCode = "12345";
    String categoryDescription = "Category description";
    String categoryNhsId = "nhsId";
    Category category = new Category(categoryName, categoryCode, categoryDescription);
    Category category1 = new Category(categoryName, categoryCode, categoryDescription, categoryNhsId);

    @Test(expected = IllegalArgumentException.class)
    public void testNullNameCategory(){
        String categoryName = "";
        String categoryCode = "12345";
        String categoryDescription = "Category description";
        Category cat = new Category(categoryName, categoryCode, categoryDescription);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNulCodeCategory(){
        String categoryName = "Hemogram";
        String categoryCode = "";
        String categoryDescription = "Category description";
        Category cat = new Category(categoryName, categoryCode, categoryDescription);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullDescriptionCategory(){
        String categoryName = "Hemogram";
        String categoryCode = "12345";
        String categoryDescription = "";
        Category cat = new Category(categoryName, categoryCode, categoryDescription);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLongNameCategory(){
        String categoryName = "1234567898765432";
        String categoryCode = "12345";
        String categoryDescription = "Category description";
        Category cat = new Category(categoryName, categoryCode, categoryDescription);
    }

    @Test
    public void createNewParameterTest() {
        Parameter parameter = category.createNewParameter("name", "12123", "description");
        String name = parameter.getParameterName();
        String code = parameter.getParameterCode();
        String description = parameter.getParameterDescription();

        Assert.assertEquals(name, "name");
        Assert.assertEquals(code, "12123");
        Assert.assertEquals(description, "description");
    }

    @Test
    public void getCategoryNhsId() {
        String nhsId = category1.getCategoryNhsId();
        Assert.assertEquals(nhsId, categoryNhsId);
    }

    @Test
    public void toStringTest() {
        String toString = category1.toString();
        String actualString = "Category{name: name; code: 12345; description: Category description; nhs id: nhsId}";
        Assert.assertEquals(toString, actualString);
    }

}
