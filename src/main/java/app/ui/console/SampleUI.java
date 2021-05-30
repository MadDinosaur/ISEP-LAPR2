package app.ui.console;

import app.controller.SampleController;
import app.domain.model.Sample;
import app.domain.store.SampleList;
import app.mappers.dto.TestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SampleUI implements Runnable {
    static Scanner input = new Scanner(System.in);
    private SampleController SC = new SampleController();

    public void run() {

        List<TestDTO> TestList = SC.getTestList();
        String testCode = TestCodeSelection(TestList);

        SampleList sampleList = null;
        try {
            System.out.println("Insert number of samples:");
            sampleList = setSampleNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SC.criar(testCode, sampleList);
    }

    public String TestCodeSelection(List<TestDTO> testList) {
        int index = 0;
        for (TestDTO testDTO : testList) {
            System.out.println(index + ":");
            System.out.print(testDTO.getTestCode());
            index++;
        }

        System.out.println();
        System.out.println("Choose a test code by inserting its index:");
        int k = input.nextInt();
        input.nextLine();

        System.out.println("Is the test code " + testList.get(k).getTestCode() + "? (yes/no)");

        boolean confirm = confirmation();
        if(confirm) {
            return testList.get(k).getTestCode();
        } else {
            TestCodeSelection(testList);
        }
        return null;
    }

    public SampleList setSampleNumber() throws Exception {
        int n = input.nextInt();
        input.nextLine();

        System.out.println("Number of samples:" + n);
        System.out.println("Is this information correct? (type yes/no)");

        boolean confirm = confirmation();
        if (confirm) {
            return SC.setSampleNumber(n);
        } else {
            System.out.println("Enter the correct number of samples:");
            setSampleNumber();
        }
        return null;
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
