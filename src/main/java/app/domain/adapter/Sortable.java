package app.domain.adapter;

import app.domain.model.Client;

import java.util.List;

public interface Sortable {

    public Client[] sortByTin(List<Client> clientList);

    public Client[] sortByName(List<Client> clientList);
}
