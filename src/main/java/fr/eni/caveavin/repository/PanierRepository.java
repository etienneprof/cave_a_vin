package fr.eni.caveavin.repository;

import fr.eni.caveavin.bo.client.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Integer> {
}
