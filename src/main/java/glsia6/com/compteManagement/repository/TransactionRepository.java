package glsia6.com.compteManagement.repository;

import glsia6.com.compteManagement.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
