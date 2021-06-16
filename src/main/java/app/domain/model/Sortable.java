package app.domain.model;

import java.util.List;

public interface Sortable {

    public Client[] sortByTin(List<Client> clientList);

    public Client[] sortByName(List<Client> clientList);
}
