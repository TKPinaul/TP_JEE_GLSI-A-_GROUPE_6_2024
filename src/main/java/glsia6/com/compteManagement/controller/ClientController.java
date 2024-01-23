package glsia6.com.compteManagement.controller;

import glsia6.com.compteManagement.dto.ClientDto;
import glsia6.com.compteManagement.entity.Client;
import glsia6.com.compteManagement.exception.ClientNotFoundException;
import glsia6.com.compteManagement.serviceImpl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public List<ClientDto> getClients(){
        return clientService.getAllCLients();
    }

    @GetMapping("/{id}")
    public ClientDto getClient(@PathVariable(name = "id") int clientId) throws ClientNotFoundException {

        return clientService.getOneClient(clientId);
    }

    @PostMapping("/")
    public ClientDto saveClient(@RequestBody ClientDto clientDto){

        return clientService.saveClient(clientDto);
    }

    @PutMapping("/{clientId}")
    public ClientDto updateClient(@PathVariable int clientId, @RequestBody ClientDto clientDto) throws ClientNotFoundException {
        ClientDto existingClient = clientService.getOneClient(clientId);
        clientDto.setId(clientId);
        return clientService.updateClient(clientDto);

    }

    @DeleteMapping("/{clientId}")
    public  void deleteClient(@PathVariable int clientId) throws ClientNotFoundException {

        clientService.deleteClient(clientId);
}
}
