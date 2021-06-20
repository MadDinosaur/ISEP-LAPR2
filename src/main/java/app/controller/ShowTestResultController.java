package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.model.TestParameter;
import app.mappers.TestMapper;
import app.mappers.TestParamMapper;
import app.mappers.dto.TestDTO;
import app.mappers.dto.TestParameterDTO;

import java.util.List;

public class ShowTestResultController {
    private Company company = App.getInstance().getCompany();
    private Client client;

    public List<TestDTO> displayClientTests(String email) {
        client = company.getClientStore().getClientByEmail(email);
        List<Test> tests = company.getTestStore().getValidatedTestsByClient(client);
        return new TestMapper(tests).toDtoList();
    }
    
    public List<TestParameterDTO> displayTestResults(String testCode) {
        Test test = company.getTestStore().getTestByCode(testCode);
        List<TestParameter> testParams = test.getTestParamList().getTestParameters();
        return new TestParamMapper(testParams).toDTOList();
    }
}
