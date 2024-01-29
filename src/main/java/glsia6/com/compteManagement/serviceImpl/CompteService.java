package glsia6.com.compteManagement.serviceImpl;

import glsia6.com.compteManagement.dto.CompteCourantDto;
import glsia6.com.compteManagement.dto.CompteDto;
import glsia6.com.compteManagement.dto.CompteEpargneDto;
import glsia6.com.compteManagement.entity.*;
import glsia6.com.compteManagement.enums.TypeTransaction;
import glsia6.com.compteManagement.exception.ClientNotFoundException;
import glsia6.com.compteManagement.exception.CompteNotFoundException;
import glsia6.com.compteManagement.exception.SoldeNotSufficientException;
import glsia6.com.compteManagement.mappers.CompteMapperImpl;
import glsia6.com.compteManagement.repository.ClientRepository;
import glsia6.com.compteManagement.repository.CompteRepository;
import glsia6.com.compteManagement.repository.TransactionRepository;
import glsia6.com.compteManagement.service.ICompteService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


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
    @Autowired
    private CompteMapperImpl compteMapper;

    //Logger log= LoggerFactory.getLogger(this.getClass().getName());
    /*@Override
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }*/


    @Override
    public List<CompteDto> getAllComptes() {
        List<Compte> comptes = compteRepository.findAll();
        List<CompteDto> compteDTOS = comptes.stream().map(compte -> {
            if (compte instanceof CompteEpargne) {
                CompteEpargne compteEpargne = (CompteEpargne) compte;
                return compteMapper.formCompteEpargne(compteEpargne);
            } else {
                CompteCourant compteCourant = (CompteCourant) compte;
                return compteMapper.fromCompteCourant(compteCourant);
            }

        }).collect(Collectors.toList());

        return compteDTOS;
    }

    @Override
    public CompteDto getOneCompte(String compteId) throws CompteNotFoundException, ClientNotFoundException {
        Optional<Compte> optionalCompte = compteRepository.findById(compteId);
        if (optionalCompte.isPresent()) {
            Compte compte = optionalCompte.get();

            if (compte instanceof CompteEpargne) {
                CompteEpargne compteEpargne = (CompteEpargne) compte;
                return compteMapper.formCompteEpargne(compteEpargne);
            } else if (compte instanceof CompteCourant) {
                CompteCourant compteCourant = (CompteCourant) compte;
                return compteMapper.fromCompteCourant(compteCourant);
            } else {
                throw new CompteNotFoundException("Pas de compte pour cet Id:" + compteId);
            }
        } else {
            throw new CompteNotFoundException("Pas de compte pour cet Id:" + compteId);

        }


    }

    @Override
    public CompteCourantDto saveCompteCourant(double initialSolde, double decouvert, String numeroCompte, int clientId) throws ClientNotFoundException {
        log.info("Recherche du client avec l'identifiant : " + clientId);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + clientId));
        if (client == null)
            throw new ClientNotFoundException("Client not found");
        CompteCourant compteCourant = new CompteCourant();

        compteCourant.setId(UUID.randomUUID().toString());
        compteCourant.setDateCreation(new Date());
        compteCourant.setSolde(initialSolde);
        compteCourant.setNumeroCompte(numeroCompte);
        compteCourant.setDecouvert(decouvert);
        compteCourant.setClient(client);
        CompteCourant savedCompteCourant = compteRepository.save(compteCourant);
        return compteMapper.fromCompteCourant(savedCompteCourant);
    }

    @Override
    public CompteEpargneDto saveCompteEpargne(double solde, double tauxInteret, String numeroCompte, int clientId) throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null)
            throw new ClientNotFoundException("Client not found");
        CompteEpargne compteEpargne = new CompteEpargne();

        compteEpargne.setId(UUID.randomUUID().toString());
        compteEpargne.setDateCreation(new Date());
        compteEpargne.setSolde(solde);
        compteEpargne.setNumeroCompte(numeroCompte);
        compteEpargne.setTauxInteret(tauxInteret);
        compteEpargne.setClient(client);
        CompteEpargne savedcompteEpargne = compteRepository.save(compteEpargne);
        return compteMapper.formCompteEpargne(savedcompteEpargne);

    }

    @Override
    public void debit(String compteId, double montant, String description) throws SoldeNotSufficientException, CompteNotFoundException {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new CompteNotFoundException("Compte not found"));
        if (compte.getSolde() < montant)
            throw new SoldeNotSufficientException("Solde insuffisant");
        Transaction transaction = new Transaction();
        transaction.setType(TypeTransaction.DEBIT);
        transaction.setMontant(montant);
        transaction.setDateTransaction(new Date());
        transaction.setDescription(description);
        transaction.setCompte(compte);
        transactionRepository.save(transaction);
        compte.setSolde(compte.getSolde() - montant);

    }

    @Override
    public void credit(String compteId, double montant, String description) throws SoldeNotSufficientException, CompteNotFoundException {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new CompteNotFoundException("Compte not found"));
        Transaction transaction = new Transaction();
        transaction.setType(TypeTransaction.CREDIT);
        transaction.setMontant(montant);
        transaction.setDateTransaction(new Date());
        transaction.setDescription(description);
        transaction.setCompte(compte);
        transactionRepository.save(transaction);
        compte.setSolde(compte.getSolde() + montant);

    }

    @Override
    public void transfer(String compteIdSource, String compteIdDestination, double montant) throws CompteNotFoundException, SoldeNotSufficientException {
        debit(compteIdSource, montant, "Virement vers" + compteIdDestination);
        credit(compteIdSource, montant, "Virement recu depuis" + compteIdSource);


    }
    @PutMapping("/{compteId}")

    @Override
    public CompteCourantDto updateCompte(double initialSolde, double decouvert,String numeroCompte,int clientId) throws ClientNotFoundException {

        return saveCompteCourant(initialSolde,decouvert,numeroCompte,clientId);
    }
}

    //@Override
    //public CompteCourantDto updateCompte(double solde, double decouvert, double tauxInteret, String compteId) throws CompteNotFoundException {
        /*Compte updatedCompte = compteRepository.findById(compteId)
                .orElseThrow(() -> new CompteNotFoundException("Compte non trouvé avec l'ID : " + compteId));

        if (updatedCompte instanceof CompteCourant) {
            CompteCourant compteCourant = (CompteCourant) updatedCompte;
            compteCourant.setDecouvert(decouvert);
            compteCourant.setSolde(solde);
            compteRepository.save(compteCourant);
            return compteMapper.fromCompteCourant(compteCourant);
        } else if (updatedCompte instanceof CompteEpargne) {
            CompteEpargne compteEpargne = (CompteEpargne) updatedCompte;
            compteEpargne.setSolde(solde);
            compteEpargne.setTauxInteret(tauxInteret);
            compteEpargne.setClient(Client.builder().build());

            compteRepository.save(compteEpargne);
            return compteMapper.formCompteEpargne(compteEpargne); // Assurez-vous d'avoir une méthode de mappage appropriée
        } else {
            throw new CompteNotFoundException("Type de compte non pris en charge pour l'ID : " + compteId);
        }
    }*/

        /*public void deleteCompte(String compteId) {
        compteRepository.deleteById(compteId);


    }*/

    /*public CompteDto getOneCompte(String compteId, String description) throws CompteNotFoundException, ClientNotFoundException, SoldeNotSufficientException {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new CompteNotFoundException("Compte not found"));
        if (compte instanceof CompteEpargne) {
            CompteEpargne compteEpargne = (CompteEpargne) compte;
            return compteMapper.formCompteEpargne(compteEpargne);
        } else {
            CompteCourant compteCourant = (CompteCourant) compte;
            return compteMapper.fromCompteCourant(compteCourant);
        }

    }


}*/
/* public CompteCourantDto updateCompteCourant(String id, double solde, double decouvert) throws CompteNotFoundException {
    CompteCourant compteCourant = compteRepository.findById(id)
            .orElseThrow(() -> new CompteNotFoundException("Compte courant not found with id: " + id));

    compteCourant.setSolde(solde);
    compteCourant.setDecouvert(decouvert);

    CompteCourant updatedCompteCourant = compteRepository.save(compteCourant);
    return compteMapper.fromCompteCourant(updatedCompteCourant);
}

public CompteEpargneDto updateCompteEpargne(String id, double solde, double tauxInteret) throws CompteNotFoundException {
    CompteEpargne compteEpargne = compteEpargneRepository.findById(id)
            .orElseThrow(() -> new CompteNotFoundException("Compte épargne not found with id: " + id));

    compteEpargne.setSolde(solde);
    compteEpargne.setTauxInteret(tauxInteret);

    CompteEpargne updatedCompteEpargne = compteEpargneRepository.save(compteEpargne);
    return compteMapper.fromCompteEpargne(updatedCompteEpargne);
}
 */





    /*@Override
    public void credit(String compteId, double montant, String description) throws CompteNotFoundException {
        Compte compte = getOneCompte(compteId, description);
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
}*/
