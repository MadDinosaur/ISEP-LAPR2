package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Exceptions.*;
import app.mappers.ClientMapper;
import app.mappers.dto.ClientDTO;

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

        if (!company.getAuthFacade().changeUserEmail(client.getEmail().toString(), clientData.getEmail()))
            throw new InvalidClientException("Unable to change client e-mail!");
        if (!company.getAuthFacade().changeUserPwd(client.getEmail().toString(), clientData.getPassword()))
            throw new InvalidClientException("Unable to change password!");
        company.getClientStore().updateClientData(client, clientData);
    }
}
