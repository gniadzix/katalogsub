package com.pz.demo.DataBase;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String opis;
    private String urlObrazka;
    private String urlFilmu;
}
