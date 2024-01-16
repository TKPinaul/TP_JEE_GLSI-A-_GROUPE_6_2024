package glsia6.com.compteManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String typeCompte;
    private String numeroCompte;
    private LocalDate dateCreation;
    private float solde;
    private String proprietaire;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
