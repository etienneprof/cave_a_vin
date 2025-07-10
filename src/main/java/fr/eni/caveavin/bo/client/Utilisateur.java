package fr.eni.caveavin.bo.client;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="cav_user")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"password"})
@EqualsAndHashCode(of = {"pseudo"})
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role")
@DiscriminatorValue("user")
public class Utilisateur {
    @Id
    @Column(name = "login")
    private String pseudo;

    @Column(name = "password")
    private String password;

    @Column(name = "last_name")
    private String nom;

    @Column(name = "first_name")
    private String prenom;
}
