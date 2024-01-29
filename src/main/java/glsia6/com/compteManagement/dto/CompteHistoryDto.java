package glsia6.com.compteManagement.dto;

import lombok.Data;

import java.util.List;
@Data
public class CompteHistoryDto {

    private String compteId;
    private double solde;
    private int currentPage;
    private int totalPage;
    private int pageSize;


    private List<TransactionDto> transactionDtos;
}
