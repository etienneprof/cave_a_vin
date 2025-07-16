package fr.eni.caveavin.services;

import java.util.List;
import java.util.Optional;

import fr.eni.caveavin.bo.client.Client;
import fr.eni.caveavin.bo.client.LignePanier;
import fr.eni.caveavin.bo.client.Panier;
import fr.eni.caveavin.repository.ClientRepository;
import fr.eni.caveavin.repository.LignePanierRepository;
import fr.eni.caveavin.repository.PanierRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/** Proprio peut accéder à toutes les requêtes **/
@AllArgsConstructor
@Service
public class PanierServiceImpl implements PanierService {
	private PanierRepository pRepository;
	private ClientRepository cRepository;
	private LignePanierRepository lpRepository;

	/** Permet au Client (et au Proprio) de voir un certain Panier **/
	@Override
	public Panier chargerPanier(int idPanier) {
		// Validation de l'identifiant
		if (idPanier <= 0) {
			throw new RuntimeException("Identifiant n'existe pas");
		}

		final Optional<Panier> opt = pRepository.findById(idPanier);
		if (opt.isPresent()) {
			return opt.get();
		}
		// Identifiant correspond à aucun enregistrement en base
		throw new RuntimeException("Aucun panier ne correspond");
	}

	/** Permet au Client (et au Proprio) de voir les commandes d'un Client **/
	@Override
	public List<Panier> chargerCommandes(String idClient) {
		// Validation du client
		if (idClient == null) {
			throw new RuntimeException("Client n'existe pas");
		}
		return pRepository.findSameAgainButWithJPQL(idClient);
	}

	/**
	 * C'est les Panier qui ne sont pas commandés Pour afficher au Client ses Panier
	 * à valider
	 **/
	@Override
	public List<Panier> chargerPaniersNonPayes(String idClient) {
		// Validation du client
		final Client client = validerClient(idClient);
		return pRepository.findByClientPseudoAndNumCommandeIsNull(client.getPseudo());
	}

	/**
	 * Permet au Client (et au Proprio) d'enregistrer ou de mettre à jour son Panier
	 **/
	@Override
	public Panier ajouterOuMAJPanier(Panier panier) {
		// Validation du Panier
		if (panier == null) {
			throw new RuntimeException("Panier n'existe pas");
		}

		// Validation du client
		if (panier.getClient() == null || panier.getClient().getPseudo() == null) {
			throw new RuntimeException("Client n'existe pas");
		}
		final Client clientDB = validerClient(panier.getClient().getPseudo());
		panier.setClient(clientDB);

		// Validation de LignePanier
		final List<LignePanier> list = panier.getLignes();
		validerLignes(list);

        return pRepository.save(panier);
	}

	/**
	 * Permet au Client (et au Proprio) de valider un Panier (Création d'une
	 * commande)
	 **/
	@Override
	public Panier passerCommande(Panier panier) {
		// Validation du Panier
		if (panier == null) {
			throw new RuntimeException("Panier n'existe pas");
		}

		// Validation du client
		if (panier.getClient() == null || panier.getClient().getPseudo() == null) {
			throw new RuntimeException("Client n'existe pas");
		}
		final Client clientDB = validerClient(panier.getClient().getPseudo());
		panier.setClient(clientDB);

		// Validation de LignePanier
		final List<LignePanier> list = panier.getLignes();
		validerLignes(list);

		// Gestion de la commande et du paiement
		panier.setPaye(true);
		panier.setNumCommande(panier.getClient().getPseudo() + "_" + panier.getId());

        return pRepository.save(panier);
	}

	// Validation des données
	private Client validerClient(String idClient) {
		// Valider la Client
		if (idClient == null) {
			throw new RuntimeException("Identifiant n'existe pas");
		}

		final Optional<Client> opt = cRepository.findById(idClient);
		if (opt.isPresent()) {
			return opt.get();
		}
		// Identifiant correspond à aucun enregistrement en base
		throw new RuntimeException("Aucun client ne correspond");
	}
	// Validation des données
	private void validerLignes(List<LignePanier> list) {
		if (list == null || list.isEmpty()) {
			throw new RuntimeException("Il faut au moins une ligne dans le panier");
		}

		list.forEach(lp ->{
			if(lp.getId() != null) {
				final Optional<LignePanier> opt = lpRepository.findById(lp.getId());
				if (opt.isPresent()) {
					lp = opt.get();
				}
			}
		});
	}
}
