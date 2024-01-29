package glsia6.com.compteManagement.dto;

import glsia6.com.compteManagement.enums.CompteStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CompteEpargneDto extends CompteDto{
    private String id;
    private String numeroCompte;
    private double solde;
    private CompteStatus status;
    private Date dateCreation;
    private ClientDto clientDto;
    private double tauxInteret;


}
