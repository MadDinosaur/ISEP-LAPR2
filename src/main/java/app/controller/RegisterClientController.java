package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.DateBirth;
import app.domain.model.Exceptions.*;
import app.domain.store.ClientStore;
import auth.domain.model.Email;
import auth.domain.model.Password;

public class RegisterClientController {
    public static ClientStore listClients = new ClientStore();
    private static String name; //-
    private static long cardNumber; //-
    private static long nhsId; //-
    private static DateBirth dateBirth;   //-
    private static long TIN; //-
    private static long phoneNumber; //-
    private static Email email; //
    private static String sex; //
    private static String SEX_POR_OMISSAO = "No sex assigned";
    private static Client newClient;

    public static void setSexOpcao(int opcao) {
        if (opcao == 1) {
            sex = "Male";
        }
        if (opcao == 2) {
            sex = "Female";
        }
        if (opcao == 3) {
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
            throw new InvalidTINException("TIN must have 10 digits");
        }
        RegisterClientController.TIN = TIN;
    }
    public static void setPhoneNumber(long phoneNumber){
        if(phoneNumber < 10000000000.0 || phoneNumber> 99999999999.0){
            throw new InvalidPhoneNumberException("Phone Number must have 11 digits");
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

    public static String getName() {
        return name;
    }
    public static long getCardNumber() {
        return cardNumber;
    }
    public static long getNhsId() {
        return nhsId;
    }
    public static DateBirth getDateBirth() {
        return dateBirth;
    }
    public static long getTIN() {
        return TIN;
    }
    public static long getPhoneNumber() {
        return phoneNumber;
    }
    public static Email getEmail() {
        return email;
    }
    public static String getSex() {
        return sex;
    }

    public static void createClient(){
        String id =RegisterClientController.email.toString();
        RegisterClientController.newClient = new Client(RegisterClientController.name,RegisterClientController.cardNumber,RegisterClientController.nhsId,RegisterClientController.dateBirth,RegisterClientController.TIN,RegisterClientController.phoneNumber,RegisterClientController.email,RegisterClientController.sex);
    }

    public static boolean saveClient(){
        return App.getInstance().getCompany().addClient(newClient);
    }


}







