package glsia6.com.compteManagement.repository;

import glsia6.com.compteManagement.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,Integer> {
}
