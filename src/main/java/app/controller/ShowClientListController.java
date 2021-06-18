package app.controller;

import app.domain.adapter.Sortable;
import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.store.ClientStore;
import app.mappers.ClientMapper;
import app.mappers.dto.ClientDTO;

import java.lang.ref.Cleaner;
import java.util.List;

public class ShowClientListController {


    /**
     * Instance of company
     */
    private Company company;

    /**
     * Instance of client store
     */
    private ClientStore clientStore;

    /**
     * Instance of client list
     */
    private List<Client> clientList;

    /**
     * Constructor of TestConsultationController without parameters
     */
    public ShowClientListController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor of TestConsultationController with one parameter
     * @param company
     */
    public ShowClientListController(Company company){
        this.company = company;
    }

    /**
     * Method that gets and sorts the client list by the specified method
     * @param sortMethod
     * @return an ordered list of client dto
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public List<ClientDTO> getSortedClientList(String sortMethod) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.clientStore = this.company.getClientStore();
        this.clientList = this.clientStore.getClientList();
        Sortable sortingAlgorithm = this.clientStore.getSortingAlgorithm();
        Client[] clientArr;
        if(this.clientList != null) {
            if (sortMethod.equalsIgnoreCase("tin")) {
                clientArr = sortingAlgorithm.sortByTin(this.clientList);
            } else if(sortMethod.equalsIgnoreCase("name")){
                clientArr = sortingAlgorithm.sortByName(this.clientList);
            }else throw new IllegalAccessException("The specified ordering method doesn't exist.");
        }else throw new IllegalArgumentException("Client list is empty!");
        List<ClientDTO> clientDTOList = null;
        ClientMapper clientMapper = new ClientMapper();
        for (int i = 0; i < clientArr.length; i++) {
            clientDTOList.add(clientMapper.toDTO(clientArr[i]));
        }
        return clientDTOList;
    }

}
