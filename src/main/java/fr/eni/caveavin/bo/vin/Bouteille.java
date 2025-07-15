package fr.eni.caveavin.bo.vin;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity @Table(name = "cav_bottles")
public class Bouteille {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 250, unique = true)
    @NotBlank @Size(min = 1, max = 250, message = "{exception.bouteille.nom}")
    private String nom;

    @Column(name = "sparkling")
    private boolean petillant;

    @Column(name = "vintage", length = 100)
    @Size(min = 1, max = 100, message = "{exception.bouteille.millesime}")
    private String millesime;

    @Column(name = "quantity")
    @Min(value = 1, message = "{exception.bouteille.quantite}")
    private int quantite;

    @Column(name = "price", precision = 2)
    @Min(value = 1, message = "{exception.bouteille.prix}")
    private float prix;

    @ManyToOne
    @JoinColumn(name = "color_id")
    @EqualsAndHashCode.Exclude
    @NotNull(message = "{exception.bouteille.couleur}")
    private Couleur couleur;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @EqualsAndHashCode.Exclude
    @NotNull(message = "{exception.bouteille.region}")
    private Region region;
}
