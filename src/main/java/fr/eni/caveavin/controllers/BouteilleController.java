package fr.eni.caveavin.controllers;

import fr.eni.caveavin.bo.vin.Bouteille;
import fr.eni.caveavin.services.BouteilleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caveavin/bouteilles")
public class BouteilleController {

    private final BouteilleService bouteilleService;

    @Autowired
    public BouteilleController(BouteilleService bouteilleService) {
        this.bouteilleService = bouteilleService;
    }

    @GetMapping
    @Operation(summary = "Permet de récupérer toutes les bouteilles de l'application", description = "Retourne une liste de bouteilles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "Tout s'est bien passé !")
    })
    public ResponseEntity<List<Bouteille>> getAllBouteilles() {
        var list = bouteilleService.chargerToutesBouteilles();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Bouteille> getBouteilleById(@PathVariable int id) {
        Bouteille bouteille;
        try {
            bouteille = bouteilleService.chargerBouteilleParId(id);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bouteille);
    }

    @GetMapping("/couleur/{id:\\d+}")
    public ResponseEntity<List<Bouteille>> getBouteillesByCouleurId(@PathVariable int id) {
        List<Bouteille> list;
        try {
            list = bouteilleService.chargerBouteillesParCouleur(id);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/region/{id:\\d+}")
    public ResponseEntity<List<Bouteille>> getBouteillesByRegionId(@PathVariable int id) {
        List<Bouteille> list;
        try {
            list = bouteilleService.chargerBouteillesParRegion(id);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Bouteille> saveBouteille(@Valid @RequestBody Bouteille bouteille) {
        if (bouteille.getId() != null && bouteille.getId() >= 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        var newBouteille = bouteilleService.save(bouteille);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBouteille);
    }

    @PutMapping
    public ResponseEntity<Bouteille> updateBouteille(
            @Valid @RequestBody Bouteille bouteille
    ) {
        if (bouteille.getId() == null || bouteille.getId() <= 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        var newBouteille = bouteilleService.save(bouteille);
        return ResponseEntity.ok(newBouteille);
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Bouteille> updateBouteilleWithId(
            @PathVariable int id,
            @Valid @RequestBody Bouteille bouteille
    ) {
        if (bouteille.getId() == null || bouteille.getId() <= 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        if (bouteille.getId() != id)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        var newBouteille = bouteilleService.save(bouteille);
        return ResponseEntity.ok(newBouteille);
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<String> deleteBouteilleById(@PathVariable int id) {
        bouteilleService.delete(id);
        return ResponseEntity.ok("Safely deleted bottle " + id);
        // return ResponseEntity.noContent().build();
    }
}
