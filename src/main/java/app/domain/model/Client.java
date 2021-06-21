package app.domain.model;

import app.mappers.dto.ClientDTO;
import auth.domain.model.Email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client implements Serializable {

    /**
     * Instance of client's name
     */
    private String name;

    /**
     * Instance of client's card number
     */
    private long cardNumber;

    /**
     * Instance of client's NHS Id
     */
    private long nhsId;

    /**
     * Instance of client's date of birth
     */
    private DateBirth dateBirth;

    /**
     * Instance of client's TIN(Tax identification number)
     */
    private long tin;

    /**
     * Instance of client's phone number
     */
    private long phoneNumber;

    /**
     * Instance of client's email
     */
    private Email email;

    /**
     * Instance of client's sex
     */
    private String sex;

    /**
     * Instance of client's organization role
     */
    private final String organizationRole = "cl";

    /**
     * Client's list of tests
     */
    private final List<Test> clientTests = new ArrayList<>();

    /**
     * Instance of client's address
     */
    private String address;

    /**
     * Instance of client's age in years
     */
    private int ageInYears;


    /**
     * Constructor for the class Client with 8 parameters (with setters)
     * @param name
     * @param cardNumber
     * @param nhsId
     * @param dateBirth
     * @param TIN
     * @param phoneNumber
     * @param email
     * @param sex
     */
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

    /**
     * Constructor of class Client with 8 parameters
     * @param name
     * @param cardNumber
     * @param nhsId
     * @param dateBirth
     * @param tin
     * @param phoneNumber
     * @param address
     * @param email
     */
    public Client(String name, long cardNumber, long nhsId, DateBirth dateBirth, long tin, long phoneNumber, String address, Email email) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.nhsId = nhsId;
        this.dateBirth = dateBirth;
        this.tin = tin;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    /**
     * Setter for client's name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for client's card number
     * @param cardNumber
     */
    public void setCardNumber(long cardNumber) {

        this.cardNumber = cardNumber;
    }

    /**
     * Setter for client's NHS Id
     * @param nhsId
     */
    public void setNhsId(long nhsId) {
        this.nhsId = nhsId;

    }

    /**
     * Setter for client's date of birth
     * @param dateBirth
     */
    public void setDateBirth(DateBirth dateBirth) {
        this.dateBirth = dateBirth;
    }

    /**
     * Setter for client's TIN(Tax identification number)
     * @param tin
     */
    public void setTIN(long tin) {

        this.tin = tin;

    }

    /**
     * Setter for client's phone number
     * @param phoneNumber
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    /**
     * Setter for client's sex
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Setter for client's email
     * @param email
     */
    public void setEmail(Email email) {

        this.email = email;
    }

    /**
     * Getter for client's email
     * @return client's email
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Getter for client's name
     * @return client's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for client's card number
     * @return client's card number
     */
    public long getCardNumber() {
        return cardNumber;
    }

    /**
     * Getter for client's organization role
     * @return client's organization role
     */
    public String getOrganizationRole() {
        return organizationRole;
    }

    /**
     * Getter for client's TIN(Tax identification number)
     * @return client's TIN
     */
    public long getTIN() {
        return tin;
    }

    /**
     * Getter for client's tests
     * @return list of client's tests
     */
    public List<Test> getClientTests() {
        return clientTests;
    }

    /**
     * Method that check if the client has any validated tests
     * @return boolean
     */
    public boolean checkValidatedTests(){
        for(Test test : clientTests){
            if(test.isValidated()){
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for client's date of birth
     * @return client's date of birth
     */
    public DateBirth getDateBirth() {
        return dateBirth;
    }

    /**
     * Getter for client's phone number
     * @return client's phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Getter for client's sex
     * @return client's sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Getter for client's NHS Id
     * @return client's NHS Id
     */
    public long getNhsId() {
        return nhsId;
    }

    /**
     * Getter for client's age in years
     * @return client's age in years
     */
    public int getAgeInYears() {
        return 2021- dateBirth.getYear();
    }

    /**
     * Method that adds a test to the client's test list
     * @param test
     */
    public void addTestToClient(Test test) {
        clientTests.add(test);
    }

    /**
     * Equals method
     * @param o
     * @return boolean
     */
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

    /**
     * Method that updates a client's data
     * @param clientData
     */
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

    /**
     * Method that compares two clients' TIN(Tax identification number)
     * @param o
     * @return integer
     */
    public int compareToTin(Client o){
        return (int) (this.getTIN() - o.getTIN());
    }

    /**
     * Method that compares two clients' names
     * @param o
     * @return integer
     */
    public int compareToName(Client o){
        return this.getName().compareTo(o.getName());
    }

}
