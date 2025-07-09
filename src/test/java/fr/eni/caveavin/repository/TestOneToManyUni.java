package fr.eni.caveavin.repository;

import static org.junit.jupiter.api.Assertions.*;

import fr.eni.caveavin.bo.client.LignePanier;
import fr.eni.caveavin.bo.client.Panier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestOneToManyUni {
	@Autowired
	PanierRepository prepository;

	@Autowired
	LignePanierRepository lprepository;

	@Test
	public void save_PanierWithLigne_insertBoth() {
		int initialPanierSize = prepository.findAll().size();
		int initialLigneSize = lprepository.findAll().size();

		LignePanier l = LignePanier.builder()
				.qteCommande(8)
				.prix(12.45F)
				.build();

		Panier p = Panier.builder()
				.numCommande("C00124")
				.prixTotal(8*12.45F)
				.paye(false)
				.build();

		p.getLignes().add(l);

		Panier result = prepository.save(p);

		assertNotNull(result.getId());
		assertTrue(result.getId() > 0);

		assertNotNull(result.getLignes().getFirst().getId());
		assertTrue(result.getLignes().getFirst().getId() > 0);

		int finalPanierSize = prepository.findAll().size();
		int finalLigneSize = lprepository.findAll().size();

		assertEquals(initialPanierSize + 1, finalPanierSize);
		assertEquals(initialLigneSize + 1, finalLigneSize);

		System.out.println(result);
	}

	@Test
	public void save_LigneToExistingPanier_insertOnlyLigne() {
		int initialPanierSize = prepository.findAll().size();
		int initialLigneSize = lprepository.findAll().size();

		LignePanier l = LignePanier.builder()
				.qteCommande(8)
				.prix(12.45F)
				.build();

		Panier p = prepository.findAll().getFirst();

		p.getLignes().add(l);

		Panier result = prepository.save(p);

		assertNotNull(result.getId());
		assertTrue(result.getId() > 0);

		assertNotNull(result.getLignes().getLast().getId());
		assertTrue(result.getLignes().getLast().getId() > 0);

		int finalPanierSize = prepository.findAll().size();
		int finalLigneSize = lprepository.findAll().size();

		assertEquals(initialPanierSize, finalPanierSize);
		assertEquals(initialLigneSize + 1, finalLigneSize);

		System.out.println(result);
	}

	@Test
	public void delete_Panier_deleteAllLigne() {
		int initialPanierSize = prepository.findAll().size();
		int initialLigneSize = lprepository.findAll().size();

		Panier p = prepository.findAll().getFirst();
		prepository.delete(p);

		int finalPanierSize = prepository.findAll().size();
		int finalLigneSize = lprepository.findAll().size();

		assertEquals(initialPanierSize - 1, finalPanierSize);
		assertEquals(initialLigneSize - 1, finalLigneSize);
	}

	@Test
	public void delete_Panier_removeOrphans() {
		int initialPanierSize = prepository.findAll().size();
		int initialLigneSize = lprepository.findAll().size();

		Panier p = prepository.findAll().getFirst();
		// p.setLignes(new ArrayList<>()); // ne fonctionne pas car création d'une nouvelle instance non gérée par l'EntityManager
		p.getLignes().clear(); // Fonctionne car on conserve l'entité gérée par l'EntityManager

		prepository.save(p);
		prepository.delete(p);

		int finalPanierSize = prepository.findAll().size();
		int finalLigneSize = lprepository.findAll().size();

		assertEquals(initialPanierSize - 1, finalPanierSize);
		assertEquals(initialLigneSize - 1, finalLigneSize);
	}
}
