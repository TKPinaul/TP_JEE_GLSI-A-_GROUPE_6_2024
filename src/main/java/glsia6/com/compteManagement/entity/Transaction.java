package glsia6.com.compteManagement.entity;

import glsia6.com.compteManagement.enums.TypeTransaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@NoArgsConstructor @AllArgsConstructor @Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dateTransaction;
    private double montant;
    @Enumerated(EnumType.STRING)
    private TypeTransaction type; // Versement, Retrait, Virement

    @ManyToOne
    private Compte compte;
    private String description;

}
