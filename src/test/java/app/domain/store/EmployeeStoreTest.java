package app.domain.store;

import app.domain.model.Employee;
import app.domain.model.Exceptions.InvalidEmployeeException;
import app.domain.model.OrganizationRole;
import app.domain.store.EmployeeStore;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeStoreTest {
    EmployeeStore es = new EmployeeStore();

    String validId = "AMSM00021";
    String validName = "Ana Maria Santos Moura";
    String validAddress = "Rua das Flores 12";
    String validPhoneNumber = "123456789";
    String validEmail = "amsm@manylabs.com";
    String validSocCode = "1234";
    String validDIN = "123456";
    OrganizationRole validRole = new OrganizationRole("any", "any");

    @Test (expected = IllegalArgumentException.class)
    public void generateEmployeeIdNull() {
        es.generateEmployeeId(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void generateEmployeeIdEmpty() {
        es.generateEmployeeId("");
    }

    @Test
    public void generateEmployeeIdBlank() {
        String expected = "00001";
        String actual = es.generateEmployeeId(" ");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void generateEmployeeIdValid() {
        String expected = "AMSM00001";
        String actual = es.generateEmployeeId(validName);
        Assert.assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void saveEmployeeNull() {
        es.saveEmployee(null);
    }

    @Test
    public void saveEmployeeDuplicate() {
        boolean exceptionFlag;

        Employee e = new Employee(validId, validRole, validName, validAddress, validEmail, validPhoneNumber, validSocCode);
        Assert.assertTrue(es.saveEmployee(e));
        //Same Employee
        try {exceptionFlag = false; es.saveEmployee(e);} catch (InvalidEmployeeException ex) {exceptionFlag = true;}
        Assert.assertTrue(exceptionFlag);

        //Same e-mail
        Employee e2 = new Employee("another id", validRole, "another name", "another address", validEmail, "987654321", "4321");
        try {exceptionFlag = false; es.saveEmployee(e2);} catch (InvalidEmployeeException ex) {exceptionFlag = true;}
        Assert.assertTrue(exceptionFlag);

        //Same phone number
        Employee e3 = new Employee("another id", validRole, "another name", "another address", "another@email.com", validPhoneNumber, "4321");
        try {exceptionFlag = false; es.saveEmployee(e3);} catch (InvalidEmployeeException ex) {exceptionFlag = true;}
        Assert.assertTrue(exceptionFlag);

        //Same name and address
        Employee e4 = new Employee("another id", validRole, validName, validAddress, "another@email.com", "987654321", "4321");
        try {exceptionFlag = false; es.saveEmployee(e4);} catch (InvalidEmployeeException ex) {exceptionFlag = true;}
        Assert.assertTrue(exceptionFlag);

        //Different Employee
        Employee e5 = new Employee("another id", validRole, "another name", "another address", "another@email.com", "987654321", "4321");
        Assert.assertTrue(es.saveEmployee(e5));
    }
}