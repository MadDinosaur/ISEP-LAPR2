package app.domain.model;

import auth.domain.model.Email;
import junit.framework.TestCase;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestTest{
    Client client1 = new Client("Teste",(long) 8765432187654321.0,1234512347,new DateBirth(24,12,2002),1234512345,(long)12345123457.0,new Email("teste50@gmail.com"),"male");
    CollectionMethod collectionMethodTest = new CollectionMethod("test Colection");
    private List<Category> categoryList = new ArrayList<Category>(Collections.singleton(new Category("Hemograma", "pistola", "WBC", "toma")));
    TestType testTypeHardCoded = new TestType("TestCorreto","test Of Test",collectionMethodTest,categoryList);
    Test test1 = new Test(client1,testTypeHardCoded,"123456789012","123456789012");

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        Test test = new Test(null, null,null,null);
    }

    @org.junit.Test
    public void getClientTest() throws NoSuchFieldException, IllegalAccessException {
        Client clientTest = test1.getClient();
        Assert.assertEquals(clientTest,client1);
    }

    @org.junit.Test
    public void getListOfCategoriesTest(){
        List<Category> listTest = test1.getListOfCategories();
        Assert.assertEquals(listTest,categoryList);
    }

    @org.junit.Test
    public void getTestType(){
        TestType testTypeTest = test1.getTestType();
        Assert.assertEquals(testTypeTest,testTypeHardCoded);
    }

    @org.junit.Test
    public void getNhsCodeTest(){
        String code = test1.getNhsCode();
        Assert.assertEquals("123456789012",code);
    }

    @org.junit.Test
    public void getTestCodeTest(){
        String testCode = test1.getTestCode();
        Assert.assertEquals("123456789012",testCode);
    }

    @org.junit.Test
    public void getTestIsValidatedTest(){
        test1.setStateOfTestToValidated();
        Assert.assertTrue(test1.isValidated());
    }

    @org.junit.Test
    public void getTestIsNotValidatedTest(){
        test1.setStateOfTestToSamplesAnalyzed();
        Assert.assertFalse(test1.isValidated());
    }




}