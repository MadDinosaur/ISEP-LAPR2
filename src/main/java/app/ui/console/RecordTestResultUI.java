package app.ui.console;

import app.controller.App;
import app.controller.RecordTestResultController;
import app.controller.RegisterClientController;
import app.domain.model.*;
import app.domain.model.Exceptions.UnregisteredBarcodeException;
import app.mappers.dto.ParamDTO;
import app.domain.model.Client;
import auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecordTestResultUI implements Runnable {
    RecordTestResultController recordTestResultController = new RecordTestResultController(App.getInstance().getCompany());
    List<ParamDTO> parameters;
    int selectedParameter;
    String barcode;
    String paramCode;
    String result;
    String metric;

    Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        loadDemoInfo();

        boolean validBarcode = false;
        boolean validAnalysis = false;

        if (!displayTests()) return;

        while (!validBarcode) {
            validBarcode = true;
            try { parameters = requestBarcode(); }
            catch (UnregisteredBarcodeException ex) {
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
            if(displayConfirmation())
                parameters.remove(selectedParameter - 1);
        }
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

    private List<ParamDTO> requestBarcode() {
        System.out.println("Please insert a sample barcode:");
        barcode = sc.nextLine();

        return recordTestResultController.getTestParameters(barcode);
    }

    private boolean selectParameters() {
        System.out.println("Please select a parameter:");
        int optionNum = 1;
        for(ParamDTO parameter: parameters) {
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
        result =  sc.nextLine();

        System.out.println("Insert metric:");
        metric = sc.nextLine();

        paramCode = parameters.get(selectedParameter - 1).getCode();

        recordTestResultController.createTestParameterResult(paramCode, result, metric);
    }

    private boolean displayConfirmation() {
        System.out.println("Checking information...");
        System.out.printf("Barcode no.: %s\n", barcode);
        System.out.printf("Parameter code: %s --> %s%s\n", paramCode, result, metric);
        System.out.println("Confirm? Y/N");
        String option = sc.nextLine();

        switch (option) {
            case "Y": case "y":
                if (recordTestResultController.saveTestParameterResult()) {
                    System.out.println("Result saved successfully!");
                    return true;
                }
                else {
                    System.out.println("Error saving result. Please try again.");
                    return false;
                }
            case "N": case "n":
                return false;
        }
        return false;
    }

    private void loadDemoInfo() {
        Company company = App.getInstance().getCompany();
        //Client
        Client client = new Client("Default Name", 1234567887654321L, 1098765432L, new DateBirth(01,01,2000), 1098765432L, 12345678901L, new Email("client@org.com"), "No sex assigned");
        company.getClientStore().saveClient(client);
        //TestType
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Hemogram", "HEM00", "Hemogram Description"));
        TestType testType = new TestType("Blood", "Blood Test Description", new CollectionMethod("Swab"), categories);
        //Parameters
        Category category = categories.get(0);
        category.saveParameter(category.createNewParameter("HB", "HB000", "Haemoglobin"));
        category.saveParameter(category.createNewParameter("WBC","WBC00",  "White Cell Count"));
        //Test
        Test test = new Test(client, testType, "test1234", "nhs1234");
        company.getTestStore().addTest(test);
        //Samples
        test.getSampleList().saveSample(new Sample("1234"));
        //Status
        test.setStateOfTestToSamplesCollected();

    }
}
