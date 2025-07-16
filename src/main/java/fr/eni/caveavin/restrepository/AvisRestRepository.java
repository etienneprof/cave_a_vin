package fr.eni.caveavin.restrepository;

import fr.eni.caveavin.bo.client.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "avis", path = "avis")
public interface AvisRestRepository extends JpaRepository<Avis,Integer> {
    List<Avis> findByNoteGreaterThan(int note);
    List<Avis> findByNoteLessThan(int note);

    List<Avis> findByClientPseudo(String pseudo);
    List<Avis> findByQuantiteGreaterThan(int quantite);

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    List<Avis> findByDateBetween(LocalDate deb, LocalDate fin);

}
