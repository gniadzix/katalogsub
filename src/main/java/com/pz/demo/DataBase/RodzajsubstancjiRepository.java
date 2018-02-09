package com.pz.demo.DataBase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RodzajsubstancjiRepository extends CrudRepository <Rodzajsubstancji, Long>{

    Rodzajsubstancji findRodzajsubstancjiByNazwaRodzaju(String nazwaSub);
}
