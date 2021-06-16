package app.domain.model;

import auth.domain.model.Email;
import org.junit.Assert;
import org.junit.Test;

public class ClientTest {
    String name = "testClient";
    long cardNumber = 8765432187654321L;
    long nhsId = 1234512347;
    DateBirth dateBirth = new DateBirth(24,12,2002);
    long tin = 1234512345;
    long phonenumber = 12345123457L;
    Email email = new Email("testClient@gmail.com");
    String gender = "male";
    Client testClient = new Client(name, cardNumber, nhsId, dateBirth, tin, phonenumber, email, gender);

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

        Assert.assertEquals(name, name1);
        Assert.assertEquals(cardNumber, cardNumber1);
        Assert.assertEquals(nhsId, nhsId1);
        Assert.assertEquals(dateBirth, dateBirth1);
        Assert.assertEquals(tin, tin1);
        Assert.assertEquals(phonenumber, phonenumber1);
        Assert.assertEquals(email, email1);
        Assert.assertEquals(gender, gender1);
    }

}
