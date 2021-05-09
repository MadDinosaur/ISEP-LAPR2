package app.ui.console;

import app.controller.CreateTestTypeController;

import java.util.Scanner;

public class CreateTestTypeUI {

    static Scanner sc = new Scanner(System.in);
    private CreateTestTypeController cttc;

    public void run() {
        createTestType();
        createCollectionMethod();
        System.out.println("\n");
        System.out.println("--------Creating a new Test Type menu--------");
        setDesignation();
        setCollectionMethod();
        setCategories();
        saveTestType();
        System.out.println("The test type you just created was saved!");
    }

    public String setDesignation() {
        boolean loop = false;
        System.out.println("Insert the designation wanted for the test type:");
        String designation = sc.nextLine();
        System.out.printf("This is the designation you inserted: %s. Are you sure this is the designation you want? Yes/No\n", designation);
        String confirmation = sc.nextLine();
        while (!loop) {
            if (confirmation.equalsIgnoreCase("yes")) {
                loop = true;
                cttc.setDesignation(sc.nextLine());
                System.out.println("Designation saved!");
            } else if (confirmation.equalsIgnoreCase("no")) {
                System.out.println("Please, type the designation you want once again");
                designation = sc.nextLine();
                System.out.printf("This is the designation you inserted: %s. Are you sure this is the designation you want? Yes/No\n", designation);
                confirmation = sc.nextLine();
            } else {
                System.out.println("Please insert either yes or no!");
                confirmation = sc.nextLine();
            }
        }
        return designation;
    }

    public String setCollectionMethod() {
        boolean loop = false;
        System.out.println("Insert the description you want for the collection method of the test type:");
        String description = sc.nextLine();
        System.out.printf("This is the description you inserted: %s. Are you sure this is the description you want? (Yes/No)\n", description);
        String confirmation = sc.nextLine();
        while (!loop) {
            if (confirmation.equalsIgnoreCase("yes")) {
                loop = true;
                cttc.setCollectionMethod(description);
                cttc.setCollectionMethodToTestType();
                System.out.println("Description saved!\n");
            } else if (confirmation.equalsIgnoreCase("no")) {
                System.out.println("Please, type the description you want once again.\n");
                description = sc.nextLine();
                System.out.printf("This is the description you inserted: \"%s\". Are you sure this is the description you want? (Yes/No)\n", description);
                confirmation = sc.nextLine();
            } else {
                System.out.println("Please insert either yes or no!");
                confirmation = sc.nextLine();
            }
        }
        return description;
    }

    public void setCategories() {
        boolean loop = false;
        String confirmation;
        System.out.println("Here is a list of the categories available:\n");
        cttc.displayCategoryList();
        System.out.println("One by one, insert the number of the category you want to associate to the test type (If you don't want to insert any more categories, please press ENTER)\n");
        String indexPedido = sc.nextLine();
        while (!loop) {
            if (indexPedido.equals("")) {
                loop = true;
            } else {
                System.out.printf("Are you sure this is the category you want? (Yes/No)\n");
                confirmation = sc.nextLine();
                if (confirmation.equalsIgnoreCase("yes")) {
                    cttc.setCategoriesToTestType(Integer.parseInt(indexPedido));
                    cttc.displayCategoryList();
                    System.out.println("Please insert another category (If you don't want to insert any more categories, please press ENTER)");
                    indexPedido = sc.nextLine();
                } else if (confirmation.equalsIgnoreCase("no")) {
                    System.out.println("Please, choose another category");
                    cttc.displayCategoryList();
                    indexPedido = sc.nextLine();
                } else {
                    System.out.println("Please insert a valid category");
                    cttc.displayCategoryList();
                    indexPedido = sc.nextLine();
                }
            }
        }
    }

    public void saveTestType() {
        cttc.saveTestType();
    }

    public void createTestType() {
        cttc.createTestType();
    }

    public void createCollectionMethod() {
        cttc.createCollectionMethod();
    }
}
