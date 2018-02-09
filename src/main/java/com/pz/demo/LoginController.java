package com.pz.demo;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLController
public class LoginController {
    @FXML
    private TextField txfLogin;
    @FXML
    private TextField txfPassword;
    private String user = "admin";
    private String passwd = "adm1234";
    @Autowired
    private MenuAdminController menuAdminController;
    public void login() {
        if (txfLogin.getText().equals(user) && (txfPassword.getText().equals(passwd))){

            KatalogsubApplication.showView(MenuAdminView.class);
            menuAdminController.loadWindow();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błedny login lub hasło");
            alert.setHeaderText("Wprowadź poprawne dane");
            alert.show();
        }

   }

    public void goBack() {
        KatalogsubApplication.showView(HelloView.class);
        txfLogin.clear();
        txfPassword.clear();
    }
}
