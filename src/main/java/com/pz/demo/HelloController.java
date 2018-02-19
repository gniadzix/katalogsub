package com.pz.demo;

import com.pz.demo.DataBase.*;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLController
public class HelloController {
    @Autowired
    private DBManager dbManager = DBManager.getInstance();
    @Autowired
    private MenuController menuController;

    public void goToMenu() {
        KatalogsubApplication.showView(MenuView.class);
        try {
            dbManager.loadTestData();
        }
        catch (Exception ex){}
        finally {
            menuController.loadMenus();
        }
        dbManager.printTestData();
    }

    public void openAdmPane() {
        KatalogsubApplication.showView(LoginView.class);
        dbManager.loadTestData();
    }
}
