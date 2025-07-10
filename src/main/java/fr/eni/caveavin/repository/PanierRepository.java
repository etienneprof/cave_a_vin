package fr.eni.caveavin.repository;

import fr.eni.caveavin.bo.client.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Integer> {
    // ***********  Les paniers d'un client.
    List<Panier> findByClientPseudoAndNumCommandeIsNull(String pseudo);

    @Query("SELECT p FROM Panier p WHERE p.numCommande IS NULL AND p.client.pseudo = :pseudo")
    List<Panier> findSameButWithJPQL(String pseudo);


    // ***********  Les commandes d'un client.
    List<Panier> findByClientPseudoAndNumCommandeIsNotNull(String pseudo);

    @Query(value = "SELECT * FROM cav_shopping_cart p WHERE p.order_number IS NOT NULL AND p.client_id = :pseudo", nativeQuery = true)
    List<Panier> findSameAgainButWithJPQL(String pseudo);
}
