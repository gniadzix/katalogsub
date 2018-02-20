package com.pz.demo;

import com.pz.demo.DataBase.DBManager;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@FXMLController
public class MenuAdminController {
    @FXML
    TextField txfName;
    @FXML
    ChoiceBox dlGenere;
    @FXML
    TextArea txaDesc;
    @FXML
    TextField txfPicUrl;
    @FXML
    TextField txfVidUrl;
    @FXML
    TextField txfSubNameToDelete;
    @Autowired
    MenuController menuController;

    @Autowired
    private DBManager dbManager = DBManager.getInstance();
    List<String> generes = new ArrayList<>();

    public void openStart() {
        KatalogsubApplication.showView(MenuView.class);
        //menuController.loadMenus();
        txaDesc.clear();
        txfName.clear();
        txfPicUrl.clear();
        txfVidUrl.clear();
    }

    public void loadWindow() {
        generes = dbManager.loadGeneres();
        for (int i = 0; i < generes.size(); i++) {
            dlGenere.getItems().add(generes.get(i));
        }

    }

    public void newSub() {
        if (txfName.getText().isEmpty() || txaDesc.getText().isEmpty() || txfPicUrl.getText().isEmpty() || txfVidUrl.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Puste pola");
            alert.setHeaderText("Wprowadź wszystkie dane");
            alert.show();
        } else {
            try {
                dbManager.saveNewSubs(txfName.getText(), dlGenere.getSelectionModel().getSelectedItem().toString(), txaDesc.getText(), txfPicUrl.getText(), txfVidUrl.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Nie udało zapisać się do bazy danych, spróbój ponownie");
                alert.show();
            }
            dbManager.printTestData();
        }
    }

    public void deleteSub() {
        try {
            dbManager.deleteSub(txfSubNameToDelete.getText());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Brak substancji");
            alert.setHeaderText("Błędna nazwa substancji. Wprowadź poprawne dane");
            alert.show();
        }
    }

    public void clearAddSub() {
        txfName.clear();
        dlGenere.setValue(null);
        txaDesc.clear();
        txfPicUrl.clear();
        txfVidUrl.clear();
    }

    public void clearDeleteSub() {
        txfSubNameToDelete.clear();
    }
}
