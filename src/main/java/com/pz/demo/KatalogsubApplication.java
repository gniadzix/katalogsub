package com.pz.demo;

import com.pz.demo.DataBase.*;
import com.sun.xml.internal.stream.util.ReadOnlyIterator;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class KatalogsubApplication extends AbstractJavaFxApplicationSupport {


    public static void main(String[] args) {
        launchApp(KatalogsubApplication.class, HelloView.class, args);
    }

    /*@Bean
    CommandLineRunner runner(RodzajsubstancjiRepository rodzajsubstancjiRepository) {
        return args -> Stream.of("Ciecz", "Gaz").forEach(n -> rodzajsubstancjiRepository.save(Rodzajsubstancji.builder().nazwaRodzaju(n).build()));

    }
    @Bean
    CommandLineRunner runner2(SubstancjaRepository substancjaRepository, RodzajsubstancjiRepository rodzajsubstancjiRepository) {
        return args -> Stream.of("sperma szatana").forEach(n -> substancjaRepository.save(Substancja.builder().nazwaSubstancji(n).opis("Substancja z rodziny szatanusrejpus, powoduje narodziny antychrysta z numerem 666 na czole").urlObrazka("http://img.wiocha.pl/images/5/7/570b6fdc3fca255c1a0de64a6ec16a3d.jpg").build()));

    }*/

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

    }
}
