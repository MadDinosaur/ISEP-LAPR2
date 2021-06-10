package app.domain.store;


import app.domain.model.Client;
import app.domain.model.Employee;
import app.domain.model.Exceptions.InvalidClientException;
import app.domain.model.Exceptions.InvalidEmailException;
import app.domain.model.Exceptions.InvalidEmployeeException;
import app.mappers.dto.ClientDTO;
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

    public ArrayList<Client> getClientList(){
        return this.clientList;
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

    public void updateClientData(Client client, ClientDTO clientData) {
        if (!validateData(client, clientData)) throw new InvalidClientException("Client already exists!");
        client.updateData(clientData);
    }

    public boolean validateData(Client client, ClientDTO clientData) {
        Email newEmail = new Email(clientData.getEmail());
        long newCardNum = Long.parseLong(clientData.getCardNumber());
        long newNhsId = Long.parseLong(clientData.getNhsId());
        long newTIN = Long.parseLong(clientData.getTIN());

        for (Client cli: clientList) {
            if (cli.getEmail().equals(client.getEmail()))
                continue;

            if (cli.getEmail().equals(newEmail)
                || cli.getCardNumber() == newCardNum
                || cli.getNhsId() == newNhsId
                || cli.getTIN() == newTIN)
                return false;
        }
        return true;
    }

    public Client getClientByNhsID(Long nhsID) {
        for (Client client : clientList) {
            if (client.getNhsId() == nhsID) {
                return client;
            }
        }
        throw new InvalidClientException();
    }
}
