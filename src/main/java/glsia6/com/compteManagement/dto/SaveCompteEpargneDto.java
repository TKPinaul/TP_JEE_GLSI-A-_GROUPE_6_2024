package glsia6.com.compteManagement.dto;

import lombok.Data;

@Data
public class SaveCompteEpargneDto {

    private double solde;
    private String numeroCompte;
    private double tauxInteret;
    private int clientId;

}
