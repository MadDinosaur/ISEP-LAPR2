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
    
}
