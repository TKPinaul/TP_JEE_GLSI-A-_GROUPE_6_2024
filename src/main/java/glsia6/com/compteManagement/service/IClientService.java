package glsia6.com.compteManagement.service;

import glsia6.com.compteManagement.dto.ClientDto;
import glsia6.com.compteManagement.entity.Client;
import glsia6.com.compteManagement.exception.ClientNotFoundException;

import java.util.List;

public interface IClientService {
    public List<ClientDto> getAllCLients();

    public ClientDto getOneClient(int clientId) throws ClientNotFoundException;


    ClientDto saveClient(ClientDto clientDto);

    ClientDto updateClient(ClientDto clientDto);

    public void deleteClient(int clientId);
}
