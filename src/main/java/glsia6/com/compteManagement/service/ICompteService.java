package glsia6.com.compteManagement.service;

import glsia6.com.compteManagement.entity.Compte;
import glsia6.com.compteManagement.entity.CompteCourant;
import glsia6.com.compteManagement.entity.CompteEpargne;
import glsia6.com.compteManagement.exception.ClientNotFoundException;
import glsia6.com.compteManagement.exception.CompteNotFoundException;
import glsia6.com.compteManagement.exception.SoldeNotSufficientException;

import java.util.List;

public interface ICompteService {
    public List<Compte> getAllComptes();
    public Compte getOneCompte(String compteId) throws CompteNotFoundException;
    public CompteCourant saveCompteCourant(double initialSolde, double decouvert, int clientId) throws ClientNotFoundException;

    public CompteEpargne saveCompteEpargne(double initialSolde, double tauxInteret, int clientId) throws ClientNotFoundException;

    void debit(String compteId, double montant,String description) throws SoldeNotSufficientException, CompteNotFoundException;
    void credit(String compteId, double montant, String description) throws SoldeNotSufficientException, CompteNotFoundException;

    void transfer(String compteIdSource, String compteIdDestination, double montant) throws CompteNotFoundException, SoldeNotSufficientException;

    public void  deleteCompte(String compteId);
}
