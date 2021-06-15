package app.domain.model;

import org.junit.Test;

public class CategoryTests {

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
}
