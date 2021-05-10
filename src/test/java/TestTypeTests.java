import app.domain.model.Category;
import app.domain.model.CollectionMethod;
import app.domain.model.Exceptions.InvalidCodeException;
import app.domain.model.Exceptions.InvalidDescriptionException;
import app.domain.model.TestType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class TestTypeTests {
    Category c1 = new Category("nome", "code", "description");
    ArrayList<Category> list = new ArrayList<>(Collections.singleton(c1));
    TestType tt = new TestType("code", "description", new CollectionMethod("isso"), list);

    @Test(expected = InvalidCodeException.class)
    public void testEmptyTestTypeCode() {
        tt.setCode("");
    }

    @Test(expected = InvalidCodeException.class)
    public void testTestTypeCodeWithMoreThan5Characters() {
        tt.setCode("yayaya");
    }

    @Test(expected = InvalidCodeException.class)
    public void testTestTypeCodeWithNonAlphanumericCharacters() {
        tt.setCode("asd!");
    }

    @Test(expected = InvalidDescriptionException.class)
    public void testEmptyTestTypeDescription() {
        tt.setDescription("");
    }

    @Test(expected = InvalidDescriptionException.class)
    public void testTestTypeDescriptionWithMoreThan15Characters() {
        tt.setDescription("abcdefghijklmnop");
    }

    @Test
    public void testSetCollectionMethod() {
        TestType tt1 = new TestType();
        tt1.setCollectionMethod(new CollectionMethod("isso"));
        Assert.assertEquals(tt1.getCollectionMethod().getDescription(), tt.getCollectionMethod().getDescription());
    }

}
