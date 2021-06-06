package app.controller;

import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.model.TestParameterResult;
import app.mappers.TestMapper;
import app.mappers.dto.TestDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ShowTestResultController {
    private Company company = App.getInstance().getCompany();
    private Client client;

    public List<TestDTO> displayClientTests(String email) {
        client = company.getClientStore().getClientByEmail(email);
        List<Test> tests = company.getTestStore().getValidatedTestsByClient(client);
        return new TestMapper(tests).toDtoList();
    }
    
    public List<String> displayTestResults(String testCode) {
        Test test = company.getTestStore().getTestByCode(testCode);
        return test.getTestParamResults()
                .stream()
                .map(TestParameterResult :: toString)
                .collect(Collectors.toList());
    }
}
