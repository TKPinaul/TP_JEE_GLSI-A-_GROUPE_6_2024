package glsia6.com.compteManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@DiscriminatorValue("CC")
@NoArgsConstructor
@AllArgsConstructor
public class CompteCourant extends Compte{
    private double decouvert;
}
