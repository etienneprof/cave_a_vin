package fr.eni.caveavin.services;

import java.util.List;

import fr.eni.caveavin.bo.vin.Bouteille;

public interface BouteilleService {
    Bouteille save(Bouteille bouteille);

	void delete(int id);

	List<Bouteille> chargerToutesBouteilles();
	
	Bouteille chargerBouteilleParId(int idBouteille);

	List<Bouteille> chargerBouteillesParRegion(int idRegion);

	List<Bouteille> chargerBouteillesParCouleur(int idCouleur);
}
