package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.Database.Connessione;
import com.example.progetto_oo.Database.OperatoreDao;
import com.example.progetto_oo.Database.OrdiniDao;
import com.example.progetto_oo.gui.Login;
import com.example.progetto_oo.gui.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    public Button GoLogout;
    public Button GoProfilo;
    public Button GoVisualizzaReport;
    public Button GoCreaSpedizione;
    public Button GoVisualizzaOrdini;
    public Text ordiniDaGestire;
    Login login = new Login();
    Profilo profilo = new Profilo();
    VisualizzaOrdini visualizzaOrdini = new VisualizzaOrdini();
    CreaSpedizione creaSpedizione = new CreaSpedizione();
    VisualizzaReport visualizzaReport = new VisualizzaReport();
    Connessione connessione = new Connessione();
    OrdiniDao ordiniDao = new OrdiniDao(connessione);


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

    public void GoVisualizzaOrdiniOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) GoVisualizzaOrdini.getScene().getWindow();
        stage.close();
        visualizzaOrdini.creaFinestraVisualizzaOrdini();
    }

    public void GoCreaSpedizioneOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) GoCreaSpedizione.getScene().getWindow();
        stage.close();
        creaSpedizione.creaFinestraCreaSpedizione();
    }

    public void GoVisualizzaReportOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) GoVisualizzaReport.getScene().getWindow();
        stage.close();
        visualizzaReport.creaFinestraVisualizzaReport();
    }

    public void AvviaOnAction(ActionEvent event) {
    }
}
