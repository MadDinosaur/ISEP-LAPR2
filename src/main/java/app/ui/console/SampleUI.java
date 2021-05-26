package app.ui.console;

import app.controller.SampleController;
import java.util.Scanner;

public class SampleUI implements Runnable {
    static Scanner input = new Scanner(System.in);
    private SampleController SC = new SampleController();

    public void run() {
        getTestList();
        selectTestCode();

        System.out.println("Insert number of samples:");
        setSampleNumber();
    }

    public void getTestList() {
        SC.getTestList();
    }

    public void selectTestCode() {

    }


    public void setSampleNumber() {
        int n = input.nextInt();

        System.out.println("Number of samples:" + n);
        System.out.println("Is this information correct? (type yes/no)");

        boolean confirm = confirmation();
        if (confirm) {
            SC.setSampleNumber(n);
        } else {
            System.out.println("Enter the correct number of samples:");
            setSampleNumber();
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
