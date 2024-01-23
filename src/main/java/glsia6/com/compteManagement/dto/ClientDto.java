package glsia6.com.compteManagement.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClientDto {

    private int id;
    private String nom;
    private String sexe;
    private String adresse;
   // private String telephone;
    //private Date dateNaissance;
    //private String courriel;
    private String nationalite;
}
