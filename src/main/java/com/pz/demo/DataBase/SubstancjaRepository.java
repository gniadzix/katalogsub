package com.pz.demo.DataBase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubstancjaRepository extends CrudRepository <Substancja, Long> {
    Substancja findSubstancjaByIdKategorii(long id);
    Substancja findSubstancjaByNazwaSubstancji(String nazwa);
}
