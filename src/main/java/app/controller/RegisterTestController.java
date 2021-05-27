package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.TestType;
import app.domain.store.TestTypeStore;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RegisterTestController {

    private final Company company;
    private final TestTypeStore testTypeStore;

    public RegisterTestController(){
        this.company = App.getInstance().getCompany();
        this.testTypeStore = App.getInstance().getCompany().getTestTypeStore();
    }

    public Client getClientByCardNumber(long cardNumber){
        return company.getClientStore().getClientByCardNumber(cardNumber);
    }

   /* public List<TestType> testTypeList(){
        return company.getTestTypeStore(
    }
*/
}
