package fr.eni.caveavin.bo.vin;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity @Table(name = "cav_bottles")
public class Bouteille {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 250)
    private String nom;

    @Column(name = "sparkling")
    private boolean petillant;

    @Column(name = "vintage", length = 100)
    private String millesime;

    @Column(name = "quantity")
    private int quantite;

    @Column(name = "price", precision = 2)
    private float prix;

    @ManyToOne
    @JoinColumn(name = "color_id")
    @EqualsAndHashCode.Exclude
    private Couleur couleur;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @EqualsAndHashCode.Exclude
    private Region region;
}
