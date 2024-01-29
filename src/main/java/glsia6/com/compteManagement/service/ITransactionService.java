package glsia6.com.compteManagement.service;

import glsia6.com.compteManagement.dto.CompteHistoryDto;
import glsia6.com.compteManagement.dto.TransactionDto;
import glsia6.com.compteManagement.exception.CompteNotFoundException;

import java.util.List;

public interface ITransactionService {
    public  List<TransactionDto> TransactionHistory(String compteId);

    CompteHistoryDto getCompteHistory(String compteId, int page, int size) throws CompteNotFoundException;
}
