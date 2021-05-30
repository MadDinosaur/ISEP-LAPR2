package app.ui.console;

import app.controller.RegisterTestController;
import app.domain.model.Category;
import app.domain.model.Client;
import app.mappers.ParamMapper;
import app.mappers.dto.ParamDTO;
import app.mappers.dto.TestCategoryDto;
import app.mappers.dto.TestTypeDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterTestUI implements Runnable {

    private RegisterTestController registerTestController = new RegisterTestController();
    private Client client;
    private List<TestTypeDto> testTypeList = new ArrayList<TestTypeDto>();
    private TestTypeDto testTypeOfTest;
    private List<TestCategoryDto> testCategoryDtos = new ArrayList<>();
    private TestCategoryDto testCategory;
    private List<ParamDTO> parametersDtos = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        System.out.println("\n");
        System.out.println("######## Test Registration ########");
        System.out.println("Please introduce Client Card Number  (To leave type exit)");
        boolean clientIsCorrect = false;
        do {
            boolean clientExists;
            do {
                long clientCardNumber = sc.nextLong();
                registerTestController.setClientByCardNumber(clientCardNumber);
                client = registerTestController.getClient();
                if (client == null) {
                    System.out.println("This client does not exist, please introduce a valid Card Number");
                    clientExists = false;
                } else {
                    clientExists = true;
                }
            } while (!clientExists);
            System.out.println("Is this the correct Client? (Type Yes/No)");  //resolver AQUI
            System.out.println("Client name: " + client.getName());
            boolean validAwnser;
            do {
            String clientIsCorrectAnswer = sc.next();
                if (clientIsCorrectAnswer.equalsIgnoreCase("yes")) {
                    System.out.println("chehou aqui");
                    clientIsCorrect = true;
                    validAwnser = true;
                } else {
                    if (clientIsCorrectAnswer.equalsIgnoreCase("no")) {
                        clientIsCorrect = false;
                        validAwnser = true;
                    } else {
                        System.out.println("Please introduce the a valid awnser (Yes/No)");
                        validAwnser = false;
                    }
                }
            }while (!validAwnser);
        }while (!clientIsCorrect);
        System.out.println("Please select a Test Type");
        this.testTypeList = registerTestController.testTypeList();
        int index = 0;
        for (TestTypeDto testTypeDto : testTypeList) {
            index++;
            System.out.println(index + "- " + testTypeDto.getCode());
        }
        int optionOfTestType = sc.nextInt();
        testTypeOfTest = testTypeList.get(optionOfTestType - 1);
        //Category
        boolean repeatCategory = false;
        do {
            System.out.println("Please select a Test Category");
            registerTestController.setTestTypeByCode(testTypeOfTest.getCode());
            testCategoryDtos = registerTestController.getListOfTestCategories();
            index = 0;
            for (TestCategoryDto categoryDto : testCategoryDtos) {
                index++;
                System.out.println(index + "- " + categoryDto.getName());
            }
            int optioOfTestCategory = sc.nextInt();
            testCategory = testCategoryDtos.get(optioOfTestCategory - 1);
            registerTestController.setCategoryByName(testCategory.getName());
            //Parameter
            boolean repeatParameter = false;
            do {
                System.out.println("Please select a Test Parameter");
                parametersDtos = registerTestController.getListOfTestParameters();
                index = 0;
                for (ParamDTO parameterDTO : parametersDtos) {
                    index++;
                    System.out.println(index + "- " + parameterDTO.getName());
                }
                int optionOfTestParameter = sc.nextInt();
                registerTestController.setParameterByName(parametersDtos.get(optionOfTestParameter-1).getName());
                registerTestController.saveParametersAndCategories();
                System.out.println("Would you like to add more parameters in that Category? Type Yes/No");


                boolean validAwnser;
                do {
                    String awnser = sc.next();
                    if (awnser.equalsIgnoreCase("yes")) {
                        repeatParameter = true;
                        validAwnser = true;
                    } else {
                        if (awnser.equalsIgnoreCase("no")) {
                            repeatParameter = false;
                            validAwnser = true;
                        } else {
                            System.out.println("That is not a valid awnser! Please type Yes/No");
                            validAwnser = false;
                        }
                    }

                } while (!validAwnser);
            } while (repeatParameter);

            System.out.println("Would you like to add more Categories of that Test Type? Type Yes/No");

            boolean validAwnser;
            do {
                String awnser = sc.next();
                if (awnser.equalsIgnoreCase("yes")) {
                    repeatCategory = true;
                    validAwnser = true;
                } else {
                    if (awnser.equalsIgnoreCase("no")) {
                        repeatCategory = false;
                        validAwnser = true;
                    } else {
                        System.out.println("That is not a valid awnser! Please type Yes/No");
                        validAwnser = false;
                    }
                }
            } while (!validAwnser);
        }while (repeatCategory);

        System.out.println("Please confirm the Test information");
        String[] codesOfTest = registerTestController.createTest();
        System.out.println(registerTestController.getToSaveTestType().getCode());
        System.out.println(registerTestController.getToSaveTestType().getCategories().get(0).getCategoryName());
        System.out.println(registerTestController.getToSaveTestType().getCategories().get(0).getParameterList().get(0).getParameterName());

        String generatedTestCode= codesOfTest[0];
        String generatedNhsCode= codesOfTest[1];
        System.out.println("The test was registered with the \n" +
                "Test Code: " + generatedTestCode +"\n"+
                "Nhs Code: " + generatedNhsCode);
    }


}
