package app.ui.console;

import app.controller.RegisterTestController;
import app.domain.model.Category;
import app.domain.model.Client;
import app.DTO.TestTypeDto;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterTestUI implements Runnable{

    private RegisterTestController registerTestController = new RegisterTestController();
    private Client client;
    private List<TestTypeDto> testTypeList = new ArrayList<TestTypeDto>();
    private

    Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        System.out.println("\n");
        System.out.println("######## Test Registration ########");
        System.out.println("Please introduce Client Card Number");
        long clientCardNumber = sc.nextLong();
        this.client = registerTestController.getClientByCardNumber(clientCardNumber);
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
        TestTypeDto testTypeOfTest = testTypeList.get(optionOfTestType-1);
        System.out.println("Please select a Test Category");
        index = 0;
        for(Category categoryOfTestType : testTypeOfTest.getCategories()){
            index++;
            System.out.println(index + "- " + categoryOfTestType.getCategoryName());
        }




    }


}
