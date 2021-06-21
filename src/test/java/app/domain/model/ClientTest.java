package app.domain.model;

import app.mappers.dto.ClientDTO;
import auth.domain.model.Email;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientTest {
    String name = "testClient";
    long cardNumber = 8765432187654321L;
    long nhsId = 1234512347;
    DateBirth dateBirth = new DateBirth(24,12,2002);
    long tin = 1234512345;
    long phonenumber = 12345123457L;
    Email email = new Email("testClient@gmail.com");
    String gender = "male";
    TestType testType = new TestType();
    String testCode = "123456789012";
    String nhsCode = "123456789012";
    protected String password = "password100";
    Client testClient = new Client(name, cardNumber, nhsId, dateBirth, tin, phonenumber, email, gender);
    app.domain.model.Test test = new app.domain.model.Test(testClient, testType, testCode, nhsCode);
    List<app.domain.model.Test> clientTest = new ArrayList<>(Collections.singleton(test));

    @Test
    public void gettersTest() {
        String name1 = testClient.getName();
        long cardNumber1 = testClient.getCardNumber();
        long nhsId1 = testClient.getNhsId();
        DateBirth dateBirth1 = testClient.getDateBirth();
        long tin1 = testClient.getTIN();
        long phonenumber1 = testClient.getPhoneNumber();
        Email email1 = testClient.getEmail();
        String gender1 = testClient.getSex();
        testClient.addTestToClient(test);
        List<app.domain.model.Test> clientTest1 = testClient.getClientTests();

        Assert.assertEquals(name, name1);
        Assert.assertEquals(cardNumber, cardNumber1);
        Assert.assertEquals(nhsId, nhsId1);
        Assert.assertEquals(dateBirth, dateBirth1);
        Assert.assertEquals(tin, tin1);
        Assert.assertEquals(phonenumber, phonenumber1);
        Assert.assertEquals(email, email1);
        Assert.assertEquals(gender, gender1);
        //Assert.assertEquals(clientTest, clientTest1);
    }

    @Test
    public void compareTest() {
        int tinResult = testClient.compareToTin(testClient);
        int nameResult = testClient.compareToName(testClient);
        Assert.assertEquals(tinResult, 0);
        Assert.assertEquals(nameResult, 0);
    }

    @Test
    public void DateBirthTest() {
        DateBirth dateBirth = new DateBirth(1, 10, 2002);
        testClient.setDateBirth(dateBirth);
        DateBirth dateBirth1 = testClient.getDateBirth();
        int age = testClient.getAgeInYears();

        Assert.assertEquals(dateBirth, dateBirth1);
        Assert.assertEquals(age, 19);
    }

    @Test
    public void updateDataTest() {
        DateBirth datebirth123 = new DateBirth(24,12,2002);
        String datebirthString = datebirth123.toString();
        ClientDTO clientDTO = new ClientDTO(name, "8765432187654321", "1234512347", datebirthString, "1234512345",
                "12345123457", "testClient@gmail.com", password, gender);
        testClient.updateData(clientDTO);

        String name1 = testClient.getName();
        long cardNumber1 = testClient.getCardNumber();
        long nhsId1 = testClient.getNhsId();
        DateBirth dateBirth1 = testClient.getDateBirth();
        String dateBirth1String = dateBirth1.toString();
        long tin1 = testClient.getTIN();
        long phonenumber1 = testClient.getPhoneNumber();
        Email email1 = testClient.getEmail();
        String gender1 = testClient.getSex();
        testClient.addTestToClient(test);
        List<app.domain.model.Test> clientTest1 = testClient.getClientTests();

        Assert.assertEquals(name, name1);
        Assert.assertEquals(cardNumber, cardNumber1);
        Assert.assertEquals(nhsId, nhsId1);
        Assert.assertEquals(datebirthString, dateBirth1String);
        Assert.assertEquals(tin, tin1);
        Assert.assertEquals(phonenumber, phonenumber1);
        Assert.assertEquals(email, email1);
        Assert.assertEquals(gender, gender1);
        //Assert.assertEquals(clientTest, clientTest1);
    }
}
