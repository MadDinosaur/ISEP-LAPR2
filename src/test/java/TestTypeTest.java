import app.domain.model.CollectionMethod;
import org.junit.Assert;
import org.junit.Test;

public class TestTypeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyCollectionMethod() {
        CollectionMethod test = new CollectionMethod();
        test.setDescription("");

    }
}
