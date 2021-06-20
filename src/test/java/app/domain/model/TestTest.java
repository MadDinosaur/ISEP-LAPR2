package app.domain.model;

import app.domain.store.SampleList;
import app.domain.store.TestParamList;
import auth.domain.model.Email;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestTest{
    ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = new ClinicalAnalysisLaboratory();
    Client client1 = new Client("Teste",(long) 8765432187654321.0,1234512347,new DateBirth(24,12,2002),1234512345,(long)12345123457.0,new Email("teste50@gmail.com"),"male");
    CollectionMethod collectionMethodTest = new CollectionMethod("test Colection");
    private final List<Category> categoryList = new ArrayList<Category>(Collections.singleton(new Category("Hemograma", "pistola", "WBC", "toma")));
    TestType testTypeHardCoded = new TestType("TestCorreto","test Of Test",collectionMethodTest,categoryList);
    TestParamList testParamList = new TestParamList(testTypeHardCoded);
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

    @org.junit.Test
    public void gettersTest() {
        Test.StateOfTest stateOfTest = test1.getStateOfTest();
        Report report = test1.getReport();
        String dateTimeReport = test1.getDateTimeReport();
        String dateTimeResults = test1.getDateTimeResults();
        String dateTimeValidation = test1.getDateTimeValidation();

        Assert.assertEquals(stateOfTest, Test.StateOfTest.REGISTERED);
        Assert.assertNull(dateTimeReport);
        Assert.assertNull(dateTimeResults);
        Assert.assertNull(dateTimeValidation);
        Assert.assertNull(report);
    }

    @org.junit.Test
    public void constructorTest() {
        Test newTest = new Test(clinicalAnalysisLaboratory, client1, "123456789012", "123456789012",
                testTypeHardCoded, categoryList, testParamList, "12 maio", "13h00", "13h01", "13h49");
        Client client = newTest.getClient();
        String testCode = newTest.getTestCode();
        String nhsCode = newTest.getNhsCode();
        TestType testType = newTest.getTestType();
        List<Category> categoryList1 = newTest.getListOfCategories();
        TestParamList parameterList = newTest.getTestParamList();
        String dateTimeRegister = newTest.getDateTimeRegister();
        String dateTimeResults = newTest.getDateTimeResults();
        String dateTimeReport = newTest.getDateTimeReport();
        String dateTimeValidation = newTest.getDateTimeValidation();
        //
        Assert.assertEquals(client, client1);
        Assert.assertEquals(testCode, "123456789012");
        Assert.assertEquals(nhsCode, "123456789012");
        Assert.assertEquals(testType, testTypeHardCoded);
        Assert.assertEquals(parameterList, testParamList);
        Assert.assertEquals(dateTimeRegister, "12 maio");
        Assert.assertEquals(dateTimeResults, "13h00");
        Assert.assertEquals(dateTimeReport, "13h01");
        Assert.assertEquals(dateTimeValidation, "13h49");

    }

    @org.junit.Test
    public void setSampleListTest() {
        SampleList sampleList = new SampleList();
        Sample sample = new Sample("12345678901");
        sampleList.saveSample(sample);
        test1.setSampleList(sampleList);
        SampleList sampleList1 = test1.getSampleList();

        Assert.assertEquals(sampleList1, sampleList);
    }

}