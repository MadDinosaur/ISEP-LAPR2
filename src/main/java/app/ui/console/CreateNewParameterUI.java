package app.ui.console;

import app.controller.App;
import app.controller.*;
import app.domain.model.Category;
import app.domain.model.Exceptions.InvalidCodeException;
import app.domain.model.Exceptions.InvalidDescriptionException;
import app.domain.model.Exceptions.InvalidNameException;
import app.domain.model.Exceptions.InvalidNhsIdException;
import app.ui.Main;

import javax.swing.*;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class CreateNewParameterUI implements Runnable{

    Scanner sc = new Scanner(System.in);
    private CreateNewParameterController cnpc = new CreateNewParameterController();

    @Override
    public void run() {
        System.out.println("\n");
        System.out.println("######## New Parameter Specification ########");
        System.out.println("*write \"exit\" at any moment to exit*");
        boolean verifier = false;
        System.out.println(cnpc.getCategoryList());
        Iterator<Category> iterator = cnpc.getCategoryList().iterator();
        while(!verifier) {
            while (cnpc.getCategory().equals(null)) {
                String category = sc.nextLine();
                if(category.equalsIgnoreCase("exit")){
                    System.out.println("Exiting....");
                    verifier = true;
                }else while (iterator.hasNext()) {
                    Category cat = iterator.next();
                    if (cat.getName().equals(category)) {
                        cnpc.setCategory(cat);
                    }
                }
                if (cnpc.getCategory().equals(null)) {
                    System.out.println("That category isn't in the system.");
                }
            }
            String confirmation = "N";
            while(confirmation.equalsIgnoreCase("N") || confirmation.equalsIgnoreCase("No")) {

                System.out.println("Please introduce the parameter data in the following order, pressing enter after each: name, code, description.");
                String shortName = sc.nextLine();
                String code = sc.nextLine();
                String description = sc.nextLine();
                if (shortName.equalsIgnoreCase("exit") || code.equalsIgnoreCase("exit") || description.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting....");
                    verifier = true;
                } else{
                    try {
                        System.out.println(shortName + "; " + code + "; " + description + " Do you confirm this is the data for the new parameter?(Write Y/N  for yes or no respectively)");
                        confirmation = sc.nextLine();
                        if (confirmation.equalsIgnoreCase("Y") || confirmation.equalsIgnoreCase("Yes")) {
                            cnpc.createNewParameter(shortName, code, description);
                        }
                    } catch (InvalidNameException e) {
                        System.out.println("The name introduced doesn't seem to be acceptable. Please re-introduce the parameter data in the following order, pressing enter after each: name, code, description.");
                    } catch (InvalidCodeException e) {
                        System.out.println("The code introduced doesn't seem to be acceptable. Please re-introduce the parameter data in the following order, pressing enter after each: name, code, description.");
                    } catch (InvalidDescriptionException e) {
                        System.out.println("The description introduced doesn't seem to be acceptable. Please re-introduce the parameter data in the following order, pressing enter after each: name, code, description.");
                    }
                }
            }
        }

    }
}