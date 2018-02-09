package com.pz.demo.DataBase;

import com.pz.demo.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBManager {
    private static DBManager ourInstance = new DBManager();

    public static DBManager getInstance() {
        return ourInstance;
    }

    @Autowired
    private TestData testData;
    @Autowired
    private RodzajsubstancjiRepository rodzajsubstancjiRepository;
    @Autowired
    private SubstancjaRepository substancjaRepository;


    private DBManager() {
    }

    public void loadTestData() {
        testData.loadData();
    }

    public void printTestData() {
        testData.printData();
    }

    public List<String> loadLiquids() {
        List<String> liquids = new ArrayList<>();
        for (Substancja substancja : this.substancjaRepository.findAll()
                ) {
            if (substancja.getIdKategorii() == this.rodzajsubstancjiRepository.findRodzajsubstancjiByNazwaRodzaju("ciecz").getIdRodzaju()) {
                liquids.add(substancja.getNazwaSubstancji());
            }
        }

        return liquids;

    }

    public List<String> loadGeneres() {
        List<String> generes = new ArrayList<>();
        for (Rodzajsubstancji rodzajSubstancji : this.rodzajsubstancjiRepository.findAll()) {
            generes.add(rodzajSubstancji.getNazwaRodzaju());

        }
        return generes;
    }

    public String getDesc(String nazwaSub) {
        String desc = this.substancjaRepository.findSubstancjaByNazwaSubstancji(nazwaSub).getOpis();
        return desc;
    }

    public String getImage(String nazwaSub) {
        String image = this.substancjaRepository.findSubstancjaByNazwaSubstancji(nazwaSub).getUrlObrazka();
        return image;
    }

    public void saveNewSubs(String nazwaSubstancji, String nazwaKategorii, String opis, String urlObrazka, String urlFilmu) {
        long idKat = this.rodzajsubstancjiRepository.findRodzajsubstancjiByNazwaRodzaju(nazwaKategorii).getIdRodzaju();
        this.substancjaRepository.save(Substancja.builder().nazwaSubstancji(nazwaSubstancji).idKategorii(idKat).opis(opis).urlObrazka(urlObrazka).urlFilmu(urlFilmu).build());
    }

    public void deleteSub(String subName) {
        this.substancjaRepository.delete(this.substancjaRepository.findSubstancjaByNazwaSubstancji(subName).getIdSubstancja());
    }

    public List<String> nameCheck() {
        List<String> list = new ArrayList<>();
        for (Substancja substancja : this.substancjaRepository.findAll()) {
            list.add(substancja.getNazwaSubstancji());
        }
        return list;
    }
}
