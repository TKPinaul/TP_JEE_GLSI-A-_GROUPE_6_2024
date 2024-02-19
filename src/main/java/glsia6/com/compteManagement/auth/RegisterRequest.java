package glsia6.com.compteManagement.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nom;
    private String sexe;
    private Date dateNaissance;
    private String courriel;
    private String telephone;
    private String password;
    private String adresse;
    private String nationalite;

}
