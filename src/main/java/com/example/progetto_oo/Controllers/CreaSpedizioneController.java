package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.gui.Home;
import com.example.progetto_oo.gui.Login;
import com.example.progetto_oo.gui.Profilo;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CreaSpedizioneController {

    public Button GoHome;
    public Button GoProfilo;
    public Button GoLogout;
    Profilo profilo = new Profilo();
    Login login = new Login();
    Home home = new Home();

    public void GoHomeOnAction(ActionEvent event) throws IOException{
        Stage stage = (Stage) GoHome.getScene().getWindow();
        stage.close();
        home.creaFinestraHome();
    }

    public void GoProfiloOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) GoProfilo.getScene().getWindow();
        stage.close();
        profilo.creaFinestraProfilo();
    }

    public void GoLogoutOnAction(ActionEvent event) throws IOException{
        Stage stage = (Stage) GoLogout.getScene().getWindow();
        stage.close();
        login.start(new Stage());
    }
}
