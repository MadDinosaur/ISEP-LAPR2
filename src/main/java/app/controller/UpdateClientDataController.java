package app.controller;

import app.domain.model.Company;
import app.domain.model.Exceptions.InvalidCardNumberException;
import app.domain.model.Exceptions.InvalidNhsIdException;
import app.domain.model.Exceptions.InvalidPhoneNumberException;
import app.domain.model.Exceptions.InvalidTINException;
import app.mappers.ClientMapper;
import app.mappers.dto.ClientDTO;
import app.domain.model.Client;

public class UpdateClientDataController {
    private Company company = App.getInstance().getCompany();
    private Client client;

    public ClientDTO getClient(String email) {
        ClientMapper map = new ClientMapper();
        client = company.getClientStore().getClientByEmail(email);
        return map.toDTO(client);
    }

    public void updateClientData(ClientDTO clientData) {
        RegisterClientController registerClientController = new RegisterClientController();

        registerClientController.setNameClient(clientData.getName());
        try { registerClientController.setCardNumber(Long.parseLong(clientData.getCardNumber()));
        } catch (NumberFormatException ex) { throw new InvalidCardNumberException("Citizen Card Number must be a number!");}
        try { registerClientController.setNhsId(Long.parseLong(clientData.getNhsId()));
        } catch (NumberFormatException ex) { throw new InvalidNhsIdException("NHS ID must be a number!");}
        registerClientController.setDate(clientData.getDateBirth());
        try { registerClientController.setTIN(Long.parseLong(clientData.getTIN()));
        } catch (NumberFormatException ex) { throw new InvalidTINException("Tax Identification Number must be a number!");}
        try { registerClientController.setPhoneNumber(Long.parseLong(clientData.getPhoneNumber()));
        } catch (NumberFormatException ex) { throw new InvalidPhoneNumberException("Phone number must be a number!");}
        registerClientController.setEmail(clientData.getEmail());

        company.getClientStore().updateClientData(client, clientData);
    }
}
