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

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

    }
}
