package fr.eni.caveavin.bo.client;

import fr.eni.caveavin.bo.vin.Bouteille;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cav_line")
public class LignePanier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity")
    private int qteCommande;

    @Column(name = "price", precision = 2)
    private float prix;

    @ManyToOne
    @JoinColumn(name = "bottle_id")
    private Bouteille bouteille;
}
