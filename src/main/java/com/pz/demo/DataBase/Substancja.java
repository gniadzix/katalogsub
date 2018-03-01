package com.pz.demo.DataBase;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Substancja {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idSubstancja;
    private String nazwaSubstancji;
    private long idKategorii;
    @Column(columnDefinition = "VARCHAR(1000)")
    private String opis;
    private String urlObrazka;
    private String urlFilmu;
}
