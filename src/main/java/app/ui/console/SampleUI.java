package app.ui.console;

import app.controller.SampleController;
import app.domain.model.Test;
import app.mappers.dto.TestDTO;

import java.util.List;
import java.util.Scanner;

public class SampleUI implements Runnable {
    static Scanner input = new Scanner(System.in);
    private SampleController SC = new SampleController();

    public void run() {

        List<TestDTO> TestList = SC.getTestList();
        TestCodeSelection(TestList);

        System.out.println("Insert number of samples:");
        try {
            setSampleNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void TestCodeSelection(List<TestDTO> testList) {
        int index = 0;
        for (TestDTO testDTO : testList) {
            System.out.println(index + ":");
            System.out.print(testDTO.getTestCode());
            index++;
        }

        System.out.println("Choose a test code by inserting its index:");
        int k = input.nextInt();

        System.out.println("Is the test code" + testList.get(k).getTestCode() + "?");

        if(confirmation()) {
            SC.setTestCode(testList.get(k).getTestCode());
        } else {
            TestCodeSelection(testList);
        }
    }

    public void setSampleNumber() throws Exception {
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
