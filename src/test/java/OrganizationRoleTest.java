import app.domain.model.Employee;
import app.domain.model.Exceptions.InvalidEmployeeException;
import app.domain.model.OrganizationRole;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrganizationRoleTest {
    OrganizationRole orgRoleTest = new OrganizationRole("any", "any");
    OrganizationRole orgRoleTest_SD = new OrganizationRole("sd", "Specialist Doctor");

    String validId = "AMSM00021";
    String validName = "Ana Maria Santos Moura";
    String validAddress = "Rua das Flores 12";
    String validPhoneNumber = "123456789";
    String validEmail = "amsm@manylabs.com";
    String validSocCode = "1234";
    String validDIN = "123456";

    @Test (expected = IllegalArgumentException.class)
    public void createEmployeeNull() {
        orgRoleTest.createEmployee(null, null, null, null, null, null,null);
    }
    @Test (expected = IllegalArgumentException.class)
    public void createEmployeeInvalidName() {
        orgRoleTest.createEmployee(validId, "", validAddress, validPhoneNumber, validEmail, validSocCode);
    }
    @Test (expected = IllegalArgumentException.class)
    public void createEmployeeInvalidAddress() {
        orgRoleTest.createEmployee(validId, validName, "", validPhoneNumber, validEmail, validSocCode);
    }
    @Test (expected = IllegalArgumentException.class)
    public void createEmployeeInvalidPhoneNumber() {
        orgRoleTest.createEmployee(validId, validName, validAddress, "1" , validEmail, validSocCode);
    }
    @Test (expected = IllegalArgumentException.class)
    public void createEmployeeInvalidEmail() {
        orgRoleTest.createEmployee(validId, validName, validAddress, validPhoneNumber, "email", validSocCode);
    }
    @Test (expected = IllegalArgumentException.class)
    public void createEmployeeInvalidSocCode() {
        orgRoleTest.createEmployee(validId, validName, validAddress, validPhoneNumber, validEmail, "1");
    }
    @Test (expected = IllegalArgumentException.class)
    public void createEmployeeInvalidDIN() {
        orgRoleTest_SD.createEmployee(validId, validName, validAddress, validPhoneNumber, validEmail, validSocCode);
        orgRoleTest_SD.createEmployee(validId, validName, validAddress, validPhoneNumber, validEmail, validSocCode, "1");
    }
    @Test
    public void createEmployeeValid() {
        orgRoleTest.createEmployee(validId, validName, validAddress, validPhoneNumber, validEmail, validSocCode);
        orgRoleTest_SD.createEmployee(validId, validName, validAddress, validPhoneNumber, validEmail, validSocCode, validDIN);
    }
}