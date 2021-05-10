import app.domain.model.CollectionMethod;
import app.domain.model.Exceptions.InvalidDescriptionException;
import org.junit.Assert;
import org.junit.Test;

public class CollectionMethodTests {

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

    @Test
    public void testIfDescriptionsAreEqual() {
        CollectionMethod cmR = new CollectionMethod("asdasd");
        CollectionMethod cmE = new CollectionMethod();
        cmE.setDescription("asdasd");
        Assert.assertEquals(cmE.getDescription(), cmR.getDescription());
    }
}
