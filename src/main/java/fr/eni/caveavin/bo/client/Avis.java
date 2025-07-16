package fr.eni.caveavin.bo.client;

import fr.eni.caveavin.bo.vin.Bouteille;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity @Table(name = "comments")
public class Avis {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_date")
    @Past
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "bouteille_id")
    @NotNull
    private Bouteille bouteille;

    @Column(name = "quantity")
    @Min(1)
    private int quantite;

    @Column(name = "rating")
    @Min(1) @Max(5)
    private int note;

    @Column(name = "comment")
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
