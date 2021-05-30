package app.domain.model;

import app.domain.store.ClientStore;
import app.domain.store.EmployeeStore;
import app.domain.store.TestStore;
import auth.domain.model.Email;
import junit.framework.TestCase;
import org.junit.Assert;

public class CompanyTest extends TestCase {

    private ClientStore clientStore= new ClientStore();
    private EmployeeStore employeeStore = new EmployeeStore();
    private Client client = new Client("test",(long)  8765432187654322.0,1234512345,new DateBirth(24,12,2002),1234512347,(long)12345123456.0,new Email("teste@gmail.com"),"male");
    private String pss = "1234567890";
    private Employee employee = new Employee("testE",new OrganizationRole("oi","teste"),"testeEmp","house","testea@gmai.com","12345678912","1234");

/*    public void testSaveEmployeeAsUser() {
        employeeStore.saveEmployee(employee);
        Assert.assertEquals(employee.getName(),employeeStore.);
    }*/

    public void testSaveClientAsUser() {
        clientStore.saveClient(client);
        Client client2 = clientStore.getClientByCardNumber( (long)8765432187654322.0);
        Assert.assertEquals(client,client2);
    }
}