package app.ui.console;

import app.controller.CreateReportController;
import app.domain.model.Exceptions.InvalidTestCodeException;
import app.domain.model.Exceptions.InvalidTextReportException;
import app.domain.model.TestParameterResult;
import app.mappers.dto.ReportDTO;
import app.mappers.dto.TestDTO;

import java.util.List;
import java.util.Scanner;

public class CreateReportUI implements Runnable {

    private static final Scanner sc = new Scanner(System.in);
    private CreateReportController createReportController = new CreateReportController();

    public void run() {
        System.out.println("-----Create Report Menu-----");
        System.out.println("This is the list of tests ready to have a report made for them:");
        displayTestsListReadyForReport();
        getTestParameterResultsAndReferenceValues();
        createAndSaveReport();

    }

    private void displayTestsListReadyForReport() {
        List<TestDTO> testListReadyForReportDTO = createReportController.getTestsListReadyForReport();
        if (testListReadyForReportDTO.isEmpty()) {
            System.out.println("There are no tests ready for a report");
        } else {
            for (TestDTO testDTO : testListReadyForReportDTO) {
                System.out.printf("Test's Code - Client's Name\n%s - %s\n"
                        , testDTO.getTestCode(), testDTO.getClient().getName());
            }
        }
    }

    private void getTestParameterResultsAndReferenceValues() {
        System.out.println("Please choose which test you want to see by its code");
        boolean valid = false;
        while (!valid) {
            try {
                String testCode = sc.nextLine();
                for (TestParameterResult testParameterResult : createReportController.getTestParametersResultsByCode(testCode)) {
                    System.out.printf("%s - %s\n", testParameterResult, testParameterResult.getRefValue());
                }
                valid = true;
            } catch (InvalidTestCodeException e) {
                System.out.println(e.getMessage());
                System.out.println("Please, choose another code");
            }
        }
    }

    private void createAndSaveReport() {
        boolean valid = false;
        boolean loop = false;
        System.out.println("Please type in your diagnosis");
        String textDiagnosis = sc.nextLine();
        while (!valid) {
            try {
                System.out.println("Please type in your report (400 words)");
                String textReport = sc.nextLine();
                while (!loop) {
                    System.out.println("Are you sure this is the report you want? (Y/N)");
                    String confirmation = sc.nextLine();
                    if (confirmation.toLowerCase().equals("y")) {
                        loop = true;
                        createReportController.createReport(new ReportDTO(textDiagnosis, textReport));
                        createReportController.saveReport();
                        valid = true;
                    } else if (confirmation.toLowerCase().equals("n")) {
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
