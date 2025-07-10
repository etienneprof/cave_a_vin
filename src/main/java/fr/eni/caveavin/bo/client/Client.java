package fr.eni.caveavin.bo.client;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity @Table(name="cav_client")
@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@DiscriminatorValue("client")
@ToString(callSuper = true)
public class Client extends Utilisateur {
    @OneToOne(cascade = CascadeType.ALL,  fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Adresse adresse;
}
