package glsia6.com.compteManagement.dto;

import lombok.Data;

@Data
public class TransferDto {

    private String compteSource;
    private String compteDestination;
    private double montant;
    private String description;

}
