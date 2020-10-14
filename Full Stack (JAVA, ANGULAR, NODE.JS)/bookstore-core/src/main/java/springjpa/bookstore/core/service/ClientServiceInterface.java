package springjpa.bookstore.core.service;


import springjpa.bookstore.core.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServiceInterface {
    List<Client> getAllClients();

    Client saveClient(Client client);

    Client updateClient(Long id, Client client);

    Client getClientById(Long id);

    void deleteClient(Long id);
}
