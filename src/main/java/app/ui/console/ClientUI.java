package app.ui.console;


import app.controller.App;
import app.domain.model.Category;
import app.domain.model.Client;
import app.domain.model.Parameter;
import app.domain.model.Test;
import auth.domain.model.Email;

import java.util.List;
import java.util.Scanner;

public class ClientUI implements Runnable{

    private final Scanner sc = new Scanner(System.in);

    /**
     *
     */
    public ClientUI() {
        //Empty constructor
    }

    public void run() {
        System.out.println("You are registered as a Client");
        System.out.println("Here you will se your tests");
        Client clientUser = getClient();
        List<Test> testsOfUser = getTests(clientUser);
        for(Test test : testsOfUser){
            System.out.println("############ Test ############");
            System.out.println("Nhs Code: "+ test.getNhsCode());
            System.out.println("Test Code: "+ test.getTestCode());
            System.out.println("Test Type: " + test.getTestType().getCode());
            for(Category category : test.getTestType().getCategories()){
                System.out.println("Category :" + category.getCategoryName());
                for(Parameter parameter : category.getParameterList()){
                    System.out.println("Parameter :" + parameter.getParameterName());
                }
            }
        }
        System.out.println("Press any key to return");
        sc.nextLine();
    }
    public Client getClient(){
        Email userEmail = App.getInstance().getCurrentUserSession().getUserId();
        return App.getInstance().getCompany().getClientStore().getClientById(userEmail);
    }
    public List<Test> getTests(Client client){
        return client.getClientTests();
    }
}
