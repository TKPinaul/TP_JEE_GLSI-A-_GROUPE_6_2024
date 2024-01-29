package glsia6.com.compteManagement.dto;

import lombok.Data;

@Data
public class CreditDto {
    private String compteId;
    private double montant;
    private String description;
}
