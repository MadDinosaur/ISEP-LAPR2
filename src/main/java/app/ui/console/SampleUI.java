package app.ui.console;

import app.controller.SampleController;
import app.mappers.dto.TestDTO;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public class SampleUI implements Runnable {

    static Scanner input = new Scanner(System.in);
    private final SampleController sc = new SampleController();

    public void run() {

        List<TestDTO> testList = sc.getTestList();
        TestCodeSelection(testList);

        try {
            System.out.println("Insert number of samples:");
            setSampleNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void TestCodeSelection(List<TestDTO> testList) {
        int index = 0;
        for (TestDTO testDTO : testList) {
            System.out.print(index + ": ");
            System.out.println(testDTO.getTestCode());
            index++;
        }

        System.out.println("Choose a test code by inserting its index:");

        boolean verifier = false;
        int k = -1;
        while(!verifier) {
            try {
                k = input.nextInt();
                input.nextLine();
                System.out.println("Is the test code " + testList.get(k).getTestCode() + "? (yes/no)");
                verifier = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid number. Please insert a valid number.");
            } catch (InputMismatchException e) {
                System.out.println("Insert a number.");
                input.next();
            }
        }

        boolean confirm = confirmation();
        if(confirm) {
            sc.setTestCode(testList.get(k).getTestCode());
        } else {
            TestCodeSelection(testList);
        }

    }

    public void setSampleNumber() throws Exception {
        int n = 0;

        boolean loop = true;
        while(loop) {
            try {
                n = input.nextInt();

                while (n <= 0) {
                    System.out.println("Please insert a valid number.");
                    n = input.nextInt();
                }
                loop = false;
            } catch (InputMismatchException e) {
                System.out.println("Please insert a number.");
                input.next();
            }
        }

        System.out.println("Number of samples: " + n);
        System.out.println("Is this information correct? (type yes/no)");

        input.nextLine();
        boolean confirm = confirmation();
        if (confirm) {
            sc.createSampleList(n);
        } else {
            System.out.println("Enter the correct number of samples:");
            setSampleNumber();
        }
    }


    /**
     *
     * @return
     */
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
