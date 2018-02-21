package com.pz.demo.Controllers;

import com.pz.demo.DataBase.*;
import com.pz.demo.KatalogsubApplication;
import com.pz.demo.Views.LoginView;
import com.pz.demo.Views.MenuView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

@FXMLController
public class HelloController {
    @Autowired
    private DBManager dbManager = DBManager.getInstance();
    @FXML
    private Label lblTwitter;

    public void goToMenu() {
        KatalogsubApplication.showView(MenuView.class);
        try {
            dbManager.loadTestData();
        }
        catch (Exception ex){}
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
}
