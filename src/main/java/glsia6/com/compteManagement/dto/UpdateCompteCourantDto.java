package glsia6.com.compteManagement.dto;

import lombok.Data;

@Data
public class UpdateCompteCourantDto {

    private String compteId;
    private double newSolde;
    private double newDecouvert;

}
