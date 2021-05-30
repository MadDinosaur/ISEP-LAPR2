package app.ui.console;

import app.controller.RegisterNewLabController;
import app.domain.model.Exceptions.*;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.Scanner;

public class    RegisterNewLabUI implements Runnable {
    static Scanner input = new Scanner(System.in);
    private RegisterNewLabController CNLC = new RegisterNewLabController();

    public void run(){
        System.out.println("Creating a new clinical analysis laboratory...");
        setData();
        setTestType();
    }

    public void setData() {
        boolean exception = false;
        while (!exception) {
            try {
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
                if (confirm) {
                    CNLC.setData(name, address, phonenumber, TIN, labID);
                } else {
                    System.out.println("Reenter the information.");
                    setData();
                }

                exception = true;

            } catch (InvalidNameException e) {
                System.out.println("Invalid name. Name must not be empty or have more than 20 characters!");
            } catch (InvalidAddressException e) {
                System.out.println("Invalid address. Address must not be empty or have more than 30 characters!");
            } catch (InvalidPhoneNumberException e) {
                System.out.println("Invalid phone number. Phone number must be a 12 digit number!");
            } catch (InvalidTINException e) {
                System.out.println("Invalid TIN. Tax identification number must be an 11 digit number!");
            } catch (InvalidLaboratoryIDException e) {
                System.out.println("Invalid ID. Laboratory ID must have 5 alphanumerical characters!");
            }
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
        input.nextLine();
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
