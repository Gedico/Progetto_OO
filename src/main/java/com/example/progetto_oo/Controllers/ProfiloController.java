package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.gui.Home;
import com.example.progetto_oo.gui.Login;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfiloController {

    public Button GoHome;
    public Button GoLogout;
    Login login = new Login();
    Home home = new Home();

    public void GoHomeOnAction(ActionEvent event)throws IOException {
        Stage stage = (Stage) GoHome.getScene().getWindow();
        stage.close();
        home.creaFinestraHome();
    }

    public void GoLogoutOnAction(ActionEvent event)throws  IOException {
        Stage stage = (Stage) GoLogout.getScene().getWindow();
        stage.close();
        login.start(new Stage());
    }
}
