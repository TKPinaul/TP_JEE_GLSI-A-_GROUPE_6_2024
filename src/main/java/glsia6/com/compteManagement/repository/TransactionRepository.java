package glsia6.com.compteManagement.repository;

import glsia6.com.compteManagement.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
    public List<Transaction> findByCompteId(String compteId);
    Page<Transaction> findByCompteId(String compteId, Pageable pageable);
}
