package glsia6.com.compteManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private LocalDate dateNaissance;
    private String sexe;
    private String adresse;
    private String telephone;
    private String courriel;
    private String nationalite;

    @OneToMany(mappedBy = "client")
    private List<Compte> Comptes;
}


