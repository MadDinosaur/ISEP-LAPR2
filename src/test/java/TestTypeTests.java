import app.domain.model.Exceptions.InvalidCodeException;
import app.domain.model.Exceptions.InvalidDescriptionException;
import app.domain.model.TestType;
import org.junit.Test;

public class TestTypeTests {

    @Test(expected = InvalidCodeException.class)
    public void testEmptyTestTypeCode() {
        TestType test = new TestType();
        test.setCode("");
    }

    @Test(expected = InvalidCodeException.class)
    public void testTestTypeCodeWithMoreThan5Characters() {
        TestType test = new TestType();
        test.setCode("yayaya");
    }

    @Test(expected = InvalidCodeException.class)
    public void testTestTypeCodeWithNonAlphanumericCharacters() {
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


}
