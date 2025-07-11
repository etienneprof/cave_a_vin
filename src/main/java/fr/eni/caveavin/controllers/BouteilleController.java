package fr.eni.caveavin.controllers;

import fr.eni.caveavin.bo.vin.Bouteille;
import fr.eni.caveavin.services.BouteilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Bouteille>> getAllBouteilles() {
        var list = bouteilleService.chargerToutesBouteilles();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }
}
