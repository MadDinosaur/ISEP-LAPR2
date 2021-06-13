package app.controller;

import app.domain.model.*;
import app.domain.store.*;
import app.mappers.*;
import app.mappers.dto.*;

import java.util.List;

public class TestConsultationController {

    private Company company;

    private ClientStore clientStore;

    private List<Client> clientList;

    private TestStore testStore;

    public TestConsultationController() {
        this.company = App.getInstance().getCompany();
    }

    public TestConsultationController(Company company){
        this.company = company;
    }

    public List<ClientDTO> getClientList(){
        this.clientStore = this.company.getClientStore();
        this.clientList = this.clientStore.getClientList();
        List<ClientDTO> clientDTOList = null;
        ClientMapper clientMapper = new ClientMapper();
        for(Client client : this.clientList){
            clientDTOList.add(clientMapper.toDTO(client));
        }
        return clientDTOList;
    }

    public List<TestDTO> getClientTestList(Client client){
        this.testStore = this.company.getTestStore();
        List<Test> clientTestList = testStore.getValidatedTestsByClient(client);
        TestMapper testMapper = new TestMapper();
        List<TestDTO> clientTestListDTO = null;
        for (Test test : clientTestList){
            clientTestListDTO.add(testMapper.toDTO(test));
        }
        return clientTestListDTO;
    }

    public List<TestParameterResult> displayTestResults(String testCode){
        Test test = testStore.getTestByCode(testCode);
        return test.getTestParamResults();
    }


}
