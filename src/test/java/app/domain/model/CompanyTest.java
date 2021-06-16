package app.domain.model;

import app.domain.store.ClientStore;
import app.domain.store.EmployeeStore;
import app.domain.store.SampleList;
import app.domain.store.TestTypeStore;
import auth.domain.model.Email;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompanyTest {

    private Company company = new Company("test Company");
    private ClientStore clientStore= new ClientStore();
    private EmployeeStore employeeStore = new EmployeeStore();
    private Client client = new Client("test",(long)  8765432187654322.0,1234512345,new DateBirth(24,12,2002),1234512347,(long)12345123456.0,new Email("teste@gmail.com"),"male");
    private String pss = "1234567890";

    private SampleList sampleList = new SampleList();
    private Sample sample1 = new Sample("123456789012");
    private Sample sample2 = new Sample("111111111111");
    private Sample sample3 = new Sample("123123123123");


    @Test
    public void testSaveClientAsUser() {
        clientStore.saveClient(client);
        Client client2 = clientStore.getClientByCardNumber( (long)8765432187654322.0);
        Assert.assertEquals(client,client2);
    }

    @Test
    public void testGeneratePassword(){
        String password = company.generateUserPassword();
        Assert.assertEquals(password.length(),10);
    }

    @Test
    public void testTestNumberGeneretor(){
        Assert.assertEquals(company.generateNhsCodeGenerator().length(),12);
    }
    @Test
    public void testNhsNumberGeneretor(){
        String testCode = company.generateNumberTest();
        Assert.assertEquals(testCode,"000000000004");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompanyBlack(){
        new Company(" ");
    }


    @Test
    public void testGetDesignation() {
        String designation = company.getDesignation();
        String actualDesignation = "test Company";
        Assert.assertEquals(designation, actualDesignation);
    }

    @Test
    public void testGetTestTypeStore() {
        CollectionMethod collectionMethodTest = new CollectionMethod("test Colection");
        Category categoryTest = new Category("Hemograma", "pistola", "WBC", "toma");
        List<Category> categoryList = new ArrayList<Category>(Collections.singleton(categoryTest));
        TestType t1 = new TestType("name", "code", collectionMethodTest, categoryList);
        TestTypeStore testStore = company.getTestTypeStore();
    }

    @Test
    public void testValidateCategory() {
        Category pc = null;
        Assert.assertFalse(company.validateCategory(pc));
    }
}