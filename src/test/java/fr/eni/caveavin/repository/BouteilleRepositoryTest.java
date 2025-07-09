package fr.eni.caveavin.repository;

import fr.eni.caveavin.bo.vin.Bouteille;
import fr.eni.caveavin.bo.vin.Couleur;
import fr.eni.caveavin.bo.vin.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest @AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class BouteilleRepositoryTest {
    @Autowired
    private BouteilleRepository bouteilleRepository;

    @Autowired
    private CouleurRepository couleurRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void save_bottleWithRegionAndColor_onlyInsertBottle() {
        Couleur couleur = couleurRepository.findAll().stream()
                .filter(c -> c.getNom().equals("Blanc"))
                .findFirst()
                .orElseThrow();

        Region region = regionRepository.findAll().stream()
                .filter(r -> r.getName().equals("Pays de la Loire"))
                .findFirst()
                .orElseThrow();

        Bouteille bouteille = Bouteille.builder()
                .nom("Muscadet")
                .petillant(false)
                .millesime("2012")
                .quantite(14)
                .prix(9.5F)
                .couleur(couleur)
                .region(region)
                .build();

        Bouteille result = bouteilleRepository.save(bouteille);

        assertNotNull(result.getId());
        assertTrue(result.getId() > 0);

        assertEquals(couleur.getId(), result.getCouleur().getId());
        assertEquals(region.getId(), result.getRegion().getId());
    }

    @Test
    public void save_multipleBottles_onlyInsertBouteilles() {
        List<Couleur> couleurs =  couleurRepository.findAll();
        List<Region> regions = regionRepository.findAll();

        for (int i = 0; i < 100; i++) {
            Bouteille bouteille = Bouteille.builder()
                    .nom("bout"+i)
                    .petillant(false)
                    .millesime("2012")
                    .quantite(14)
                    .prix(9.5F)
                    .couleur(couleurs.get(i % couleurs.size()))
                    .region(regions.get((i*2) % regions.size()))
                    .build();

            Bouteille result = bouteilleRepository.save(bouteille);
            assertNotNull(result.getId());
            assertTrue(result.getId() > 0);
        }
    }

    @Test
    public void delete_bouteille_onlyDeleteBouteille() {
        long initialCouleurSize = couleurRepository.count();
        long initialRegionSize = regionRepository.count();
        long initialBouteilleSize = bouteilleRepository.count();

        bouteilleRepository.deleteAll();

        long finalCouleurSize = couleurRepository.count();
        long finalRegionSize = regionRepository.count();
        long finalBouteilleSize = bouteilleRepository.count();

        assertEquals(initialCouleurSize, finalCouleurSize);
        assertEquals(initialRegionSize, finalRegionSize);
        assertTrue(finalBouteilleSize < initialBouteilleSize);
        assertEquals(0, finalBouteilleSize);
    }
}