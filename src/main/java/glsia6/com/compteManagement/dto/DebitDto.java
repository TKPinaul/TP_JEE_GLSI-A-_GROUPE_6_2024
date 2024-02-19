package glsia6.com.compteManagement.dto;

import lombok.Data;

@Data
public class DebitDto {

    private String compteId;
    private double montant;
    private String description;

}
