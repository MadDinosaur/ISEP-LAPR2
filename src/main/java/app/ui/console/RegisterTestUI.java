package app.ui.console;

import app.controller.RegisterClientController;
import app.controller.RegisterTestController;
import app.domain.model.Client;
import app.domain.model.TestType;
import app.mappers.dto.TestTypeDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterTestUI implements Runnable{

    private RegisterTestController registerTestController = new RegisterTestController();
    private Client client;
    private List<TestTypeDto> testTypeList = new ArrayList<TestTypeDto>();

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
        this.testTypeList = registerTestController.testTypeList();
        for (TestTypeDto testTypeDto : testTypeList) {
            System.out.println(testTypeDto.getDescription());
        }

    }


}
