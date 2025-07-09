package fr.eni.caveavin.bo.client;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity @Table(name = "cav_shopping_cart")
public class Panier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_number", length = 200)
    private String numCommande;

    @Column(name = "total_price", precision = 2)
    private float prixTotal;

    @Column(name = "paid")
    private boolean paye;

    @OneToMany(cascade = CascadeType.ALL,  orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "shopping_cart_id")
    private @Builder.Default List<LignePanier> lignes = new ArrayList<>();
}
