package app.ui.console;

import app.controller.App;
import app.controller.RecordTestResultController;

import java.util.List;
import java.util.Scanner;

public class RecordTestResultUI implements Runnable {
    RecordTestResultController recordTestResultController = new RecordTestResultController(App.getInstance().getCompany());
    List<String> parameters;
    int selectedParameter;
    List<String> barcodes;
    List<String> paramCodes;
    List<String> results;
    List<String> metrics;

    Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        boolean validBarcode = false;
        boolean validAnalysis = false;

        if (!displayTests()) return;

        while (!validBarcode) {
            validBarcode = true;
            try { parameters = requestBarcode(); }
            catch (Exception ex) {
                System.out.println("Invalid Barcode: " + ex.getMessage());
                validBarcode = false;
            }
        }

        while (parameters.size() != 0) {
            while (!selectParameters()) selectParameters();

            while (!validAnalysis) {
                validAnalysis = true;
                try { insertParameterAnalysis(); }
                catch (Exception ex) {
                    System.out.println("Invalid Analysis Data: " + ex.getMessage());
                    validAnalysis = false;
                }
            }
            parameters.remove(selectedParameter);
        }

        displayConfirmation();
    }

    private boolean displayTests() {
        List<String> tests = recordTestResultController.getTestsWithCollectedSamples();

        if(tests.isEmpty()) {
            System.out.println("There are currently no tests available for analysis.");
            return false;
        }
        System.out.println("Please check below the tests available for analysis:");

        for (String test : tests)
            System.out.println(test);

        System.out.println("");
        return true;
    }

    private List<String> requestBarcode() {
        System.out.println("Please insert a sample barcode:");
        barcodes.set(selectedParameter, sc.nextLine());

        return recordTestResultController.getTestParameters(barcodes.get(selectedParameter));
    }

    private boolean selectParameters() {
        System.out.println("Please select a parameter:");
        int optionNum = 1;
        for(String parameter: parameters) {
            System.out.println(optionNum + " - " + parameter);
            optionNum++;
        }
        int optionSelect = Integer.parseInt(sc.nextLine());
        if (optionSelect >= optionNum || optionSelect < 1) {
            System.out.println("Invalid option!");
            return false;
        }
        selectedParameter = optionSelect;
        return true;
    }

    private void insertParameterAnalysis() {
        System.out.println("Insert result value:");
        results.set(selectedParameter, sc.nextLine());

        System.out.println("Insert metric:");
        metrics.set(selectedParameter, sc.nextLine());

        paramCodes.set(selectedParameter,
                recordTestResultController.getParamCodeFromString(parameters.get(selectedParameter - 1)));

        recordTestResultController.createTestParameterResult(paramCodes.get(selectedParameter), results.get(selectedParameter), metrics.get(selectedParameter));
    }

    private void displayConfirmation() {
        System.out.printf("Not implemented yet");
    }
}
