package app.ui.console;

import app.controller.CreateNewParameterController;
import app.domain.model.Category;
import app.domain.model.exceptions.InvalidCodeException;
import app.domain.model.exceptions.InvalidDescriptionException;
import app.domain.model.exceptions.InvalidNameException;

import java.util.Iterator;
import java.util.Scanner;



public class CreateNewParameterUI implements Runnable{

    private final String EXITING = "Exiting....";

    Scanner sc = new Scanner(System.in);
    private final CreateNewParameterController cnpc = new CreateNewParameterController();

    @Override
    public void run() {
        System.out.println("\n");
        System.out.println("######## New Parameter Specification ########");
        System.out.println("Write \"exit\" at any moment to exit.");
        boolean verifier = false;
        if(cnpc.getCategoryList() != null) {
            System.out.println("These are the available categories in the system: ");
            System.out.println(cnpc.getCategoryList());
            Iterator<Category> iterator = cnpc.getCategoryList().iterator();
            while (!verifier) {
                String category = sc.nextLine();
                if (category.equalsIgnoreCase("exit")) {
                    System.out.println(EXITING);
                } else while (iterator.hasNext()) {
                    Category cat = iterator.next();
                    if (cat.getCategoryName().equals(category)) {
                        cnpc.setCategory(cat);
                    }
                }
                if (cnpc.getCategory().equals(null)) {
                    System.out.println("That category isn't in the system.");
                }
                String confirmation = "N";
                while (confirmation.equalsIgnoreCase("N") || confirmation.equalsIgnoreCase("No")) {
                    System.out.println("Please introduce the parameter data in the following format: name; code; description.");
                    boolean exception = false;
                    while(!exception) {
                        try {
                            String data = sc.nextLine();
                            String[] parts = data.split("; ");
                            String parameterName = parts[0];
                            String parameterCode = parts[1];
                            String parameterDescription = parts[2];
                            if (parameterName.equalsIgnoreCase("exit") || parameterCode.equalsIgnoreCase("exit") || parameterDescription.equalsIgnoreCase("exit")) {
                                System.out.println(EXITING);
                            } else {
                                System.out.println(parameterName + "; " + parameterCode + "; " + parameterDescription + ". Do you confirm this is the data for the new parameter?(Write Y/N  for yes or no respectively)");
                                confirmation = sc.nextLine();
                                if (confirmation.equalsIgnoreCase("Y") || confirmation.equalsIgnoreCase("Yes")) {
                                    cnpc.createNewParameter(parameterName, parameterCode, parameterDescription);
                                    exception = true;
                                }
                            }
                        } catch (InvalidNameException e) {
                            System.out.println("The name introduced doesn't seem to be acceptable. Please re-introduce the parameter data in the following format: name, code, description.");
                        } catch (InvalidCodeException e) {
                            System.out.println("The code introduced doesn't seem to be acceptable. Please re-introduce the parameter data in the following format: name, code, description.");
                        } catch (InvalidDescriptionException e) {
                            System.out.println("The description introduced doesn't seem to be acceptable. Please re-introduce the parameter data in the following format: name, code, description.");
                        }
                    }
                    if (cnpc.saveParameter()) {
                        System.out.println("Parameter saved.");
                    }
                }
                System.out.println("Would you like to add more parameters? (write Y/N for yes or no respectively.)");
                confirmation = sc.nextLine();
                if (confirmation.equalsIgnoreCase("exit") || confirmation.equalsIgnoreCase("N") || confirmation.equalsIgnoreCase("No")) {
                    System.out.println(EXITING);
                    verifier = true;
                } else
                    verifier = false;
            }
        } else System.out.println("There doesn't seem to be any categories in the system, please introduce some before specifying parameters.");
    }
}