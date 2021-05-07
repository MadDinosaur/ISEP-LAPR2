package app.domain.model;
import app.controller.RegisterClientController;
import app.domain.model.Exceptions.*;
import auth.domain.model.Email;
import auth.domain.model.Password;
import auth.domain.model.User;

public class Client {

    private User clientUser;
    private String name;
    private long cardNumber;
    private long nhsId;
    private DateBirth dateBirth;
    private long TIN;
    private long phoneNumber;
    private Email email;
    private String sex;
    private String SEX_POR_OMISSAO = "No sex assigned";


    public Client(String name, long cardNumber, long nhsId, DateBirth dateBirth, long TIN, long phoneNumber, Email email, String sex) {
        Password pwd = new Password(email.getEmail());
        setName(name);
        setCardNumber(cardNumber);
        setNhsId(nhsId);
        setDateBirth(dateBirth);
        setTIN(TIN);
        setPhoneNumber(phoneNumber);
        setSex(sex);
        clientUser = new User(email, pwd, name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardNumber(long cardNumber) {
        if (RegisterClientController.listClients.existsCardNumber(cardNumber)) {
            throw new InvalidCardNumberException("Card Number already in use!");
        }
        this.cardNumber = cardNumber;
    }

    public void setNhsId(long nhsId) {
        if (RegisterClientController.listClients.existsNhsId(nhsId)) {
            throw new InvalidNhsIdException("NHS Id already in use!");
        }
        this.nhsId = nhsId;

    }

    public void setDateBirth(DateBirth dateBirth) {
        //mudar para date
        this.dateBirth = dateBirth;
    }

    public void setTIN(long TIN) {
        if (RegisterClientController.listClients.existsTIN(TIN)) {
            throw new InvalidTINException("TIN already in use!");
        }
        this.TIN = TIN;

    }

    public void setPhoneNumber(long phoneNumber) {
        if (RegisterClientController.listClients.existsPhoneNumber(phoneNumber)) {
            throw new InvalidPhoneNumberException("Phone Number alreay in use!");
        }
        this.phoneNumber = phoneNumber;

    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(Email email) {
        if (RegisterClientController.listClients.exists(email)) {
            throw new InvalidEmailException("Email already in use!");
        }
        this.email = email;

    }

    //Verify if equal in Company
    public boolean hasEmail(Email email) {
        return this.email.equals(email);
    }

    public boolean hasTIN(long TIN) {
        return this.TIN == TIN;
    }

    public boolean hasCardNumber(long cardNumber) {
        return this.cardNumber == cardNumber;
    }

    public boolean hasNhsID(long nhsId) {
        return this.nhsId == nhsId;
    }

    public boolean hasPhoneNumber(long phoneNumber) {
        return this.phoneNumber == phoneNumber;
    }


}
