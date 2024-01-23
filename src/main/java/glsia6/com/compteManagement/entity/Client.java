package glsia6.com.compteManagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import glsia6.com.compteManagement.enums.CompteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private Date dateNaissance;
    private String sexe;
    private String adresse;
    private String telephone;
    private String courriel;
    private String nationalite;

    @OneToMany(mappedBy = "client")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Compte> Comptes;
}


