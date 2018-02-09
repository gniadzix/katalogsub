package com.pz.demo;

import com.pz.demo.DataBase.DBManager;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
