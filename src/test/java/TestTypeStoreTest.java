import app.domain.model.CollectionMethod;
import app.domain.model.Exceptions.InvalidTestType;
import app.domain.model.TestType;
import app.domain.store.TestTypeStore;
import org.junit.Assert;
import org.junit.Test;

public class TestTypeStoreTest {

    @Test(expected = InvalidTestType.class)
    public void checkingIfATestTypeAlreadyExistsInTheTestTypeList() {
        TestTypeStore tts = new TestTypeStore();
        tts.addTestType(new TestType("12345"));
        tts.validateTestType(new TestType("12345"));
    }

    @Test
    public void checkingIfACollectionMethodIsCreatedNull() {
        TestTypeStore tts = new TestTypeStore();
        CollectionMethod cmR = tts.createCollectionMethod();
        CollectionMethod cmE = new CollectionMethod();
        Assert.assertEquals(cmR, cmE);
    }
}
