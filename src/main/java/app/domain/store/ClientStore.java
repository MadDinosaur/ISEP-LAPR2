package app.domain.store;


import app.domain.model.Client;
import app.domain.model.Employee;
import app.domain.model.Exceptions.InvalidEmailException;
import app.domain.model.Exceptions.InvalidEmployeeException;
import auth.domain.model.Email;

import java.util.ArrayList;

public class ClientStore {
    private ArrayList<Client> clientList = new ArrayList<>();

    private boolean addClient(Client client) {
        return this.clientList.add(client);
    }
    public boolean saveClient(Client client) {
        return addClient(client);
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

    public Client getClientById(Email email){
        for (Client client : clientList) {
            if (client.getEmail().toString().equals(email.toString())) {
                return client;
            }
        }
        return null;
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


    public Client getClientByEmail(String email) {
        for (Client client : clientList)
            if (client.getEmail().toString().equalsIgnoreCase(email))
                return client;
        throw new InvalidEmailException("Unable to find client with e-mail " + email);
    }
}
