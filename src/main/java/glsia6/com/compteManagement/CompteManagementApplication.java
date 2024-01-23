package glsia6.com.compteManagement;

import glsia6.com.compteManagement.dto.ClientDto;
import glsia6.com.compteManagement.entity.Client;
import glsia6.com.compteManagement.entity.Compte;
import glsia6.com.compteManagement.entity.CompteCourant;
import glsia6.com.compteManagement.entity.CompteEpargne;
import glsia6.com.compteManagement.enums.CompteStatus;
import glsia6.com.compteManagement.exception.ClientNotFoundException;
import glsia6.com.compteManagement.exception.CompteNotFoundException;
import glsia6.com.compteManagement.exception.SoldeNotSufficientException;
import glsia6.com.compteManagement.repository.ClientRepository;
import glsia6.com.compteManagement.repository.CompteRepository;
import glsia6.com.compteManagement.repository.TransactionRepository;
import glsia6.com.compteManagement.serviceImpl.ClientService;
import glsia6.com.compteManagement.serviceImpl.CompteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class CompteManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompteManagementApplication.class, args);
	}
	//@Bean
	CommandLineRunner start(ClientService clientService, CompteService compteService, TransactionRepository transactionRepository){
		return args->{
			Stream.of("Jules","Celine","Ali").forEach(nom->{
				ClientDto client = new ClientDto();
				client.setNom(nom);
				client.setSexe("Male");
				client.setAdresse("Lome");
				//client.setDateNaissance(new Date());
				client.setNationalite("Togolaise");
				//client.setCourriel(nom+"@gmail.com");
				//client.setTelephone("96454545");
				clientService.saveClient(client);
			});
			clientService.getAllCLients().forEach(client ->{
				try {
					compteService.saveCompteCourant(Math.random()*9000,900,client.getId());
					compteService.saveCompteEpargne(Math.random()*120000,5.5, client.getId());
					List<Compte> comptes = compteService.getAllComptes();
					for (Compte compte:comptes){
						for (int i = 0; i<10; i++){
							compteService.credit(compte.getId(),10000+Math.random()*120000,"Credit");
							compteService.debit(compte.getId(),1000+Math.random()*9000,"Debit");
						}
					}
				} catch (ClientNotFoundException | CompteNotFoundException | SoldeNotSufficientException e) {
					throw new RuntimeException(e);
				}
			} );

		};
	}

}
