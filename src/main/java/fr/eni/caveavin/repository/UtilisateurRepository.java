package fr.eni.caveavin.repository;

import fr.eni.caveavin.bo.client.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,String> {
    Utilisateur findByPseudo(String pseudo);
    Utilisateur findByPseudoAndPassword(String pseudo, String password);
}
