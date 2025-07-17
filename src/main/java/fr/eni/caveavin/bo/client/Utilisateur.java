package fr.eni.caveavin.bo.client;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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
public class Utilisateur implements UserDetails {
    @Id
    @Column(name = "login")
    private String pseudo;

    @Column(name = "password")
    private String password;

    @Column(name = "last_name")
    private String nom;

    @Column(name = "first_name")
    private String prenom;

    @Column(length = 15)
    private String authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getUsername() {
        return pseudo;
    }
}
