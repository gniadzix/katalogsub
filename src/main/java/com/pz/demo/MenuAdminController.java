package com.pz.demo;

import com.pz.demo.DataBase.DBManager;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    @Autowired
    private DBManager dbManager = DBManager.getInstance();
    List<String> generes = new ArrayList<>();

    public void openStart() {
        KatalogsubApplication.showView(HelloView.class);
    }
    public void loadWindow(){
        generes = dbManager.loadGeneres();
        for (int i=0; i<generes.size();i++) {
            dlGenere.getItems().add(generes.get(i));
        }

    }

    public void newSub() {
        dbManager.saveNewSubs(txfName.getText(),dlGenere.getSelectionModel().getSelectedItem().toString(),txaDesc.getText(),txfPicUrl.getText(),txfVidUrl.getText());
        dbManager.printTestData();
    }
}
