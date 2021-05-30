package app.domain.store;


import app.domain.model.Client;
import app.domain.model.Employee;
import app.domain.model.Exceptions.InvalidEmployeeException;

import java.util.ArrayList;

public class ClientStore {
    private ArrayList<Client> clientList = new ArrayList<>();

    private boolean addClient(Client client) {
        return this.clientList.add(client);
    }
    public boolean saveClient(Client client) {
        return addClient(client);
    }

    public Client getClientByCardNumber(long cardNumber){
        for (Client client : clientList) {
            if (client.getCardNumber() == cardNumber) {
                return client;
            }
        }
        System.out.println("There is no Client with that Card Number");
        return null;
    }
    public Client getClientByTINumber(long taxNumber){
        for (Client client : clientList) {
            if (client.getTIN() == taxNumber) {
                return client;
            }
        }
        System.out.println("There is no Client with that Card Number");
        return null;
    }

}
