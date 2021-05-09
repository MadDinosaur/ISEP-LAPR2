package app.ui.console;

import app.controller.CreateNewLabController;

import java.util.Scanner;

public class newLabUI {
    static Scanner input = new Scanner(System.in);
    private CreateNewLabController CNLC = new CreateNewLabController();

    public void run(){
        System.out.println("Creating a new clinical analysis laboratory...");
        setData();
        setTestType();
    }

    public void setData() {
        System.out.println("Insert name.");
        String name = input.nextLine();

        System.out.println("Insert address.");
        String address = input.nextLine();

        System.out.println("Insert phone number.");
        long phonenumber = input.nextLong();

        System.out.println("Insert tax identification number.");
        long TIN = input.nextLong();

        System.out.println("Insert laboratory ID.");
        String labID = input.nextLine();

        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phonenumber);
        System.out.println("Tax Identification Number: " + TIN);
        System.out.println("Laboratory ID " + labID);
        System.out.println("Is this information correct? (yes/no)");

        int value = 0;
        while(value == 0) {
            String confirmation = input.nextLine();
            if (confirmation.equalsIgnoreCase("yes")) {
                CreateNewLabController.setData(name, address, phonenumber, TIN, labID);
                value = 1;
            } else {
                if (confirmation.equalsIgnoreCase("no")) {
                    setData();
                    value = 1;
                } else {
                    System.out.println("Insert valid answer");
                }
            }
        }

    }
}
