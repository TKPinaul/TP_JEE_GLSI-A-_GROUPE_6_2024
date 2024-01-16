package glsia6.com.compteManagement.repository;

import glsia6.com.compteManagement.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
