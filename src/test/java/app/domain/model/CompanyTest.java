package app.domain.model;

import app.domain.store.*;
import auth.AuthFacade;
import auth.domain.model.Email;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompanyTest {

    private final Company company = new Company("test Company");
    private final ClientStore clientStore= new ClientStore();
    private final EmployeeStore employeeStore = new EmployeeStore();
    private final Client client = new Client("test",(long)  8765432187654322.0,1234512345,new DateBirth(24,12,2002),1234512347,(long)12345123456.0,new Email("teste@gmail.com"),"male");
    private final String pss = "1234567890";

    private final SampleList sampleList = new SampleList();
    private final Sample sample1 = new Sample("123456789012");
    private final Sample sample2 = new Sample("111111111111");
    private final Sample sample3 = new Sample("123123123123");

    private final Category pc = null;
    private final Category pc1 = new Category("name", "12345", "description");

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
        Assert.assertEquals(testCode,"000000000005");
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
        Assert.assertNotNull(testStore);
    }

    @Test
    public void testValidateCategory() {
        Assert.assertFalse(company.validateCategory(pc));
        Assert.assertTrue(company.validateCategory(pc1));
    }

    @Test
    public void saveCategoryTest() {
        Assert.assertFalse(company.saveCategory(pc));
        Assert.assertTrue(company.saveCategory(pc1));
    }

    @Test
    public void authFacadeTest() {
       AuthFacade authFacade = company.getAuthFacade();
       Assert.assertNotNull(authFacade);
    }

    @Test
    public void createCategoryTest() {
        Category category = company.createCategory("name", "12345", "description");
        String name = category.getCategoryName();
        String code = category.getCategoryCode();
        String description = category.getCategoryDescription();

        Assert.assertEquals(name, "name");
        Assert.assertEquals(code, "12345");
        Assert.assertEquals(description, "description");
    }

    @Test
    public void companyGettersTest() {
        List<TestType> testTypeList = company.getTestTypeList();
        Assert.assertNotNull(testTypeList);

        List<Category> categoryList = company.getCategoryList();
        Assert.assertNotNull(categoryList);

        EmployeeStore employeeStore = company.getEmployeeStore();
        Assert.assertNotNull(employeeStore);

        ClientStore clientStore = company.getClientStore();
        Assert.assertNotNull(clientStore);

        OrgRoleStore orgRoleStore = company.getOrgRoleStore();
        Assert.assertNotNull(orgRoleStore);

        TestStore testStore = company.getTestStore();
        Assert.assertNotNull(testStore);

        ReportStore reportStore = company.getReportStore();
        Assert.assertNotNull(reportStore);

        SampleList sampleList = company.getSampleStore();
        Assert.assertNotNull(sampleList);
    }

}