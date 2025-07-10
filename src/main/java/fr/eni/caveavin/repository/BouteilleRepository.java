package fr.eni.caveavin.repository;

import fr.eni.caveavin.bo.vin.Bouteille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BouteilleRepository extends JpaRepository<Bouteille,Integer> {
    List<Bouteille> findByRegionNom(String nom);
    List<Bouteille> findByCouleurNom(String nom);

}
