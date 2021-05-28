package app.ui.console;

import app.controller.CreateReportController;

import java.util.Scanner;

public class CreateReportUI implements Runnable {

    private static final Scanner sc = new Scanner(System.in);
    private CreateReportController createReportController;

    public void run() {
        System.out.println("-----Create Report Menu-----");
        System.out.println("This is the list of tests ready to have a report made for them:");
        createReportController.getTestsListReadyForReport();
    }

    public void displayTestsListReadyForReport() {
        System.out.println(createReportController.getTestsListReadyForReport());
    }
}
