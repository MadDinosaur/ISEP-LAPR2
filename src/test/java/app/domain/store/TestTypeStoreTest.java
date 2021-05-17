package app.domain.store;

import app.domain.model.Category;
import app.domain.model.CollectionMethod;
import app.domain.model.Exceptions.InvalidTestType;
import app.domain.model.TestType;
import app.domain.store.TestTypeStore;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class TestTypeStoreTest {

    Category c1 = new Category("nome", "code", "description");
    ArrayList<Category> list = new ArrayList<>(Collections.singleton(c1));
    TestType tt = new TestType("12345", "description", new CollectionMethod("isso"), list);
    ArrayList<TestType> listTT = new ArrayList<>(Collections.singleton(tt));
    TestTypeStore tts = new TestTypeStore(listTT);

    @Test(expected = InvalidTestType.class)
    public void checkingIfATestTypeAlreadyExistsInTheTestTypeList() {
        tts.addTestType(new TestType("12345", "description", new CollectionMethod("isso"), list));
    }

}
