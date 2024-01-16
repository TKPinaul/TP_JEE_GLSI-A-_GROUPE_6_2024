package glsia6.com.compteManagement.service;

import glsia6.com.compteManagement.entity.Client;
import glsia6.com.compteManagement.entity.Compte;

import java.util.List;

public interface IClientService {
    public List<Client> getAllCLients();

    public Client getOneClient(int id);

    public Client saveClient(Client client);

    public void deleteClient(int id);
}
