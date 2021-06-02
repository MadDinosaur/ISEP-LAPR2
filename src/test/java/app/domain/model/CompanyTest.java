package app.domain.model;

import app.domain.model.Exceptions.InvalidTextReportException;
import app.domain.store.ClientStore;
import app.domain.store.EmployeeStore;
import app.domain.store.TestStore;
import auth.domain.model.Email;
import junit.framework.TestCase;
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

    



}