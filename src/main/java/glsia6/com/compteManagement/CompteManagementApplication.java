package glsia6.com.compteManagement;

import glsia6.com.compteManagement.entity.Client;
import glsia6.com.compteManagement.entity.CompteCourant;
import glsia6.com.compteManagement.entity.CompteEpargne;
import glsia6.com.compteManagement.enums.CompteStatus;
import glsia6.com.compteManagement.repository.ClientRepository;
import glsia6.com.compteManagement.repository.CompteRepository;
import glsia6.com.compteManagement.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class CompteManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompteManagementApplication.class, args);
	}
	CommandLineRunner start(ClientRepository clientRepository, CompteRepository compteRepository, TransactionRepository transactionRepository){
		return args->{
			Stream.of("Jules","Celine","Ali").forEach(nom->{
				Client client = new Client();
				client.setNom(nom);
				client.setSexe("Male");
				client.setAdresse("Lome");
				client.setDateNaissance(new Date());
				client.setNationalite("Togolaise");
				client.setCourriel(nom+"@gmail.com");
				client.setTelephone("96454545");
				clientRepository.save(client);
			});
			clientRepository.findAll().forEach(client -> {
				CompteCourant compteCourant = new CompteCourant();
				//compteCourant.set
				compteCourant.setStatus(CompteStatus.CREE);
				compteCourant.setDecouvert(9000);
				compteCourant.setDateCreation(new Date());
				compteCourant.setSolde(0);
				compteCourant.setNumeroCompte(UUID.randomUUID().toString());
				compteCourant.setClient(client);
				compteRepository.save(compteCourant);

				CompteEpargne compteEpargne = new CompteEpargne();
				//compteCourant.set
				compteEpargne.setStatus(CompteStatus.CREE);
				compteEpargne.setTauxInteret(5.5);
				compteEpargne.setStatus(CompteStatus.ACTIVE);
				compteEpargne.setNumeroCompte(UUID.randomUUID().toString());
				compteEpargne.setDateCreation(new Date());

				compteEpargne.setClient(client);
				compteRepository.save(compteEpargne);

			});
		};
	}

}
