package app.ui.console;

import app.controller.CreateReportController;
import app.domain.model.exceptions.InvalidTestCodeException;
import app.domain.model.exceptions.InvalidTextReportException;
import app.domain.model.TestParameter;
import app.mappers.dto.ReportDTO;
import app.mappers.dto.TestDTO;

import java.util.List;
import java.util.Scanner;

public class CreateReportUI implements Runnable {

    /**
     * Scanner for the user input
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Report controller
     */
    private final CreateReportController createReportController = new CreateReportController();

    /**
     * Responsible for running the application
     */
    public void run() {
        boolean loop = true;
        System.out.println("-----Create Report Menu-----");
        if (createReportController.getTestsListReadyForReport().isEmpty()) {
            System.out.println("There are no tests ready for a report!");
            loop = false;
        }
        while (loop) {
            System.out.println("This is the list of tests ready to have a report made for them:");
            displayTestsListReadyForReport();
            getTestParameterResultsAndReferenceValues();
            createAndSaveReport();
            System.out.println("Do you want to create more reports? (yes/no)");
            boolean confirm = false;
            while (!confirm) {
                String confirmation = sc.nextLine();
                if (confirmation.equalsIgnoreCase("no")) {
                    loop = false;
                    confirm = true;
                } else if (confirmation.equalsIgnoreCase("yes")) {
                    if (createReportController.getTestsListReadyForReport().isEmpty()) {
                        System.out.println("There are no more tests ready for a report!");
                        loop = false;
                    }
                    confirm = true;
                } else {
                    System.out.println("Please insert a valid confirmation");
                }
            }
        }
    }

    /**
     * Displays a list of tests that are ready to get a report made for them
     */
    private void displayTestsListReadyForReport() {
        List<TestDTO> testListReadyForReportDTO = createReportController.getTestsListReadyForReport();
        for (TestDTO testDTO : testListReadyForReportDTO) {
            System.out.printf("| Test's Code | Client's Name |\n| %s | %s |\n"
                    , testDTO.getTestCode(), testDTO.getClient().getName());
        }
    }

    /**
     * Getter for the test parameter results and respective reference values
     */
    private void getTestParameterResultsAndReferenceValues() {
        System.out.println("Please choose which test you want to see by its code:");
        boolean valid = false;
        while (!valid) {
            try {
                String testCode = sc.nextLine();
                for (TestParameter testParameter : createReportController.getTestParametersByCode(testCode)) {
                    System.out.printf("| Test Parameter's Result Name | Test Parameter's Result Value | Reference Values Range |\n| %s | %s %s | [%s %s ; %s %s] |\n", testParameter.getParameter().getParameterName(), testParameter.getResult().getValue(), testParameter.getResult().getMetric(), testParameter.getResult().getRefValue().getMinValue(), testParameter.getResult().getMetric(), testParameter.getResult().getRefValue().getMaxValue(), testParameter.getResult().getMetric());
                }
                valid = true;
            } catch (InvalidTestCodeException e) {
                System.out.println(e.getMessage());
                System.out.println("Please, choose another code");
            }
        }
    }

    /**
     * Creates and saves the report with the diagnosis and report written by the user
     */
    private void createAndSaveReport() {
        boolean valid = false;
        System.out.println("Please type in your diagnosis:");
        String textDiagnosis = sc.nextLine();
        while (!valid) {
            boolean loop = false;
            try {
                System.out.println("Please type in your report (400 words):");
                String textReport = sc.nextLine();
                while (!loop) {
                    System.out.println("Are you sure this is the report you want? (yes/no)");
                    String confirmation = sc.nextLine();
                    if (confirmation.equalsIgnoreCase("yes")) {
                        loop = true;
                        createReportController.createReport(new ReportDTO(textDiagnosis, textReport));
                        createReportController.saveReport();
                        System.out.println("Your report was saved to the test!");
                        valid = true;
                    } else if (confirmation.equalsIgnoreCase("no")) {
                        createAndSaveReport();
                    } else {
                        System.out.println("Please type a valid confirmation");
                    }
                }
            } catch (InvalidTextReportException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
