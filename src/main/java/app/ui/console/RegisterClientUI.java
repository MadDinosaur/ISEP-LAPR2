package app.ui.console;

import app.controller.RegisterClientController;
import app.domain.model.Exceptions.*;
import app.ui.console.utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.*;

public class RegisterClientUI implements Runnable {

    Scanner sc = new Scanner(System.in);
    @Override
    public void run() {
        System.out.println("\n");
        System.out.println("######## Client Registration ########");
        System.out.println("Please introduce Client Name");
        System.out.println("*write \"exit\" at any moment to exit*");
        boolean verifier = false;
        while(!verifier) {
            try {
                String name = sc.nextLine();
                if(name.equals("exit")) {
                    System.out.println("sair");
                    System.out.println("Returnado ao ReceptionistUI");
                }
                RegisterClientController.setNameClient(name);
                verifier = true;
            }catch (InvalidNameException e){
                System.out.println(e);
            }
        }
        System.out.println("Now introduce Client Card Number");
        System.out.println("*write \"exit\" at any moment to exit*");
        verifier = false;
        while(!verifier) {
            try {
                String clientCard = sc.nextLine();
                if(clientCard.equals("exit")) {
                    System.out.println("sair");
                    System.out.println("Returnado ao ReceptionistUI");
                }
                long cardNumber = Long.parseLong(clientCard);
                RegisterClientController.setCardNumber(cardNumber);
                verifier = true;
            }catch (InvalidCardNumberException e){
                System.out.println(e);
            }catch (InputMismatchException e){
                System.out.println("Card Number must only contain numbers");
            }
        }
        System.out.println("Now introduce NHS Id");
        System.out.println("*write \"exit\" at any moment to exit*");
        verifier = false;
        while(!verifier) {
            try {
                String nshId = sc.nextLine();
                if(nshId.equals("exit")) {
                    System.out.println("sair");
                    System.out.println("Returnado ao ReceptionistUI");
                }
                long nhsId = Long.parseLong(nshId);
                RegisterClientController.setNhsId(nhsId);
                verifier = true;
            }catch (InvalidNhsIdException e){
                System.out.println(e);
            }catch (InputMismatchException e){
                System.out.println("NHS Id must only contain numbers");
            }
        }
        System.out.println("Now introduce Birth Date. It must be in the format DD/MM/YYYY");
        System.out.println("*write \"exit\" at any moment to exit*");
        verifier = false;
        while(!verifier) {
            try {
                String date = sc.nextLine();
                if(date.equals("exit")) {
                    System.out.println("sair");
                    System.out.println("Returnado ao ReceptionistUI");
                }
                RegisterClientController.setDate(date);
                verifier = true;
            }catch (InvalidDateException e){
                System.out.println(e);
            }catch (InputMismatchException e){
                System.out.println("Birthdate must be in the format DD/MM/YYYY");
                int a = 50;
                System.out.println("O brito é burro %d");
            }
        }
        System.out.println("Now introduce Client TIN");
        System.out.println("*write \"exit\" at any moment to exit*");
        verifier = false;
        while(!verifier) {
            try {
                String TIN = sc.nextLine();
                if(TIN.equals("exit")) {
                    System.out.println("sair");
                    System.out.println("Returnado ao ReceptionistUI");
                }
                long TIN2 = Long.parseLong(TIN);
                RegisterClientController.setTIN(TIN2);
                verifier = true;
            }catch (InvalidTINException e){
                System.out.println(e);
            }catch (InputMismatchException e){
                System.out.println("TIN must only contain numbers");
            }
        }
        System.out.println("Now introduce Client phone number");
        System.out.println("*write \"exit\" at any moment to exit*");
        verifier = false;
        while(!verifier) {
            try {
                String phone = sc.nextLine();
                if(phone.equals("exit")) {
                    System.out.println("sair");
                    System.out.println("Returnado ao ReceptionistUI");
                }
                long phoneNumber = Long.parseLong(phone);
                RegisterClientController.setPhoneNumber(phoneNumber);
                verifier = true;
            }catch (InvalidPhoneNumberException e){
                System.out.println(e);
            }catch (InputMismatchException e){
                System.out.println("TIN must only contain numbers");
            }
        }
        System.out.println("Now introduce Client email");
        System.out.println("*write \"exit\" at any moment to exit*");
        verifier = false;
        while(!verifier) {
            try {
                String email = sc.nextLine();
                if(email.equals("exit")) {
                    System.out.println("sair");
                    System.out.println("Returnado ao ReceptionistUI");
                }
                RegisterClientController.setEmail(email);
                verifier = true;
            }catch (InvalidEmailException e){
                System.out.println(e);
            }catch (Exception a){
                System.out.println(a);
            }
        }
        System.out.println("Now introduce Client sex");
        System.out.println("*write \"exit\" at any moment to exit*");
        verifier = false;
        while(!verifier) {
            System.out.println("Sex Options (Optional)");
            try {
                System.out.println("1- Male");
                System.out.println("2- Female");
                System.out.println("3- Move foward wthout sex");
                String option = sc.nextLine();
                if(option.equals("exit")) {
                    System.out.println("sair");
                    System.out.println("Returnado ao ReceptionistUI");
                }
                int optionUser = Integer.parseInt(option);
                if(optionUser == 1){
                    RegisterClientController.setSexOpcao(1);
                }if(optionUser == 2){
                    RegisterClientController.setSexOpcao(2);
                }if(optionUser == 3){
                    RegisterClientController.setSexOpcao(3);
                }
                verifier = true;
            }catch (InputMismatchException e){
                System.out.println("Opcion must be a number from 1 to 3");
            }
        }
        System.out.println("Please confirm Client Data");
        System.out.println("Name: " + RegisterClientController.getName());
        System.out.println("Card Number: " + RegisterClientController.getCardNumber());
        System.out.println("Nhs Id: "+ RegisterClientController.getNhsId());
        System.out.println("Birth Date: "+ RegisterClientController.getDateBirth());
        System.out.println("TIN: " + RegisterClientController.getTIN());
        System.out.println("Phone Number: "+ RegisterClientController.getPhoneNumber());
        System.out.println("Email: "+ RegisterClientController.getEmail());
        System.out.println("Sex: " + RegisterClientController.getSex());

        System.out.println("Is the data correct?");
        System.out.println("Type Yes/No/Cancel");
        String awnser = sc.nextLine();
        boolean valid = false;
        while (!valid) {
            if (awnser.equalsIgnoreCase("yes")) {
                RegisterClientController.createClient();
                if (RegisterClientController.saveClient()){
                    System.out.println("Client Registered with success");
                }else {
                    System.out.println("Client could not me Registered with success");
                }
                valid = true;

            }
            if (awnser.equalsIgnoreCase("no")) {
                System.out.println("Would you like to restart the user creation or to leave?");
                System.out.println("Type Restart/Leave");
                String newAwnser = sc.nextLine();
                boolean validAwnser = false;
                while (!validAwnser) {
                    if (newAwnser.equalsIgnoreCase("restart")) {
                        new RegisterClientUI().run();
                        validAwnser = true;
                    }
                    if (newAwnser.equalsIgnoreCase("leave")) {
                        //new ReceptionistUI.run();
                        System.out.println("Returnado ao ReceptionistUI");
                        validAwnser = true;
                    } else {
                        System.out.println("Invalid option! Type Restart/Leave");
                    }
                }
                valid = true;
            }else {
                System.out.println("Invalid option! Type Yes/No");
            }
        }







    }







}
