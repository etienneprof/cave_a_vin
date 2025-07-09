package fr.eni.caveavin.bo.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity @Table(name = "cav_address")
public class Adresse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "street", length = 250)
    private String rue;

    @Column(name = "zip_code",  length = 5)
    private String codePostal;

    @Column(name = "city", length = 150)
    private String ville;
}
