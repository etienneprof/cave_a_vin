package fr.eni.caveavin.repository;

import fr.eni.caveavin.bo.client.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Integer> {
}
