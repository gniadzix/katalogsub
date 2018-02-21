package com.pz.demo.DataBase;

import com.pz.demo.Exceptions.NotPictureException;
import com.pz.demo.Exceptions.NullSubstanceException;
import com.pz.demo.Exceptions.WrongUrlException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public ObservableList<String> loadLiquids() {
        ObservableList<String> liquids = FXCollections.observableArrayList();
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

    public void saveNewSubs(String nazwaSubstancji, String nazwaKategorii, String opis, String urlObrazka, String urlFilmu) throws WrongUrlException, NotPictureException {
        if (urlFilmu.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")&& urlObrazka.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
            if(urlObrazka.contains("jpg")||(urlObrazka.contains("png"))) {
                long idKat = this.rodzajsubstancjiRepository.findRodzajsubstancjiByNazwaRodzaju(nazwaKategorii).getIdRodzaju();
                this.substancjaRepository.save(Substancja.builder().nazwaSubstancji(nazwaSubstancji).idKategorii(idKat).opis(opis).urlObrazka(urlObrazka).urlFilmu(urlFilmu).build());
            }
            else throw new NotPictureException();
        }
        else throw new WrongUrlException();
    }

    public void deleteSub(String subName) {
        this.substancjaRepository.delete(this.substancjaRepository.findSubstancjaByNazwaSubstancji(subName).getIdSubstancja());
    }

    public void search(String text) throws NullSubstanceException {
        try {
            if (this.substancjaRepository.exists(this.substancjaRepository.findSubstancjaByNazwaSubstancji(text).getIdSubstancja()))
                ;
        } catch (NullPointerException ex) {
            throw new NullSubstanceException();
        }
    }

    public ObservableList<String> loadGas() {
        ObservableList<String> gas = FXCollections.observableArrayList();
        for (Substancja substancja : this.substancjaRepository.findAll()
                ) {
            if (substancja.getIdKategorii() == this.rodzajsubstancjiRepository.findRodzajsubstancjiByNazwaRodzaju("gaz").getIdRodzaju()) {
                gas.add(substancja.getNazwaSubstancji());
            }
        }

        return gas;

    }

    public ObservableList<String> loadConstSub() {
        ObservableList<String> sub = FXCollections.observableArrayList();
        for (Substancja substancja : this.substancjaRepository.findAll()
                ) {
            if (substancja.getIdKategorii() == this.rodzajsubstancjiRepository.findRodzajsubstancjiByNazwaRodzaju("gaz").getIdRodzaju()) {
                sub.add(substancja.getNazwaSubstancji());
            }
        }

        return sub;

    }
}
