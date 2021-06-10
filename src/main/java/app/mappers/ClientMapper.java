package app.mappers;

import app.mappers.dto.ClientDTO;
import app.domain.model.Client;

public class ClientMapper {

    public ClientDTO toDTO(Client client) {
        String name = client.getName();
        String cardNumber = String.valueOf(client.getCardNumber());
        String nhsId = String.valueOf(client.getNhsId());
        String dateBirth = client.getDateBirth().toString();
        String TIN = String.valueOf(client.getTIN());
        String phoneNUmber = String.valueOf(client.getPhoneNumber());
        String email = client.getEmail().toString();
        String sex = client.getSex();

        return new ClientDTO(name, cardNumber, nhsId, dateBirth, TIN, phoneNUmber, email, "", sex);
    }
}
