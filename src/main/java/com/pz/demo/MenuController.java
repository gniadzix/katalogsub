package com.pz.demo;

import com.pz.demo.DataBase.DBManager;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@FXMLController
public class MenuController {
    @FXML
    private Menu menuCiecze;
    @FXML
    Label lbsubname;
    @FXML
    TextArea descpane;
    @FXML
    ImageView imgview;
    @FXML
    GridPane mainGrid;
    @FXML
    TextField txfsearch;
    @FXML
    WebView wvVideo;

    @Autowired
    private DBManager dbManager = DBManager.getInstance();
    List<String> liquids = new ArrayList<>();

    public void goBack() {
        KatalogsubApplication.showView(LoginView.class);
    }

    public void loadMenus() {
        MenuItem menuItem;
        liquids = dbManager.loadLiquids();
        for (int i = 0; i < liquids.size(); i++) {
            menuCiecze.getItems().add(menuItem = new MenuItem(liquids.get(i)));
            menuItem.setId(liquids.get(i));
            final String nazwa = menuItem.getId();
            menuItem.setOnAction(event -> {
                loadData(nazwa);
            });
        }
    }

    public void search(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            NameCheck name = new NameCheck(txfsearch.getText());
            try {
                name.check();
                if(dbManager.search(txfsearch.getText())){
                    loadData(txfsearch.getText());
                }
            } catch (MyException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Brak substancji");
                alert.setHeaderText("Błędna nazwa substancji. Wprowadź poprawne dane");
                alert.show();
            }
        }
    }

    public void loadData(String name){
        lbsubname.setText(name);
        descpane.setText(dbManager.getDesc(name));
        Image image = new Image(dbManager.getImage(name));
        imgview.setImage(image);
        imgview.setOnMouseClicked(event1 ->
        {
            Pane pane = new Pane();
            pane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(600.0,800.0,false,false,false,false))));
            mainGrid.add(pane,1,0);
            pane.setOnMouseClicked(event2 -> {
                mainGrid.getChildren().remove(pane);
            });
        });
        wvVideo.getEngine().load("https://www.youtube.com/embed/9bZkp7q19f0");
    }
}
