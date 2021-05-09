import app.domain.model.CollectionMethod;
import app.domain.model.Exceptions.InvalidCodeException;
import app.domain.model.Exceptions.InvalidDescriptionException;
import app.domain.model.TestType;
import org.junit.Test;

public class TestTypeTests {

    @Test(expected = InvalidCodeException.class)
    public void testEmptyTestTypeDesignation() {
        TestType test = new TestType();
        test.setCode("");
    }

    @Test(expected = InvalidCodeException.class)
    public void testTestTypeDesignationWithMoreThan5Characters() {
        TestType test = new TestType();
        test.setCode("yayaya");
    }

    @Test(expected = InvalidCodeException.class)
    public void testTestTypeDesignationWithNonAlphanumericCharacters() {
        TestType test = new TestType();
        test.setCode("asd!");
    }

    @Test(expected = InvalidDescriptionException.class)
    public void testEmptyTestTypeDescription() {
        TestType test = new TestType();
        test.setDescription("");
    }

    @Test(expected = InvalidDescriptionException.class)
    public void testTestTypeDescriptionWithMoreThan15Characters() {
        TestType test = new TestType();
        test.setDescription("abcdefghijklmnop");
    }

    @Test(expected = InvalidDescriptionException.class)
    public void testEmptyCollectionMethod() {
        CollectionMethod test = new CollectionMethod();
        test.setDescription("");
    }

    @Test(expected = InvalidDescriptionException.class)
    public void testCollectionMethodWithMoreThan20Characters() {
        CollectionMethod test = new CollectionMethod();
        test.setDescription("123456789123456789012");
    }

    }
