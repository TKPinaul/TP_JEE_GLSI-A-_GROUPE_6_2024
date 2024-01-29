package glsia6.com.compteManagement.dto;

import glsia6.com.compteManagement.enums.TypeTransaction;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {
    private int id;
    private Date dateTransaction;
    private double montant;
    private TypeTransaction type; // Versement, Retrait, Virement
    private String description;


}
