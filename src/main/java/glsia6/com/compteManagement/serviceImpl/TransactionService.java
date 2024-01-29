package glsia6.com.compteManagement.serviceImpl;

import glsia6.com.compteManagement.dto.CompteHistoryDto;
import glsia6.com.compteManagement.dto.TransactionDto;
import glsia6.com.compteManagement.entity.Compte;
import glsia6.com.compteManagement.entity.Transaction;
import glsia6.com.compteManagement.exception.CompteNotFoundException;
import glsia6.com.compteManagement.mappers.TransactionMapperImpl;
import glsia6.com.compteManagement.repository.CompteRepository;
import glsia6.com.compteManagement.repository.TransactionRepository;
import glsia6.com.compteManagement.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionMapperImpl transactionMapper;

    @Autowired
    private CompteRepository compteRepository;


    @Override
    public List<TransactionDto> TransactionHistory(String compteId) {
        List<Transaction> istTransactions = transactionRepository.findByCompteId(compteId);
        return istTransactions.stream().map(trans -> transactionMapper.fromTransaction(trans)).collect(Collectors.toList());

    }

    @Override
    public CompteHistoryDto getCompteHistory(String compteId, int page, int size) throws CompteNotFoundException {
        Compte compte = compteRepository.findById(compteId).orElse(null);
        if (compte==null) throw new CompteNotFoundException("Ce compte n'existe pas.");
        Page<Transaction> compteTransactions = transactionRepository.findByCompteId(compteId, PageRequest.of(page,size));
        CompteHistoryDto compteHistoryDto = new CompteHistoryDto();
        List<TransactionDto> transactionDtos =  compteTransactions.getContent().stream().map(tran -> transactionMapper.fromTransaction(tran)).collect(Collectors.toList());
        compteHistoryDto.setTransactionDtos(transactionDtos);
        compteHistoryDto.setSolde(compte.getSolde());
        compteHistoryDto.setCurrentPage(page);
        compteHistoryDto.setPageSize(size);
        compteHistoryDto.setTotalPage(compteTransactions.getTotalPages());


        return compteHistoryDto;
    }

}
