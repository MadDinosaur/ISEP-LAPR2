package app.domain.adapter;

import app.domain.model.Client;

import java.util.List;

public interface Sortable {

    Client[] sortByTin(List<Client> clientList);

    Client[] sortByName(List<Client> clientList);
}
