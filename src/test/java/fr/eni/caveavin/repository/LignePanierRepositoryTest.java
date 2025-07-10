package fr.eni.caveavin.repository;

import fr.eni.caveavin.bo.client.LignePanier;
import fr.eni.caveavin.bo.vin.Bouteille;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LignePanierRepositoryTest {
    @Autowired
    private LignePanierRepository lignePanierRepository;

    @Autowired
    private BouteilleRepository bouteilleRepository;

    @Test
    public void save_ligne_onlySaveLigne() {
        List<Bouteille> bouteilles = bouteilleRepository.findAll();
        int initialBouteilleSize = bouteilles.size();
        Bouteille first =  bouteilles.getFirst();

        LignePanier lignePanier = LignePanier.builder()
                .qteCommande(6)
                .prix(6 * first.getPrix())
                .bouteille(first)
                .build();

        LignePanier result = lignePanierRepository.save(lignePanier);

        assertNotNull(result.getId());
        assertTrue(result.getId() > 0);
        assertEquals(lignePanier.getBouteille().getId(), result.getBouteille().getId());

        bouteilles = bouteilleRepository.findAll();
        int finalBouteilleSize = bouteilles.size();

        assertEquals(initialBouteilleSize, finalBouteilleSize);
    }

    @Test
    public void delete_ligne_onlyDeleteLigne() {
        List<Bouteille> bouteilles = bouteilleRepository.findAll();
        int initialBouteilleSize = bouteilles.size();
        Bouteille first =  bouteilles.getFirst();

        LignePanier lignePanier = lignePanierRepository.findAll().getFirst();
        lignePanier.setBouteille(first);

        lignePanierRepository.save(lignePanier);
        lignePanierRepository.deleteById(lignePanier.getId());

        Optional<LignePanier> shouldBeEmpty = lignePanierRepository.findById(lignePanier.getId());
        assertTrue(shouldBeEmpty.isEmpty());

        bouteilles = bouteilleRepository.findAll();
        int finalBouteilleSize = bouteilles.size();

        assertEquals(initialBouteilleSize, finalBouteilleSize);
    }
}