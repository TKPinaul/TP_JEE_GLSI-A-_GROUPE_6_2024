package glsia6.com.compteManagement.controller;

import glsia6.com.compteManagement.dto.*;
import glsia6.com.compteManagement.entity.Client;
import glsia6.com.compteManagement.entity.Compte;
import glsia6.com.compteManagement.entity.CompteCourant;
import glsia6.com.compteManagement.exception.ClientNotFoundException;
import glsia6.com.compteManagement.exception.CompteNotFoundException;
import glsia6.com.compteManagement.exception.SoldeNotSufficientException;
import glsia6.com.compteManagement.serviceImpl.ClientService;
import glsia6.com.compteManagement.serviceImpl.CompteService;
import glsia6.com.compteManagement.serviceImpl.TransactionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comptes")
public class   CompteController {

    @Autowired
    private CompteService compteService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{compteId}")
    public CompteDto getCompte(@PathVariable String compteId) throws CompteNotFoundException, ClientNotFoundException {

        return compteService.getOneCompte(compteId);
    }

    @GetMapping("/")
    public List<CompteDto> listComptes(){

        return compteService.getAllComptes();
    }

    @GetMapping("/{compteId}/pageTransactions")
    public CompteHistoryDto getHistory(
            @PathVariable String compteId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) throws CompteNotFoundException {
        return transactionService.getCompteHistory(compteId,page,size);
    }

    @PostMapping("/debit")
    public DebitDto debit(@RequestBody DebitDto debitDto) throws CompteNotFoundException, SoldeNotSufficientException {
        this.compteService.debit(debitDto.getCompteId(),debitDto.getMontant(),debitDto.getDescription());
        return debitDto;
    }

    @PostMapping("/credit")
    public CreditDto credit(@RequestBody CreditDto creditDto) throws CompteNotFoundException, SoldeNotSufficientException {
        this.compteService.credit(creditDto.getCompteId(),creditDto.getMontant(),creditDto.getDescription());
        return creditDto;
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferDto transferDto) throws CompteNotFoundException, SoldeNotSufficientException {
        this.compteService.transfer(transferDto.getCompteSource(),transferDto.getCompteDestination(),transferDto.getMontant());

    }

    @PostMapping("/courant")

    public CompteCourantDto saveCompteCourant(@RequestBody SaveCompteCourantDto saveCompteCourantDto) throws ClientNotFoundException {
        CompteCourantDto savedCompteCourant = compteService.saveCompteCourant(
                saveCompteCourantDto.getInitialSolde(),
                saveCompteCourantDto.getDecouvert(),
                saveCompteCourantDto.getNumeroCompte(),
                saveCompteCourantDto.getClientId());
        return savedCompteCourant;
    }

    @PostMapping("/epargne")

    public CompteEpargneDto saveCompteEpargne(@RequestBody SaveCompteEpargneDto saveCompteEpargneDto) throws ClientNotFoundException {
        CompteEpargneDto savedCompteEpargneDto = compteService.saveCompteEpargne(
                saveCompteEpargneDto.getSolde(),
                saveCompteEpargneDto.getTauxInteret(),
                saveCompteEpargneDto.getNumeroCompte(),
                saveCompteEpargneDto.getClientId());
        return savedCompteEpargneDto;
    }
    /*@PutMapping("/{compteId}")
    public CompteCourantDto updateCompteCourant(@RequestBody UpdateCompteCourantDto updateCompteCourantDto) throws CompteNotFoundException {
        CompteCourantDto updatedCompteCourant = compteService.updateCompteCourant(
                updateCompteCourantDto.getNewSolde(),
                updateCompteCourantDto.getNewDecouvert(),
                updateCompteCourantDto.getCompteId()
        );

    }*/

    /*@DeleteMapping("/{compteId}")
    public void deleteCompte(@PathVariable String compteId){
        compteService.deleteCompte(compteId);
    }*/


}

