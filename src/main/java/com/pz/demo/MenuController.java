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
import javafx.scene.layout.*;
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
                lbsubname.setText(nazwa);
                descpane.setText(dbManager.getDesc(nazwa));
                Image image = new Image(dbManager.getImage(nazwa));
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
            });
        }
    }
}
