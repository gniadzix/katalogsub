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
public class Rodzajsubstancji {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idRodzaju;
    private String nazwaRodzaju;
}

