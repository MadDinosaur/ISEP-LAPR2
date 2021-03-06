package app.ui.console;

import app.controller.App;
import app.controller.CreateEmployeeController;
import app.domain.model.exceptions.InvalidEmployeeException;
import app.domain.model.exceptions.UnregisteredOrgRolesException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CreateEmployeeUI implements Runnable {
    CreateEmployeeController controller = new CreateEmployeeController(App.getInstance().getCompany());
    String organizationRole;
    String name;
    String address;
    String phoneNumber;
    String email;
    String soc;
    String din;

    Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        try {while (!chooseRole()) chooseRole();} catch (UnregisteredOrgRolesException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        try {insertInfo();} catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            displayInfo();
            return;
        }
        displayInfo();
        try {saveInfo();} catch (InvalidEmployeeException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean chooseRole() {
        List<String> roles = controller.getOrganizationRoles();

        System.out.println("Please choose an employee role:");
        int optionNum = 1;
        for (String option : roles) {
            if(!option.equals("Client")) {                                //
                System.out.printf("%d - %s\n", optionNum, option);        //
            }
            optionNum++;
        }

        int optionSelect = Integer.parseInt(sc.nextLine());
        if (optionSelect >= optionNum || optionSelect < 1) {
            System.out.println("Invalid option!");
            return false;
        }
        this.organizationRole = roles.get(optionSelect - 1);
        return true;
    }

    private void insertInfo() {
        System.out.println("Please insert employee full name:");
        this.name = sc.nextLine();
        System.out.println("Please insert employee address:");
        this.address = sc.nextLine();
        System.out.println("Please insert employee phone number:");
        this.phoneNumber = sc.nextLine();
        System.out.println("Please insert employee email:");
        this.email = sc.nextLine();
        System.out.println("Please insert employee SOC code:");
        this.soc = sc.nextLine();
        if (organizationRole.equals("Specialist Doctor")) {
            System.out.println("Please insert the Doctor Index Number:");
            this.din = sc.nextLine();
        }
        System.out.println("Checking information...");
        try {controller.createEmployee(organizationRole, name, address, phoneNumber, email, soc, din);}
        catch (InvalidEmployeeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void displayInfo() {
        System.out.printf("Job: %s\nName: %s\nAddress: %s\nPhone number: %s\nE-mail: %s\nSOC code: %s\n",
                organizationRole, name, address, phoneNumber, email, soc);
        if (din != null)
            System.out.printf("DIN: %s\n", din);
    }

    private boolean saveInfo() throws IOException {
        System.out.println("Do you wish to save? Y/N");
        switch (sc.nextLine()) {
            case "Y":
            case "y":
                if (controller.saveEmployee()) {
                    System.out.println("Employee saved successfully!");
                    return true;
                } else {
                    System.out.println("Employee not saved. Please try again.");
                    return false;
                }
            case "N":
            case "n":
                System.out.println("Employee not saved.");
                return false;
            default:
                System.out.println("Invalid option!");
                return false;
        }
    }
}