package com.pz.demo.Controllers;

import com.pz.demo.*;
import com.pz.demo.DataBase.DBManager;
import com.pz.demo.Exceptions.NullSubstanceException;
import com.pz.demo.Exceptions.SwearException;
import com.pz.demo.LoginView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import org.springframework.beans.factory.annotation.Autowired;

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
    @FXML
    private ListView listView;

    @Autowired
    private DBManager dbManager = DBManager.getInstance();
    @Autowired
    private static ExLog exLog = ExLog.getInstance();
    private ObservableList<String> liquids = FXCollections.observableArrayList();
    private ObservableList<String> gas = FXCollections.observableArrayList();
    private ObservableList<String> constSub = FXCollections.observableArrayList();

    public void goBack() {
        KatalogsubApplication.showView(LoginView.class);
    }

    public void search(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            NameCheck name = new NameCheck();
            try {
                name.ifSwear(txfsearch.getText());
                dbManager.search(txfsearch.getText());
                    loadData(txfsearch.getText());

            } catch (SwearException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Brak substancji");
                alert.setHeaderText("Błędna nazwa substancji. Wprowadź poprawne dane. Nie przeklinaj");
                alert.show();
                exLog.addError("Przekleństwo");
            }
            catch (NullSubstanceException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Brak substancji");
                alert.setHeaderText("Błędna nazwa substancji. Wprowadź poprawne dane.");
                alert.show();
                exLog.addError("Błędna nazwa substancji");
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

    public void showLiquids() {
        listView.setItems(null);
        liquids = dbManager.loadLiquids();
        listView.setItems(liquids);
    }

    public void selectSub(MouseEvent mouseEvent) {
        try{
            loadData(listView.getSelectionModel().getSelectedItem().toString());
        }
        catch (Exception ex){
            exLog.addError("Błąd wyboru substancji");
        }
    }

    public void showGas() {
        listView.setItems(null);
        gas = dbManager.loadGas();
        listView.setItems(gas);
    }

    public void showConstSubs() {
        listView.setItems(null);
        constSub = dbManager.loadConstSub();
        listView.setItems(constSub);
    }
}
