package app.domain.store;


import app.domain.adapter.Sortable;
import app.domain.model.Client;
import app.domain.model.DateBirth;
import app.domain.model.Exceptions.InvalidClientException;
import app.domain.model.Exceptions.InvalidEmailException;
import app.mappers.dto.ClientDTO;
import auth.domain.model.Email;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class ClientStore implements Serializable {

    Client client = new Client("Georgia PEDDIE", 3196, 6000003196L, new DateBirth(3, 11, 1946), 2100003196, 91200003196L, new Email("GeorgiaPEDDIE2155@gmail.com"), "Feminine");
    private ArrayList<Client> clientList = new ArrayList<>(Collections.singleton(client));

    private boolean addClient(Client client) {
        return this.clientList.add(client);
    }
    public boolean saveClient(Client client) {
        return addClient(client);
    }

    public ArrayList<Client> getClientList(){
        if(this.clientList != null) {
            return this.clientList;
        }else throw new IllegalArgumentException("Client list is currently empty.");
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

    public int getTotalNumberOfClients() {
        return this.clientList.size();
    }

    /**
     * Method that gets the sorting algorithm specified in the configuration file
     * @return Sortable interface
     */
    public Sortable getSortingAlgorithm() {
        Properties props = new Properties(System.getProperties());

        try {
            InputStream in = new FileInputStream("src/main/resources/config.properties");
            props.load(in);
            in.close();
            System.setProperties(props);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String sortAlg = props.getProperty("sortingAlgorithm1");
        Class<?> oClass = null;
        try {
            switch (sortAlg) {
                case "BubbleSort":
                    oClass = Class.forName("app.domain.algorithms.BubbleSort");
                    break;
                case "InsertionSort":
                    oClass = Class.forName("app.domain.algorithms.InsertionSort");
                    break;
                default:
                    System.out.println("No algorithm found!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Sortable sortingAlgorithm = null;
        if(oClass != null ) {
            try {
                sortingAlgorithm = (Sortable) oClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else throw new NullPointerException("Sorting algorithm is null.");

        return sortingAlgorithm;
    }

    public Client createClient(String name, long cardNumber, long nhsId, String dateBirth, long TIN, long phoneNumber, String address, String email) {
        String[] data = dateBirth.split("/");
        int ano = Integer.parseInt(data[0]);
        int mes = Integer.parseInt(data[1]);
        int dia = Integer.parseInt(data[2]);
        return new Client(name, cardNumber, nhsId, new DateBirth(ano, mes, dia), TIN, phoneNumber, address, new Email(email));
    }
}
