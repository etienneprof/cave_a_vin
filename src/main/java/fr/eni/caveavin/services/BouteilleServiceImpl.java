package fr.eni.caveavin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.caveavin.bo.vin.Bouteille;
import fr.eni.caveavin.bo.vin.Couleur;
import fr.eni.caveavin.bo.vin.Region;
import fr.eni.caveavin.repository.BouteilleRepository;
import fr.eni.caveavin.repository.CouleurRepository;
import fr.eni.caveavin.repository.RegionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BouteilleServiceImpl implements BouteilleService {
	private BouteilleRepository bRepository;
	private RegionRepository rRepository;
	private CouleurRepository cRepository;

	@Override
	public List<Bouteille> chargerToutesBouteilles() {
		return bRepository.findAll();
	}

	@Override
	public Bouteille chargerBouteilleParId(int idBouteille) {
		// Validation de l'identifiant
		if (idBouteille <= 0) {
			throw new RuntimeException("Identifiant n'existe pas");
		}

		final Optional<Bouteille> opt = bRepository.findById(idBouteille);
		if (opt.isPresent()) {
			return opt.get();
		}
		// Identifiant correspond à aucun enregistrement en base
		throw new RuntimeException("Aucune bouteille ne correspond");
	}

	@Override
	public List<Bouteille> chargerBouteillesParRegion(int idRegion) {
		final Region rDB = validerRegion(idRegion);

		final List<Bouteille> listeDB = bRepository.findByRegionNom(rDB.getNom());
		if (listeDB == null || listeDB.isEmpty()) {
			throw new RuntimeException("Aucune bouteille ne correspond");
		}
		return listeDB;		
	}

	private Region validerRegion(int idRegion) {
		// Valider la Region
		if (idRegion <= 0) {
			throw new RuntimeException("Identifiant n'existe pas");
		}

		final Optional<Region> opt = rRepository.findById(idRegion);
		if (opt.isPresent()) {
			return opt.get();
		}
		// Identifiant correspond à aucun enregistrement en base
		throw new RuntimeException("Aucune région ne correspond");
	}

	@Override
	public List<Bouteille> chargerBouteillesParCouleur(int idCouleur) {
		final Couleur cDB = validerCouleur(idCouleur);

		final List<Bouteille> listeDB = bRepository.findByCouleurNom(cDB.getNom());
		if (listeDB == null || listeDB.isEmpty()) {
			throw new RuntimeException("Aucune bouteille ne correspond");
		}
		return listeDB;		
	}

	private Couleur validerCouleur(int idCouleur) {
		// Valider la Couleur
		if (idCouleur <= 0) {
			throw new RuntimeException("Identifiant n'existe pas");
		}

		final Optional<Couleur> opt = cRepository.findById(idCouleur);
		if (opt.isPresent()) {
			return opt.get();
		}
		// Identifiant correspond à aucun enregistrement en base
		throw new RuntimeException("Aucune couleur de vin ne correspond");
	}
}
