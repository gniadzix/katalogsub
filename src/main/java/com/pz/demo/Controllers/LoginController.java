package com.pz.demo.Controllers;

import com.google.common.hash.Hashing;
import com.pz.demo.ExLog;
import com.pz.demo.KatalogsubApplication;
import com.pz.demo.MenuAdminView;
import com.pz.demo.Exceptions.NotAdminException;
import com.pz.demo.HelloView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

@FXMLController
public class LoginController {
    @FXML
    private TextField txfLogin;
    @FXML
    private TextField txfPassword;
    private String user = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
    private String passwd = "2240e98c15f4815e5f2e24e9ebad6fa816893139b55219ba9d869f8824ac72e5";

    @Autowired
    private MenuAdminController menuAdminController;
    @Autowired
    private static ExLog exLog = ExLog.getInstance();

    private String hashedLogin;
    private String hashedPasswd;

    public void login() {

        hashedPasswd = Hashing.sha256()
                .hashString(txfPassword.getText(), StandardCharsets.UTF_8)
                .toString();
        hashedLogin = Hashing.sha256()
                .hashString(txfLogin.getText(), StandardCharsets.UTF_8)
                .toString();
        try {
            checkUser(hashedLogin, hashedPasswd);
        }
        catch (NotAdminException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błedny login lub hasło");
            alert.setHeaderText("Wprowadź poprawne dane");
            alert.show();
            exLog.addError("Złe dane logowania");
        }
    }

    public void goBack() {
        KatalogsubApplication.showView(HelloView.class);
        txfLogin.clear();
        txfPassword.clear();
    }

    public void checkUser(String login, String passwd) throws NotAdminException{
        if (login.equals(user) && (passwd.equals(this.passwd))) {
            KatalogsubApplication.showView(MenuAdminView.class);
            menuAdminController.loadWindow();
            txfLogin.clear();
            txfPassword.clear();
        } else throw new NotAdminException();
    }
}
