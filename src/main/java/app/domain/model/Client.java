package app.domain.model;

import app.mappers.dto.ClientDTO;
import auth.domain.model.Email;
import auth.domain.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {

    private String name;
    private long cardNumber;
    private long nhsId;
    private DateBirth dateBirth;
    private long TIN;
    private long phoneNumber;
    private Email email;
    private String sex;
    private final String organizationRole = "cl";
    private final List<Test> clientTests = new ArrayList<>();
    private String address;

    public Client(String name, long cardNumber, long nhsId, DateBirth dateBirth, long TIN, long phoneNumber, Email email, String sex) {
        setName(name);
        setCardNumber(cardNumber);
        setNhsId(nhsId);
        setDateBirth(dateBirth);
        setTIN(TIN);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setSex(sex);
    }

    public Client(String name, long cardNumber, long nhsId, DateBirth dateBirth, long tin, long phoneNumber, String address, Email email) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.nhsId = nhsId;
        this.dateBirth = dateBirth;
        this.TIN = tin;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardNumber(long cardNumber) {

        this.cardNumber = cardNumber;
    }

    public void setNhsId(long nhsId) {
        this.nhsId = nhsId;

    }

    public void setDateBirth(DateBirth dateBirth) {
        this.dateBirth = dateBirth;
    }

    public void setTIN(long TIN) {

        this.TIN = TIN;

    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(Email email) {

        this.email = email;
    }

    public Email getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public String getOrganizationRole() {
        return organizationRole;
    }

    public long getTIN() {
        return TIN;
    }

    public List<Test> getClientTests() {
        return clientTests;
    }

    public DateBirth getDateBirth() {
        return dateBirth;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public long getNhsId() {
        return nhsId;
    }

    public void addTestToClient(Test test) {
        clientTests.add(test);
    }

    @Override
    public boolean equals(Object o) {
        // Inspired in https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        Client obj = (Client) o;
        return Objects.equals(this.email, obj.email);
    }

    public void updateData(ClientDTO clientData) {
        setName(clientData.getName());
        setCardNumber(Long.parseLong(clientData.getCardNumber()));
        setNhsId(Long.parseLong(clientData.getNhsId()));
        setDateBirth(DateBirth.parse(clientData.getDateBirth()));
        setTIN(Long.parseLong(clientData.getTIN()));
        setPhoneNumber(Long.parseLong(clientData.getPhoneNumber()));
        setEmail(new Email(clientData.getEmail()));
        setSex(clientData.getSex());
    }
}
