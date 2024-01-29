package glsia6.com.compteManagement.service;

import glsia6.com.compteManagement.dto.CompteCourantDto;
import glsia6.com.compteManagement.dto.CompteDto;
import glsia6.com.compteManagement.dto.CompteEpargneDto;
import glsia6.com.compteManagement.entity.Compte;
import glsia6.com.compteManagement.entity.CompteCourant;
import glsia6.com.compteManagement.entity.CompteEpargne;
import glsia6.com.compteManagement.exception.ClientNotFoundException;
import glsia6.com.compteManagement.exception.CompteNotFoundException;
import glsia6.com.compteManagement.exception.SoldeNotSufficientException;

import java.util.List;

public interface ICompteService {
    public List<CompteDto> getAllComptes();
    public CompteDto getOneCompte(String compteId) throws CompteNotFoundException, ClientNotFoundException;


    CompteCourantDto saveCompteCourant(double initialSolde, double decouvert, String numeroCompte, int clientId) throws ClientNotFoundException;



    CompteEpargneDto saveCompteEpargne(double solde, double tauxInteret, String numeroCompte, int clientId) throws ClientNotFoundException;

    void debit(String compteId, double montant, String description) throws SoldeNotSufficientException, CompteNotFoundException;
    void credit(String compteId, double montant, String description) throws SoldeNotSufficientException, CompteNotFoundException;

    void transfer(String compteIdSource, String compteIdDestination, double montant) throws CompteNotFoundException, SoldeNotSufficientException;
    CompteCourantDto updateCompte(double initialSolde, double decouvert,String numeroCompte, int clientId) throws ClientNotFoundException;

   // CompteCourantDto updateCompte(double Solde, double decouvert,double tautxInteret,String compteId) throws CompteNotFoundException;


    //public void  deleteCompte(String compteId);
}
