package glsia6.com.compteManagement.entity;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 10)
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Compte {
    @Id
    private String id;
    private String numeroCompte;
    private Date dateCreation;
    private double solde;
    private CompteStatus status; // etat


    @ManyToOne
    private  Client client;
    @OneToMany(mappedBy = "compte",fetch = FetchType.LAZY)
    private List<Transaction> transactions;

}
