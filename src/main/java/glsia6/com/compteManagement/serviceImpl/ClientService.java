package glsia6.com.compteManagement.serviceImpl;

import glsia6.com.compteManagement.dto.ClientDto;
import glsia6.com.compteManagement.entity.Client;
import glsia6.com.compteManagement.exception.ClientNotFoundException;
import glsia6.com.compteManagement.mappers.ClientMapperImpl;
import glsia6.com.compteManagement.repository.ClientRepository;
import glsia6.com.compteManagement.service.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapperImpl clientMapper;
    @Override
    public List<ClientDto> getAllCLients() {

        List<Client>ListClients =  clientRepository.findAll();
        List<ClientDto> clientDtos = ListClients.stream().map(client -> clientMapper.fromClient(client)).collect(Collectors.toList());
        /*List<ClientDto> clientDtos = new ArrayList<>();
        for (Client client:ListClients){
            ClientDto clientDto=clientMapper.fromClient(client);
            clientDtos.add(clientDto);
        }*/

        //ListClients.stream()
        return clientDtos;
    }

    @Override
    public ClientDto getOneClient(int clientId) throws ClientNotFoundException {
         Client client = clientRepository.findById(clientId).orElseThrow(()->new ClientNotFoundException("Ce client n'existe pas"));

         return clientMapper.fromClient(client);
    }


    @Override
    public ClientDto saveClient(ClientDto clientDto) {
        log.info("Enregistrement d'un nouveau client");
        Client client = clientMapper.fromClientDto(clientDto);
        Client savedClient = clientRepository.save(client);

        return clientMapper.fromClient(savedClient);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        log.info("Mise a jour d'un client");
        Client client = clientMapper.fromClientDto(clientDto);
        Client savedClient = clientRepository.save(client);

        return clientMapper.fromClient(savedClient);
    }

    @Override
    public void deleteClient(int clientId) {
        clientRepository.deleteById(clientId);
    }


}
