package fr.eni.caveavin.bo.client;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="cav_owner")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper=true)
@DiscriminatorValue("owner")
public class Proprio extends Utilisateur {
    @Column(name = "client_number", length = 14)
    private String siret;
}
