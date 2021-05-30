package app.ui.console;

import app.controller.RegisterClientController;
import app.domain.model.Exceptions.*;

import java.io.IOException;
import java.util.*;

public class RegisterClientUI implements Runnable {
    private RegisterClientController clientController = new RegisterClientController();

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
                }
                clientController.setNameClient(name);
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
                }
                long cardNumber = Long.parseLong(clientCard);
                clientController.setCardNumber(cardNumber);
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
                }
                long nhsId = Long.parseLong(nshId);
                clientController.setNhsId(nhsId);
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
                }
                clientController.setDate(date);
                verifier = true;
            }catch (InvalidDateException e){
                System.out.println(e);
            }catch (InputMismatchException e){
                System.out.println("Birthdate must be in the format DD/MM/YYYY");
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
                }
                long TIN2 = Long.parseLong(TIN);
                clientController.setTIN(TIN2);
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
                }
                long phoneNumber = Long.parseLong(phone);
                clientController.setPhoneNumber(phoneNumber);
                verifier = true;
            }catch (InvalidPhoneNumberException e){
                System.out.println(e);
            }catch (InputMismatchException e){
                System.out.println("Phone Number must only contain numbers");
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
                }
                clientController.setEmail(email);
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
                }
                int optionUser = Integer.parseInt(option);
                if(optionUser == 1){
                    clientController.setSexOpcao(1);
                }if(optionUser == 2){
                    clientController.setSexOpcao(2);
                }if(optionUser == 3){
                    clientController.setSexOpcao(3);
                }
                verifier = true;
            }catch (InputMismatchException e){
                System.out.println("Opcion must be a number from 1 to 3");
            }
        }
        System.out.println("Please confirm Client Data");
        System.out.println("Name:" + clientController.getName());
        System.out.println("Card Number:" + clientController.getCardNumber());
        System.out.println("Nhs Id:"+ clientController.getNhsId());
        System.out.println("Birth Date:"+ clientController.getDateBirth());
        System.out.println("TIN:" + clientController.getTIN());
        System.out.println("Phone Number:"+ clientController.getPhoneNumber());
        System.out.println("Email:"+ clientController.getEmail());
        System.out.println("Sex:" + clientController.getSex());

        System.out.println("Is the data correct?");
        System.out.println("Type Yes/No/Cancel");
        String awnser = sc.nextLine();
        if (awnser.equalsIgnoreCase("yes")){
            try {
                clientController.saveClient();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }if(awnser.equalsIgnoreCase("no")){
            System.out.println("Press any Key to restart Client Registration");
            new RegisterClientUI().run();
        }



    }


}
