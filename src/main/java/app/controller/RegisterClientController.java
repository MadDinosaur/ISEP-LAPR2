package app.controller;

import app.domain.model.Client;
import app.domain.model.DateBirth;
import app.domain.model.Exceptions.InvalidCardNumberException;
import app.domain.model.Exceptions.InvalidNameException;
import app.domain.model.Exceptions.InvalidNhsIdException;
import app.domain.model.Exceptions.InvalidPhoneNumberException;
import app.domain.model.listClients;
import auth.domain.model.Email;
import auth.domain.model.User;

import javax.print.DocFlavor;
import java.util.Date;

public class RegisterClientController {

    private static String name;
    private static long cardNumber;
    private static long nhsId;
    private static DateBirth dateBirth;   //String dateBirth ---> Date dateBirth
    private static long TIN;
    private static long phoneNumber;
    private static Email email;
    private static String sex;
    private static String SEX_POR_OMISSAO = "No sex assigned";

    public static void setSexOpcao(int opcao) {
        if (opcao == 1) {
            sex = "masculine";
        }
        if (opcao == 2) {
            sex = "femenine";
        } else {
            sex = SEX_POR_OMISSAO;
        }
    }
    public static void setNameClient(String name){
        if (name.length() > 35) {
            throw new InvalidNameException("Name is too long! Name length=" + name.length() + " Max length=35");
        }if (name.isEmpty()) {
            throw new InvalidNameException("Name is blank!");
        }
        RegisterClientController.name = name;
    }
    public static void setCardNumber(long cardNumber){
        if (cardNumber > 9999999999999999.0 || cardNumber < 1000000000000000.0){
            throw new InvalidCardNumberException("Card Number must have 16 digits");
        }
        RegisterClientController.cardNumber = cardNumber;
    }
    public static void setNhsId(long nhsId){
        if(nhsId < 1000000000 || nhsId > 9999999999.0){
            throw new InvalidNhsIdException("NHS ID must have 10 digits");
        }
        RegisterClientController.nhsId = nhsId;
    }
    public static void setTIN(long TIN){
        if(TIN < 1000000000 || TIN > 9999999999.0){
            throw new InvalidNhsIdException("NHS ID must have 10 digits");
        }
        RegisterClientController.TIN = TIN;
    }
    public static void setPhoneNumber(long phoneNumber){
        if(phoneNumber < 10000000000.0 || phoneNumber> 99999999999.0){
            throw new InvalidPhoneNumberException("NHS ID must have 11 digits");
        }
        RegisterClientController.phoneNumber = TIN;
    }
    public static void setEmail (String email){
        RegisterClientController.email = new Email(email);
    }
    public static void setDate (String date){
        String[] dateDivided = date.split("/");
        int day = Integer.parseInt(dateDivided[0]);
        int month = Integer.parseInt(dateDivided[1]);
        int year = Integer.parseInt(dateDivided[2]);
        RegisterClientController.dateBirth = new DateBirth(day,month,year);
    }
    public void createClient(){
        String id =RegisterClientController.email.toString();
        Client newClient = new Client(RegisterClientController.name,RegisterClientController.cardNumber,RegisterClientController.nhsId,RegisterClientController.dateBirth,RegisterClientController.TIN,RegisterClientController.phoneNumber,RegisterClientController.email,RegisterClientController.sex);
    }
    public void addClientToStores(){
        listClients.add
    }
}

//add CLient to Cliente Store
//add Client to UserStore





