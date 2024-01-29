package glsia6.com.compteManagement.dto;

import lombok.Data;

@Data
public class SaveCompteCourantDto {
    private double initialSolde;
    private String numeroCompte;
    private double decouvert;
    private int clientId;
}
