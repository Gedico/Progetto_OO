package com.example.progetto_oo.Controllers;
import com.example.progetto_oo.Database.Connessione;
import com.example.progetto_oo.Database.OperatoreDao;
import com.example.progetto_oo.Login;
import com.example.progetto_oo.Mockup.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public Button GoHome;
    public Button GoVisualizzaOrdini;
    public Button GoProfilo;
    public Button Home;
    public Button GoLogout;
    public Button Login;
    public PasswordField Password;
    public TextField Username;
    public Text avvisoErrore;
    public Button GoCreaSpedizione;
    public Button GoVisualizzaReport;
    VisualizzaReport visualizzaReport = new VisualizzaReport();
    CreaSpedizione creaSpedizione = new CreaSpedizione();
    Connessione connessione = new Connessione();
    OperatoreDao operatoreDao = new OperatoreDao(connessione);
    Login login = new Login();
    Profilo profilo = new Profilo();
    Home home = new Home();
    VisualizzaOrdini visualizzaOrdini = new VisualizzaOrdini();


    public void LoginOnAction(ActionEvent event) throws IOException {
        if (Username.getText().isBlank()) {
            avvisoErrore.setText("inserire l'Username");
        } else if (Password.getText().isBlank()) {
            avvisoErrore.setText("inserire la Password");
        } else if (operatoreDao.verificaLogin(Username.getText(),Password.getText())){
            Stage stage = (Stage) Login.getScene().getWindow();
            stage.close();
            home.creaFinestraHome();
        }else avvisoErrore.setText("Login fallito");
        }

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

    public void GoHomeOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) GoHome.getScene().getWindow();
        stage.close();
        home.creaFinestraHome();
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
}


