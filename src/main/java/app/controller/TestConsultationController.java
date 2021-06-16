package app.controller;

import app.domain.model.*;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;
import app.mappers.ClientMapper;
import app.mappers.TestMapper;
import app.mappers.dto.ClientDTO;
import app.mappers.dto.TestDTO;

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

    public List<ClientDTO> getClientList(String sortMethod) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.clientStore = this.company.getClientStore();
        this.clientList = this.clientStore.getClientList();
        Sortable sortingAlgorithm = this.company.getSortingAlgorithm();
        Client[] clientArr = null;
        if(this.clientList != null) {
            if (sortMethod.equalsIgnoreCase("name")) {
                clientArr = sortingAlgorithm.sortByName(this.clientList);
            } else {
                clientArr = sortingAlgorithm.sortByTin(this.clientList);
            }
        }else throw new IllegalArgumentException("Client list is empty!");
        List<ClientDTO> clientDTOList = null;
        ClientMapper clientMapper = new ClientMapper();
            for (int i = 0; i < clientArr.length; i++) {
                clientDTOList.add(clientMapper.toDTO(clientArr[i]));
            }

        return clientDTOList;
    }

    public String[] getClientTestListWithResults(Client client){
        this.testStore = this.company.getTestStore();
        List<Test> clientTestList = testStore.getValidatedTestsByClient(client);
        TestMapper testMapper = new TestMapper();
        List<TestDTO> clientTestListDTO = null;
        String[] clientTestListWithResults = null;
        for (Test test : clientTestList){

            testMapper.toDTO(test);
        }
        return clientTestListWithResults;
    }

    public List<TestParameterResult> displayTestResults(String testCode){
        Test test = testStore.getTestByCode(testCode);
        return test.getTestParamResults();
    }


}
