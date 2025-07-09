package fr.eni.caveavin.bo.client;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="cav_client")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString(exclude = {"password"})
@EqualsAndHashCode(of = {"pseudo"})
public class Client {
    @Id
    @Column(name = "login")
    private String pseudo;

    @Column(name = "password")
    private String password;

    @Column(name = "last_name")
    private String nom;

    @Column(name = "first_name")
    private String prenom;

    @OneToOne(cascade = CascadeType.ALL,  fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Adresse adresse;
}
