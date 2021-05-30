package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.DateBirth;
import app.domain.model.Exceptions.*;
import app.domain.shared.Constants;
import auth.domain.model.Email;
import auth.domain.model.UserRole;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class RegisterClientController {
    private  String name; //-
    private  long cardNumber; //-
    private  long nhsId; //-
    private  DateBirth dateBirth;   //-
    private  long TIN; //-
    private  long phoneNumber; //-
    private  Email email; //-
    private  String sex; //-
    private  Client newClient;
    private final Company company;
    private  String pass;

    public RegisterClientController(){
        this.company = App.getInstance().getCompany();
    }
    public  void setSexOpcao(int opcao) {
        if (opcao == 1) {
            sex = "Male";
        }
        if (opcao == 2) {
            sex = "Female";
        }
        if (opcao == 3) {
            sex = Constants.SEX_BY_DEFAULT;
        }
    }

    public void setNameClient(String name) {
        if (name.length() > 35) {
            throw new InvalidNameException("Name is too long! Name length=" + name.length() + " Max length=35");
        }
        if (name.isEmpty()) {
            throw new InvalidNameException("Name is blank!");
        }
        this.name = name;
    }

    public void setCardNumber(long cardNumber) {
        if (cardNumber > 9999999999999999.0 || cardNumber < 1000000000000000.0) {
            throw new InvalidCardNumberException("Card Number must have 16 digits");
        }
        this.cardNumber = cardNumber;
    }

    public void setNhsId(long nhsId) {
        if (nhsId < 1000000000 || nhsId > 9999999999.0) {
            throw new InvalidNhsIdException("NHS ID must have 10 digits");
        }
        this.nhsId = nhsId;
    }

    public void setTIN(long TIN) {
        if (TIN < 1000000000 || TIN > 9999999999.0) {
            throw new InvalidTINException("TIN must have 10 digits");
        }
        this.TIN = TIN;
    }

    public void setPhoneNumber(long phoneNumber) {
        if (phoneNumber < 10000000000.0 || phoneNumber > 99999999999.0) {
            throw new InvalidPhoneNumberException("Phone Number must have 11 digits");
        }
        this.phoneNumber = TIN;
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }

    public void setDate(String date) {
        String[] dateDivided = date.split("/");
        int day = Integer.parseInt(dateDivided[0]);
        int month = Integer.parseInt(dateDivided[1]);
        int year = Integer.parseInt(dateDivided[2]);
        this.dateBirth = new DateBirth(day, month, year);
    }

    public  String getName() {
        return name;
    }

    public  long getCardNumber() {
        return cardNumber;
    }

    public  long getNhsId() {
        return nhsId;
    }

    public  DateBirth getDateBirth() {
        return dateBirth;
    }

    public  long getTIN() {
        return TIN;
    }

    public  long getPhoneNumber() {
        return phoneNumber;
    }

    public  Email getEmail() {
        return email;
    }

    public  String getSex() {
        return sex;
    }


    public boolean saveClient() throws IOException {
        this.newClient = new Client(this.name, this.cardNumber, this.nhsId, this.dateBirth, this.TIN,this.phoneNumber,this.email,this.sex);
        this.pass = passGen();

        // company.getAuthFacade().addUserRole("cl","Client"); //....

       return company.saveClientAsUser(newClient,email.toString());
    }

    private  String passGen(){
        return pass = company.generateUserPassword();
    }

}







