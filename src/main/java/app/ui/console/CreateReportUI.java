package app.ui.console;

import app.controller.CreateReportController;
import app.domain.model.Exceptions.InvalidTestCodeException;
import app.domain.model.Exceptions.InvalidTextReportException;
import app.mappers.dto.ReportDTO;

import java.util.Scanner;

public class CreateReportUI implements Runnable {

    private static final Scanner sc = new Scanner(System.in);
    private CreateReportController createReportController;

    public void run() {
        System.out.println("-----Create Report Menu-----");
        System.out.println("This is the list of tests ready to have a report made for them:");
        displayTestsListReadyForReport();
        getTestParameterResultsAndReferenceValues();
        createReport();

    }

    private void displayTestsListReadyForReport() {
        System.out.println(createReportController.getTestsListReadyForReport());
    }

    private void getTestParameterResultsAndReferenceValues() {
        System.out.println("Please choose which test you want to see by its code");
        boolean valid = false;
        while (!valid) {
            try {
                String testCode = sc.nextLine();
                createReportController.getTestParametersResultsByCode(testCode);
                createReportController.getTestParametersReferenceValues();
                valid = true;
            } catch (InvalidTestCodeException e) {
                System.out.println(e.getMessage());
                System.out.println("Please, choose another code");
            }
        }
    }

    private void createReport() {
        boolean valid = false;
        System.out.println("Please type in your diagnosis");
        String textDiagnosis = sc.nextLine();
        while (!valid) {
            try {
                System.out.println("Please type in your report (400 words)");
                String textReport = sc.nextLine();
                createReportController.createReport(new ReportDTO(textDiagnosis, textReport));
                valid = true;
            } catch (InvalidTextReportException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
