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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String typeCompte;
    private String numeroCompte;
    private Date dateCreation;
    private float solde;
    private CompteStatus status;


    @ManyToOne
    private  Client client;
    @OneToMany(mappedBy = "compte")
    private List<Transaction> transactions;

}
