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
        System.out.println("Please introduce Client Card Number");
        long clientCardNumber = sc.nextLong();
        registerTestController.setClientByCardNumber(clientCardNumber);
        System.out.println("Is this the correct Client? (Type Yes/No)");
        System.out.println("Client name: " + client.getName());
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
                registerTestController.setParameterByName(parametersDtos.get(optionOfTestParameter).getName());
                registerTestController.saveParametersAndCategories();
                System.out.println("Would you like to add more parameters in that Category? Type Yes/No");
                String awnser = sc.nextLine();
                boolean validAwnser;
                do {
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
            String awnser = sc.nextLine();
            boolean validAwnser;
            do {
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
        registerTestController.createTest();

    }


}
