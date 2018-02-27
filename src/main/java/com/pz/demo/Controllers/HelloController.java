package com.pz.demo.Controllers;

import com.pz.demo.*;
import com.pz.demo.DataBase.*;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.FXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@FXMLController
public class HelloController {
    @Autowired
    private DBManager dbManager = DBManager.getInstance();
    @FXML
    private Label lblTwitter;

    @Autowired
    private static ExLog exLog = ExLog.getInstance();
    @Autowired
    private HelloView helloView;
    @Autowired
    private LoginView loginView;
    @Autowired
    private MenuAdminView menuAdminView;
    @Autowired
    private MenuView menuView;


    public HelloController() {
        exLog.start();

    }

    public void goToMenu() {
        KatalogsubApplication.showView(MenuView.class);
        try {
            dbManager.loadTestData();
        }
        catch (Exception ex){
            exLog.addError("Błąd ładowania danych z bazy");
        }
        dbManager.printTestData();
    }

    public void openAdmPane() {
        KatalogsubApplication.showView(LoginView.class);
        dbManager.loadTestData();

    }

    public void loadTwitter() throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("fn6CqDgi6hRokpZPjw5NdkaSp");
        cb.setOAuthConsumerSecret("0vqpuy8gcIT666TKaf7sfsxctqG9LI0mCwfeJaxhS2KdLU9cVO");
        cb.setOAuthAccessToken("965953184035475457-T8Iw3aowky2FXRKKelNZ6AThcoXb1Am");
        cb.setOAuthAccessTokenSecret("RTxDK57ZTNXpfcOIIeCtSAQkJqFT6e3j3ge3JpELXMP3c");
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        List<Status> statuses = twitter.getHomeTimeline();
        for (Status status : statuses) {
            lblTwitter.setText(status.getUser().getName() + ":" +
                    status.getText());
        }
    }

    public void changeStyleToRed(ActionEvent actionEvent){
        String css = "";
        try {
            css = Paths.get("src/main/resources/com/pz/demo/red.css").toUri().toURL().toExternalForm();
        } catch (MalformedURLException e) {


        }
        helloView.getView().getStylesheets().clear();
        helloView.getView().getStylesheets().add(css);
        loginView.getView().getStylesheets().clear();
        loginView.getView().getStylesheets().add(css);
        menuAdminView.getView().getStylesheets().clear();
        menuAdminView.getView().getStylesheets().add(css);
        menuView.getView().getStylesheets().clear();
        menuView.getView().getStylesheets().add(css);
    }

    public void changeStyleToGreen(ActionEvent actionEvent){
        String css = "";
        try {
            css = Paths.get("src/main/resources/com/pz/demo/green.css").toUri().toURL().toExternalForm();
        } catch (MalformedURLException e) {
        }
        helloView.getView().getStylesheets().clear();
        helloView.getView().getStylesheets().add(css);
        loginView.getView().getStylesheets().clear();
        loginView.getView().getStylesheets().add(css);
        menuAdminView.getView().getStylesheets().clear();
        menuAdminView.getView().getStylesheets().add(css);
        menuView.getView().getStylesheets().clear();
        menuView.getView().getStylesheets().add(css);
    }
    public void changeStyleToGrey(ActionEvent actionEvent){
        String css = "";
        try {
            css = Paths.get("src/main/resources/com/pz/demo/grey.css").toUri().toURL().toExternalForm();
        } catch (MalformedURLException e) {
        }
        helloView.getView().getStylesheets().clear();
        helloView.getView().getStylesheets().add(css);
        loginView.getView().getStylesheets().clear();
        loginView.getView().getStylesheets().add(css);
        menuAdminView.getView().getStylesheets().clear();
        menuAdminView.getView().getStylesheets().add(css);
        menuView.getView().getStylesheets().clear();
        menuView.getView().getStylesheets().add(css);
    }
}
