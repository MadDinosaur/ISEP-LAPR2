package app.ui.console;

import app.controller.RegisterClientController;
import app.domain.model.Exceptions.InvalidDateException;
import app.domain.model.Exceptions.InvalidNameException;
import app.domain.model.Exceptions.InvalidNhsIdException;
import app.domain.model.Exceptions.InvalidTINException;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

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
                }
                long cardNumber = Long.parseLong(clientCard);
                RegisterClientController.setCardNumber(cardNumber);
                verifier = true;
            }catch (InvalidNameException e){
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
                String nshId = sc.nextLine();
                if(nshId.equals("exit")) {
                    System.out.println("sair");
                }
                long TIN = Long.parseLong(nshId);
                RegisterClientController.setTIN(TIN);
                verifier = true;
            }catch (InvalidTINException e){
                System.out.println(e);
            }catch (InputMismatchException e){
                System.out.println("NHS Id must only contain numbers");
            }
        }



    }


















 /*   public static void main(String[] args) {
        boolean valido = false;
        while(!valido) {
            try {
                RegisterClientController.setNameClient("");
                valido =true;
            } catch (InvalidNameException e) {
                System.out.println(e);
            }
        }
    }*/
}
