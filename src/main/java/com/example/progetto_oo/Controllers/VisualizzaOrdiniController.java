package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.gui.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class VisualizzaOrdiniController {
    public Button GoHome;
    public Button GoProfilo;
    public Button GoLogout;
    Login login = new Login();
    Profilo profilo = new Profilo();
    Home home = new Home();

    public void GoLogoutOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) GoLogout.getScene().getWindow();
        stage.close();
        login.start(new Stage());
    }

    public void GoProfiloOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) GoProfilo.getScene().getWindow();
        stage.close();
        profilo.creaFinestraProfilo();
    }

    public void GoHomeOnAction(ActionEvent event)throws IOException {
        Stage stage = (Stage) GoHome.getScene().getWindow();
        stage.close();
        home.creaFinestraHome();
    }
}
