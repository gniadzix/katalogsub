package com.pz.demo.DataBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestData {
    private RodzajsubstancjiRepository rodzajsubstancjiRepository;
    private SubstancjaRepository substancjaRepository;

@Autowired
    public TestData(RodzajsubstancjiRepository rodzajsubstancjiRepository, SubstancjaRepository substancjaRepository) {

        this.rodzajsubstancjiRepository = rodzajsubstancjiRepository;
        this.substancjaRepository = substancjaRepository;
    }

    public void loadData() {

        this.rodzajsubstancjiRepository.deleteAll();
        this.substancjaRepository.deleteAll();

        this.rodzajsubstancjiRepository.save(Rodzajsubstancji.builder().nazwaRodzaju("ciecz").build());
        this.rodzajsubstancjiRepository.save(Rodzajsubstancji.builder().nazwaRodzaju("gaz").build());



        this.substancjaRepository.save(Substancja.builder().nazwaSubstancji("sperma szatana").idKategorii(this.rodzajsubstancjiRepository.findRodzajsubstancjiByNazwaRodzaju("ciecz").getIdRodzaju()).opis("Substancja z rodziny szatanusrejpus, powoduje narodziny antychrysta z numerem 666 na czole").urlObrazka("http://img.wiocha.pl/images/5/7/570b6fdc3fca255c1a0de64a6ec16a3d.jpg").build());


    }

    public void printData() {
        System.out.println("Substancja");
        System.out.println("---------------------------------------");
        for (Substancja substancja : this.substancjaRepository.findAll()) {
            System.out.println(substancja.getNazwaSubstancji());
        }
        System.out.println();

    }

}