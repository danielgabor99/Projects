package springjpa.bookstore.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springjpa.bookstore.core.domain.Client;
import springjpa.bookstore.core.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientServiceInterface {

    public static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<Client> getAllClients() {
        log.trace("getAllClients --- method entered");

        List<Client> result = clientRepository.findAll();

        log.trace("getAllClients: result={}", result);

        return result;
    }

    @Override
    public Client saveClient(Client client) {
        log.trace("saveClient --- method entered");

        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateClient(Long id, Client client) {
        log.trace("updateClient --- method entered");
        Client update = clientRepository.findById(id).orElse(client);
        update.setClientName(client.getClientName());
        update.setAvailableAmount(client.getAvailableAmount());
        log.trace("updateClient --- updated{}", update);
        return update;
    }

    @Override
    public Client getClientById(Long id) {
        log.trace("getClientById --- method entered");
        Client update = clientRepository.findById(id).orElse(new Client("", 0));
        log.trace("getClientById --- getClient{}", update);
        return update;
    }

    @Override
    public void deleteClient(Long id) {
        log.trace("deleteClient --- method entered");
        clientRepository.deleteById(id);
    }
}
