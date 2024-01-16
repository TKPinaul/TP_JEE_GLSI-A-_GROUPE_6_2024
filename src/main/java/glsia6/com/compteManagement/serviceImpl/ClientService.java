package glsia6.com.compteManagement.serviceImpl;

import glsia6.com.compteManagement.entity.Client;
import glsia6.com.compteManagement.repository.ClientRepository;
import glsia6.com.compteManagement.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Override
    public List<Client> getAllCLients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getOneClient(int id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }


}
