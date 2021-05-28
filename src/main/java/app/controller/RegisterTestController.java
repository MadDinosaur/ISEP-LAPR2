package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.TestType;
import app.domain.store.TestTypeStore;
import app.mappers.TestTypeMapper;
import app.mappers.dto.TestTypeDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RegisterTestController {

    private final Company company;
    private TestTypeStore testTypeStore;

    public RegisterTestController(){
        this.company = App.getInstance().getCompany();
        this.testTypeStore = App.getInstance().getCompany().getTestTypeStore();
    }

    public Client getClientByCardNumber(long cardNumber){
        return company.getClientStore().getClientByCardNumber(cardNumber);
    }

    public List<TestTypeDto> testTypeList(){
        this.testTypeStore = company.getTestTypeStore();
        List<TestType> testTypes = testTypeStore.getTestTypeList();
        TestTypeMapper testTypeMapper = new TestTypeMapper();
        return testTypeMapper.toDto(testTypes);
    }
}
