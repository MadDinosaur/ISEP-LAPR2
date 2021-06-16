package app.ui.console;

import app.controller.CreateTestTypeController;
import app.domain.model.Exceptions.InvalidCategoryException;
import app.domain.model.Exceptions.InvalidCodeException;
import app.domain.model.Exceptions.InvalidDescriptionException;

import java.util.Scanner;

public class CreateTestTypeUI implements Runnable {

    private static final Scanner sc = new Scanner(System.in);
    private final CreateTestTypeController cttc = new CreateTestTypeController();
    private final static String YESORNO = "Please insert either yes or no!";
    private final static String DESCRIPTIONINSERTED = "This is the description you inserted: \"%s\". Are you sure this is the description you want? (Yes/No)%n";
    private final static String CATEGORYINSERTED = "Are you sure this is the category you want? (Yes/No)%n";

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
        boolean valid = false;
        while (!valid) {
            try {
                boolean loop = false;
                System.out.println("Insert the code for the test type: (make sure it is five alphanumeric characters)");
                String code = sc.nextLine();
                String codeInserted = "This is the code you inserted: \"%s\". Are you sure this is the code you want? (Yes/No)%n";
                System.out.printf(codeInserted, code);
                String confirmation = sc.nextLine();
                while (!loop) {
                    if (confirmation.equalsIgnoreCase("yes")) {
                        loop = true;
                        cttc.setCode(code);
                        System.out.println("Code saved!\n");
                        valid = true;
                    } else if (confirmation.equalsIgnoreCase("no")) {
                        System.out.println("Please, type the code you want once again");
                        code = sc.nextLine();
                        System.out.printf(codeInserted, code);
                        confirmation = sc.nextLine();
                    } else {
                        System.out.println(YESORNO);
                        confirmation = sc.nextLine();
                    }
                }
            } catch (InvalidCodeException e) {
                System.out.println("The code should only have 5 alphanumeric characters!!");
            }
        }
    }

    public void setCollectionMethod() {
        boolean valid = false;
        while (!valid) {
            try {
                boolean loop = false;
                System.out.println("Insert the description you want for the collection method of the test type:");
                String description = sc.nextLine();
                System.out.printf(DESCRIPTIONINSERTED, description);
                String confirmation = sc.nextLine();
                while (!loop) {
                    if (confirmation.equalsIgnoreCase("yes")) {
                        loop = true;
                        cttc.setCollectionMethod(description);
                        cttc.setCollectionMethodToTestType();
                        System.out.println("Description saved!\n");
                        valid = true;
                    } else if (confirmation.equalsIgnoreCase("no")) {
                        System.out.println("Please, type the description you want for the collection method once again.");
                        description = sc.nextLine();
                        System.out.printf(DESCRIPTIONINSERTED, description);
                        confirmation = sc.nextLine();
                    } else {
                        System.out.println(YESORNO);
                        confirmation = sc.nextLine();
                    }
                }
            } catch (InvalidDescriptionException e) {
                System.out.println("The collection method's description shouldn't be any longer than 20 characters!!");
            }
        }
    }

    public void setCategories() {
        boolean noMore = false;
        int counter = 0;
        String confirmation;
        System.out.println("Here is a list of the categories available:%n");
        cttc.displayCategoryList();
        System.out.println();
        System.out.println("One by one, insert the number of the category you want to associate to the test type (If you don't want to insert any more categories, please press ENTER)");
        String indexPedido = sc.nextLine();
        while (!noMore) {
            try {
                boolean loop = false;
                if (indexPedido.equals("") && counter > 0) {
                    noMore = true;
                } else if (indexPedido.equals("") && counter == 0) {
                    System.out.println("You should insert at least one category!");
                    cttc.displayCategoryList();
                    indexPedido = sc.nextLine();
                } else if (Integer.parseInt(indexPedido) < 0 || Integer.parseInt(indexPedido) > (cttc.getCategoryListSize() - 1)) {
                    System.out.println("Please insert a valid category!!");
                    cttc.displayCategoryList();
                    indexPedido = sc.nextLine();
                } else {
                    System.out.printf(CATEGORYINSERTED);
                    confirmation = sc.nextLine();
                    while (!loop) {
                        if (confirmation.equalsIgnoreCase("yes")) {
                            loop = true;
                            cttc.setCategoriesToTestType(Integer.parseInt(indexPedido));
                            counter++;
                            cttc.displayCategoryList();
                            System.out.println("Please insert another category (If you don't want to insert any more categories, please press ENTER)");
                            indexPedido = sc.nextLine();
                        } else if (confirmation.equalsIgnoreCase("no")) {
                            System.out.println("Please, choose another category");
                            cttc.displayCategoryList();
                            indexPedido = sc.nextLine();
                            System.out.printf(CATEGORYINSERTED);
                            confirmation = sc.nextLine();
                        } else {
                            System.out.println(YESORNO);
                            confirmation = sc.nextLine();
                        }
                    }
                }
            } catch (InvalidCategoryException e) {
                System.out.println("This category was already selected!!\nPlease chooser another category");
                cttc.displayCategoryList();
                indexPedido = sc.nextLine();
            }
        }
    }

    public void setTestTypeDescription() {
        boolean valid = false;
        while (!valid) {
            try {
                boolean loop = false;
                System.out.println("Insert the description you want for the test type:");
                String description = sc.nextLine();
                System.out.printf(DESCRIPTIONINSERTED, description);
                String confirmation = sc.nextLine();
                while (!loop) {
                    if (confirmation.equalsIgnoreCase("yes")) {
                        loop = true;
                        cttc.setDescription(description);
                        System.out.println("Description saved!\n");
                        valid = true;
                    } else if (confirmation.equalsIgnoreCase("no")) {
                        System.out.println("Please, type the description you want once again.\n");
                        description = sc.nextLine();
                        System.out.printf(DESCRIPTIONINSERTED, description);
                        confirmation = sc.nextLine();
                    } else {
                        System.out.println(YESORNO);
                        confirmation = sc.nextLine();
                    }
                }
            } catch (InvalidDescriptionException e) {
                System.out.println("The description you introduced shouldn't be any longer than 15 characters!!");
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
