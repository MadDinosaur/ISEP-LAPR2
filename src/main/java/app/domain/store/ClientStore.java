package app.domain.store;

import app.domain.model.Client;
import auth.domain.model.Email;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ClientStore {
    private Set<Client> store = new HashSet<Client>();


    public boolean add(Client client)
    {
        if (client != null) {
            if (!exists(client))
                return this.store.add(client);
        }
        return false;
    }

    public boolean remove(Client client)
    {
        if (client != null)
            return this.store.remove(client);
        return false;
    }

    public Optional<Client> getById(String email)
    {
        return this.getById(new Email(email));
    }

    public Optional<Client> getById(Email email)
    {
        for(Client client: this.store)
        {
            if(client.hasEmail(email))
                return Optional.of(client);
        }
        return Optional.empty();
    }

    public boolean exists(String email)
    {
        Optional<Client> result = getById(email);
        return result.isPresent();
    }


    public boolean exists(Email email)
    {
        Optional<Client> result = getById(email);
        return result.isPresent();
    }

    public boolean exists(Client client)
    {
        return this.store.contains(client);
    }
    public Optional<Client> getByTIN (long TIN){
        for(Client client: this.store)
        {
            if(client.hasTIN(TIN))
                return Optional.of(client);
        }
        return Optional.empty();
    }
    public boolean existsTIN(long TIN){
        Optional<Client> result = getByTIN(TIN);
        return result.isPresent();
    }
    public Optional<Client> getByCardNumber (long cardNumber){
        for(Client client: this.store)
        {
            if(client.hasCardNumber(cardNumber))
                return Optional.of(client);
        }
        return Optional.empty();
    }
    public boolean existsCardNumber(long cardNumber){
        Optional<Client> result = getByCardNumber(cardNumber);
        return result.isPresent();
    }
    public Optional<Client> getByNhsId (long nshId){
        for(Client client: this.store)
        {
            if(client.hasNhsID(nshId))
                return Optional.of(client);
        }
        return Optional.empty();
    }
    public boolean existsNhsId(long nhsId){
        Optional<Client> result = getByNhsId(nhsId);
        return result.isPresent();
    }
    public Optional<Client> getByPhoneNumber (long phoneNumber){
        for(Client client: this.store)
        {
            if(client.hasPhoneNumber(phoneNumber))
                return Optional.of(client);
        }
        return Optional.empty();
    }
    public boolean existsPhoneNumber(long phoneNumber){
        Optional<Client> result = getByPhoneNumber(phoneNumber);
        return result.isPresent();
    }
}

