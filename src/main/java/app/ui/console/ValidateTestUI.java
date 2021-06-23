package app.ui.console;

import app.controller.ValidateTestController;
import app.domain.model.exceptions.EmptyListException;
import app.domain.model.Test;

import java.io.IOException;
import java.util.Scanner;

public class ValidateTestUI implements Runnable{

    Scanner sc = new Scanner(System.in);
    private final ValidateTestController vtctrl = new ValidateTestController();

    @Override
    public void run() {
        try {
            if (vtctrl.getListTestsWithReport() != null) {
                System.out.println("These are the available tests:");
                int i = 0;
                try {
                    for (Test test : vtctrl.getListTestsWithReport()) {
                        System.out.println("Test#" + i + ": " + test.toStringWithDates());
                        i++;
                    }
                } catch (EmptyListException e) {
                    System.out.println("There are no tests ready for validation.");
                }
                System.out.println("Please select the tests you wish to validate by their number.");
                int tests = sc.nextInt();
                i = 0;
                while (tests != -1) {
                    for(Test test: vtctrl.getListTestsWithReport()) {
                        if (i == tests) {
                            vtctrl.validateTest(test.getNhsCode());
                            try {
                                vtctrl.sendNotification(test.getNhsCode());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("The client has been notified.");
                        }
                        i++;
                    }
                }
                tests = sc.nextInt();
            }
            System.out.println("All test have been validated.");
        }catch (EmptyListException e){
            System.out.println("There are no more tests ready for validation.");
        }
    }
}
