package app.ui.console;

import app.controller.CreateTestTypeController;
import app.domain.model.Exceptions.InvalidCodeException;
import app.domain.model.Exceptions.InvalidDescriptionException;

import java.util.Scanner;

public class CreateTestTypeUI implements Runnable {

    static Scanner sc = new Scanner(System.in);
    private CreateTestTypeController cttc;

    public void run() {
        createTestType();
        createCollectionMethod();
        System.out.println("\n");
        System.out.println("--------Creating a new Test Type menu--------");
        setCode();
        setTestTypeDescription();
        setCollectionMethod();
        setCategories();
        saveTestType();
        System.out.println("The test type you just created was saved!");
    }

    public void setCode() {
        try {
            boolean loop = false;
            System.out.println("Insert the code wanted for the test type:");
            String code = sc.nextLine();
            System.out.printf("This is the code you inserted: %s. Are you sure this is the code you want? Yes/No\n", code);
            String confirmation = sc.nextLine();
            while (!loop) {
                if (confirmation.equalsIgnoreCase("yes")) {
                    loop = true;
                    cttc.setCode(sc.nextLine());
                    System.out.println("Designation saved!");
                } else if (confirmation.equalsIgnoreCase("no")) {
                    System.out.println("Please, type the code you want once again");
                    code = sc.nextLine();
                    System.out.printf("This is the code you inserted: %s. Are you sure this is the code you want? Yes/No\n", code);
                    confirmation = sc.nextLine();
                } else {
                    System.out.println("Please insert either yes or no!");
                    confirmation = sc.nextLine();
                }
            }
        } catch (InvalidCodeException e) {
            System.out.println("The designation shouldn't be any longer than 5 alphanumeric characters");
        }
    }

    public void setCollectionMethod() {
        try {
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
        } catch (InvalidDescriptionException e) {
            System.out.println("The collection method's description shouldn't be any longer than 20 characters");
        }
    }

    public void setCategories() {
        boolean noMore = false;
        boolean loop = false;
        String confirmation;
        System.out.println("Here is a list of the categories available:\n");
        cttc.displayCategoryList();
        System.out.println("One by one, insert the number of the category you want to associate to the test type (If you don't want to insert any more categories, please press ENTER)\n");
        String indexPedido = sc.nextLine();
        while (!noMore) {
            if (indexPedido.equals("")) {
                noMore = true;
            } else if (Integer.parseInt(indexPedido) < 0 || Integer.parseInt(indexPedido) > (cttc.getCategoryListSize() - 1) ) {
                    System.out.println("Please insert a valid category");
                    cttc.displayCategoryList();
                    indexPedido = sc.nextLine();
            } else {
                System.out.printf("Are you sure this is the category you want? (Yes/No)\n");
                confirmation = sc.nextLine();
                while (!loop) {
                    if (confirmation.equalsIgnoreCase("yes")) {
                        loop = true;
                        cttc.setCategoriesToTestType(Integer.parseInt(indexPedido));
                        cttc.displayCategoryList();
                        System.out.println("Please insert another category (If you don't want to insert any more categories, please press ENTER)");
                        indexPedido = sc.nextLine();
                    } else if (confirmation.equalsIgnoreCase("no")) {
                        System.out.println("Please, choose another category");
                        cttc.displayCategoryList();
                        indexPedido = sc.nextLine();
                        System.out.printf("Are you sure this is the category you want? (Yes/No)\n");
                        confirmation = sc.nextLine();
                    } else {
                        System.out.println("Please insert either yes or no!");
                        confirmation = sc.nextLine();
                    }
                }
            }
        }
    }

    public void setTestTypeDescription() {
        try {
            boolean loop = false;
            System.out.println("Insert the description you want for the test type:");
            String description = sc.nextLine();
            System.out.printf("This is the description you inserted: %s. Are you sure this is the description you want? (Yes/No)\n", description);
            String confirmation = sc.nextLine();
            while (!loop) {
                if (confirmation.equalsIgnoreCase("yes")) {
                    loop = true;
                    cttc.setDescription(description);
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
        } catch (InvalidDescriptionException e) {
            System.out.println("The description you introduced shouldn't be any longer than 15 characters.");
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
