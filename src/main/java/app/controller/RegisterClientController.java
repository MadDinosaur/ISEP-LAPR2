package app.controller;

import app.domain.model.Exceptions.InvalidCardNumberException;
import app.domain.model.Exceptions.InvalidNameException;
import app.domain.model.Exceptions.InvalidNhsIdException;
import auth.domain.model.Email;
import auth.domain.model.User;

import javax.print.DocFlavor;

public class RegisterClientController {

    private static String name;
    private static long cardNumber;
    private static long nhsId;
    private String dateBirth;   //String dateBirth ---> Date dateBirth
    private int TIN;
    private int phoneNumber;
    private Email email;
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
        }if (name.isBlank()) {
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
}

//add CLient to Cliente Store
//add Client to UserStore





