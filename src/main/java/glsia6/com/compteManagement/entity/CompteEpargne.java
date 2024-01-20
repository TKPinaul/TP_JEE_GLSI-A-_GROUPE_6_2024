package glsia6.com.compteManagement.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@DiscriminatorValue("CE")
@NoArgsConstructor
@AllArgsConstructor
public class CompteEpargne extends Compte {
    private double tauxInteret;
}
