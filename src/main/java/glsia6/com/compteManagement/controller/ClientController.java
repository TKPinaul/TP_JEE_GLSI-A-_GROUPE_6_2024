package glsia6.com.compteManagement.controller;

import glsia6.com.compteManagement.entity.Client;
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
    public List<Client> getClients(){
        return clientService.getAllCLients();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable int id){
        return clientService.getOneClient(id);
    }

    @PostMapping("/")
    public Client saveClient(@RequestBody Client client){
        return clientService.saveClient(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable int id, @RequestBody Client clientUpdated){
        Client existingClient = clientService.getOneClient(id);

        if (existingClient != null){

            existingClient.setNom(clientUpdated.getNom());
            existingClient.setAdresse(clientUpdated.getAdresse());
            existingClient.setSexe(clientUpdated.getSexe());
            existingClient.setCourriel(clientUpdated.getCourriel());
            existingClient.setNationalite(clientUpdated.getNationalite());
            existingClient.setTelephone(clientUpdated.getTelephone());
            existingClient.setDateNaissance(clientUpdated.getDateNaissance());

            clientService.saveClient(existingClient);

            return ResponseEntity.ok(existingClient);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id){
        //Optional<Client> existingClientOptional = Optional.ofNullable(clientService.getOneClient(id));
        Client existingClient = clientService.getOneClient(id);
        if (existingClient!=null){

            clientService.deleteClient(id);

            return ResponseEntity.ok("This client has been deleted successfully");

        }else {
            return ResponseEntity.notFound().build();
        }

    }
}
