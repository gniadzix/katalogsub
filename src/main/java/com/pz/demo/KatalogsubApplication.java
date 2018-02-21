package com.pz.demo;

import com.pz.demo.Views.HelloView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
