package app.domain.store;

import app.domain.model.*;
import auth.domain.model.Email;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestStoreTest {
    TestStore testStore = new TestStore();
    Client client1 = new Client("Teste",(long) 8765432187654321.0,1234512347,new DateBirth(24,12,2002),1234512345,(long)12345123457.0,new Email("teste50@gmail.com"),"male");
    CollectionMethod collectionMethodTest = new CollectionMethod("test Colection");
    private List<Category> categoryList = new ArrayList<Category>(Collections.singleton(new Category("Hemograma", "pistola", "WBC", "toma")));
    TestType testTypeHardCoded = new TestType("TestCorreto","test Of Test",collectionMethodTest,categoryList);
    Test test1 = new Test(client1,testTypeHardCoded,"123456789012","123456789012");


    @org.junit.Test
    public void checkNhsCode(){
        testStore.addTest(test1);
        boolean actual = testStore.validadeTestCode("123456789012");
        Assert.assertFalse(actual);
    }

}