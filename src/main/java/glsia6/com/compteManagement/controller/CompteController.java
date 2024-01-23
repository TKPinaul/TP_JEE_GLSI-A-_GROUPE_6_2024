package glsia6.com.compteManagement.controller;

import glsia6.com.compteManagement.entity.Client;
import glsia6.com.compteManagement.entity.Compte;
import glsia6.com.compteManagement.entity.CompteCourant;
import glsia6.com.compteManagement.serviceImpl.ClientService;
import glsia6.com.compteManagement.serviceImpl.CompteService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;

    @GetMapping("/")
    public List<Compte> getComptes(){
        return compteService.getAllComptes();
    }

    /*@SneakyThrows
    @GetMapping("/{id}")
    public Compte getOneCompte(@PathVariable String compteId){
        return compteService.getOneCompte(compteId);
    }

    @PostMapping("/")
    public CompteCourant saveCompteCourant(@RequestBody Compte compte){
        return compteService.saveCompteCourant(saveCompteCourant(saveCompteCourant()));
    }*/

    /*@PutMapping("/{id}")
    public ResponseEntity<String> updateCompte(@PathVariable int id, @RequestBody Compte compteUpdated){
        Compte existingCompte = compteService.getOneCompte(id);
        if (existingCompte!=null){

            existingCompte.setNumeroCompte(compteUpdated.getNumeroCompte());
            existingCompte.setTypeCompte(compteUpdated.getTypeCompte());
            existingCompte.setSolde(compteUpdated.getSolde());
            existingCompte.setDateCreation(compteUpdated.getDateCreation());

            compteService.saveCompte(existingCompte);
            return ResponseEntity.ok("This compte has been updated successfully");
        }else {
            return ResponseEntity.notFound().build();
        }

    }*/

    /*@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id){
        Compte existingCompte = compteService.getOneCompte();
         if ( existingCompte!=null){
             compteService.deleteCompte(compteId);

             return ResponseEntity.ok("This compte is deleted successfully");
        }else {
             return ResponseEntity.notFound().build();
         }

    }*/

}
