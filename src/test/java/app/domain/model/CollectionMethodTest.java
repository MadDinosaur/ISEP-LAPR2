package app.domain.model;

import app.domain.model.Exceptions.InvalidDescriptionException;
import org.junit.Assert;
import org.junit.Test;

public class CollectionMethodTest {

    @Test(expected = InvalidDescriptionException.class)
    public void testEmptyCollectionMethod() {
        CollectionMethod test = new CollectionMethod();
        test.setDescriptionCollectionMethod("");
    }

    @Test(expected = InvalidDescriptionException.class)
    public void testCollectionMethodWithMoreThan20Characters() {
        CollectionMethod test = new CollectionMethod();
        test.setDescriptionCollectionMethod("123456789123456789012");
    }

    @Test
    public void testIfDescriptionsAreEqual() {
        CollectionMethod cmR = new CollectionMethod("asdasd");
        CollectionMethod cmE = new CollectionMethod();
        cmE.setDescriptionCollectionMethod("asdasd");
        Assert.assertEquals(cmE.getDescriptionCollectionMethod(), cmR.getDescriptionCollectionMethod());
    }
}
