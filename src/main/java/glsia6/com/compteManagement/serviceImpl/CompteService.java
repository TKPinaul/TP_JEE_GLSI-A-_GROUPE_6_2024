package glsia6.com.compteManagement.serviceImpl;

import glsia6.com.compteManagement.entity.*;
import glsia6.com.compteManagement.enums.TypeTransaction;
import glsia6.com.compteManagement.exception.ClientNotFoundException;
import glsia6.com.compteManagement.exception.CompteNotFoundException;
import glsia6.com.compteManagement.exception.SoldeNotSufficientException;
import glsia6.com.compteManagement.repository.ClientRepository;
import glsia6.com.compteManagement.repository.CompteRepository;
import glsia6.com.compteManagement.repository.TransactionRepository;
import glsia6.com.compteManagement.service.ICompteService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@Slf4j
public class CompteService implements ICompteService {
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    //Logger log= LoggerFactory.getLogger(this.getClass().getName());
    /*@Override
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }*/


    @Override
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();

    }

    @Override
    public Compte getOneCompte(String compteId) throws CompteNotFoundException {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(()->new CompteNotFoundException("Compte not found"));
        return compte;
    }



    @Override
    public CompteCourant saveCompteCourant(double initialSolde, double decouvert, int clientId) throws ClientNotFoundException {
        log.info("Recherche du client avec l'identifiant : " + clientId);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + clientId));
        if (client==null)
            throw new ClientNotFoundException("Client not found");
        CompteCourant compteCourant = new CompteCourant();

        compteCourant.setId(UUID.randomUUID().toString());
        compteCourant.setDateCreation(new Date());
        compteCourant.setSolde(initialSolde);
        compteCourant.setDecouvert(decouvert);
        compteCourant.setClient(client);
        CompteCourant savedCompteCourant = compteRepository.save(compteCourant);
        return savedCompteCourant;

    }


    @Override
    public CompteEpargne saveCompteEpargne(double initialSolde, double tauxInteret, int clientId) throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client==null)
            throw new ClientNotFoundException("Client not found");
        CompteEpargne compteEpargne = new CompteEpargne();

        compteEpargne.setId(UUID.randomUUID().toString());
        compteEpargne.setDateCreation(new Date());
        compteEpargne.setSolde(initialSolde);
        compteEpargne.setTauxInteret(tauxInteret);
        compteEpargne.setClient(client);
        CompteEpargne savedcompteEpargne = compteRepository.save(compteEpargne);
        return savedcompteEpargne;
    }

    @Override
    public void debit(String compteId, double montant, String description) throws SoldeNotSufficientException, CompteNotFoundException {
        Compte compte = getOneCompte(compteId);
        if (compte.getSolde()<montant)
            throw new SoldeNotSufficientException("Solde insuffisant");
        Transaction transaction = new Transaction();
        transaction.setType(TypeTransaction.DEBIT);
        transaction.setMontant(montant);
        transaction.setDateTransaction(new Date());
        transaction.setDescription(description);
        transaction.setCompte(compte);
        transactionRepository.save(transaction);
        compte.setSolde(compte.getSolde()-montant);

    }



    @Override
    public void credit(String compteId, double montant, String description) throws CompteNotFoundException {
        Compte compte = getOneCompte(compteId);
        Transaction transaction = new Transaction();
        transaction.setType(TypeTransaction.CREDIT);
        transaction.setMontant(montant);
        transaction.setDateTransaction(new Date());
        transaction.setDescription(description);
        transaction.setCompte(compte);
        transactionRepository.save(transaction);
        compte.setSolde(compte.getSolde()+montant);
    }




    @Override
    public void transfer(String compteIdSource, String compteIdDestination, double montant) throws CompteNotFoundException, SoldeNotSufficientException {
        debit(compteIdSource,montant,"Virement vers"+compteIdDestination);
        credit(compteIdSource,montant,"Virement recu depuis"+compteIdSource);

    }

    @Override
    public void deleteCompte(String compteId) {
        compteRepository.deleteById(compteId);
    }
}
