package fr.eni.caveavin.controllers;

import java.util.List;

import fr.eni.caveavin.bo.client.Panier;
import fr.eni.caveavin.services.PanierService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

/** rôle Owner et le rôle Client peuvent accéder à tout */

@AllArgsConstructor
@RestController
@RequestMapping("/caveavin/paniers")
public class PanierController {
	private PanierService pService;

	@GetMapping("/{id}")
	public ResponseEntity<?> rechercherPanierParId(@PathVariable("id") String idInPath) {
		// Toutes les données transmises par le protocole HTTP sont des chaines de
		// caractères par défaut
		// Il vaut mieux gérer les exceptions des données dans la méthode
		try {
			int id = Integer.parseInt(idInPath);
			final Panier emp = pService.chargerPanier(id);
			return ResponseEntity.ok(emp);
		} catch (NumberFormatException e) {
			// Statut 406 : No Acceptable
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Votre identifiant n'est pas un entier");
		}
	}

	@GetMapping("/client/actifs/{id}")
	public ResponseEntity<?> rechercherPaniersClientNonPayes(@PathVariable("id") String idClient) {
		final List<Panier> paniers = pService.chargerPaniersNonPayes(idClient);
		if (paniers == null || paniers.isEmpty()) {
			// Statut 204 : No Content - Pas de body car rien à afficher
			return ResponseEntity.noContent().build();
		}
		// Statut 200 : OK + dans le body paniers
		// Le contenu du body est directement injecté dans la méthode ok
		return ResponseEntity.ok(paniers);
	}

	@GetMapping("/client/commandes/{id}")
	public ResponseEntity<?> rechercherCommandesClient(@PathVariable("id") String idClient) {
		final List<Panier> paniers = pService.chargerCommandes(idClient);
		if (paniers == null || paniers.isEmpty()) {
			// Statut 204 : No Content - Pas de body car rien à afficher
			return ResponseEntity.noContent().build();
		}
		// Statut 200 : OK + dans le body paniers
		// Le contenu du body est directement injecté dans la méthode ok
		return ResponseEntity.ok(paniers);
	}

	@PostMapping
	public ResponseEntity<?> ajouterPanier(@Valid @RequestBody Panier panier) {
		pService.ajouterOuMAJPanier(panier);
		return ResponseEntity.ok(panier);
	}

	@PutMapping
	public ResponseEntity<?> passerCommande(@Valid @RequestBody Panier panier) {
		pService.passerCommande(panier);
		return ResponseEntity.ok(panier);
	}
}
