package app.ui.console;

import app.controller.CreateNewLabController;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.Scanner;

public class CreateNewLabUI implements Runnable {
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

        input.nextLine();
        System.out.println("Insert laboratory ID.");
        String labID = input.nextLine();

        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phonenumber);
        System.out.println("Tax Identification Number: " + TIN);
        System.out.println("Laboratory ID: " + labID);
        System.out.println("Is this information correct? (yes/no)");

        boolean confirm = confirmation();
        if(confirm) {
            CNLC.setData(name, address, phonenumber, TIN, labID);
        } else {
            System.out.println("Reenter the information.");
            setData();
        }

    }

    public void setTestType() {
        ArrayList<TestType> TestTypeList = CNLC.getTestTypeList();
        System.out.println("Choose one or more of the following test types (type -1 to end selection):");

        int index = 0;
        for(TestType tt : TestTypeList){
            System.out.printf("%s - %s\n", index, tt.getCode());
            index++;
        }

        int i = input.nextInt();
        while (i != -1) {
            CNLC.setTestType(TestTypeList.get(i));
            i = input.nextInt();
        }
    }

    public boolean confirmation() {
        String confirmation = input.nextLine();
        while(true) {
            if (confirmation.equalsIgnoreCase("yes")) {
                return true;
            } else {
                if (confirmation.equalsIgnoreCase("no")) {
                    return false;
                } else {
                    System.out.println("Insert valid answer");
                }
            }
            confirmation = input.nextLine();
        }
    }
}
