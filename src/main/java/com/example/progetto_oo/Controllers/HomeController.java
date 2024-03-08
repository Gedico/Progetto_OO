package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.Alerts.Alerts;
import com.example.progetto_oo.Database.Connessione;
import com.example.progetto_oo.Database.OperatoreDao;
import com.example.progetto_oo.Database.OrdiniDao;
import com.example.progetto_oo.gui.Login;
import com.example.progetto_oo.gui.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public Button GoLogout;
    public Button GoProfilo;
    public Button GoVisualizzaReport;
    public Button GoCreaSpedizione;
    public Button GoVisualizzaOrdini;
    public Text ordiniDaGestire;
    public Button chiudiHome;
    public Button riduciHome;
    public Text benvenutoOperatore;
    public Button Aggiorna;
    public Text OrdiniGestiti;
    public AnchorPane patternHome;
    public AnchorPane patternVisualizzaReport;
    public Button goHome;
    public AnchorPane patternVisualizzaOrdini;
    public AnchorPane patternProfilo;
    public AnchorPane patternCreaSpedizione;
    Login login = new Login();
    Connessione connessione = new Connessione();
    OrdiniDao ordiniDao = new OrdiniDao(connessione);
    Alerts Alerts = new Alerts();



    public void GoLogoutOnAction(ActionEvent event) throws IOException {//metodo per tornare alla finestra di login con popu-up che chiede se siamo sicuri di voler uscire
        boolean risultatio = Alerts.mostraPopupConferma("Conferma", "Sei sicuro di voler uscire?");
        if(risultatio) {
            Stage stage = (Stage) GoLogout.getScene().getWindow();
            stage.close();
            login.start(new Stage());
        }
    }

   public void aggiornaOnAction(ActionEvent event) {
     ordiniDaGestire.setText("" + ordiniDao.ordiniDaGestire());
     OrdiniGestiti.setText("" + ordiniDao.ordiniGestiti());
    }

    public void chiudiOnAction(ActionEvent event) {//metodo per il bottone chiudi
            Stage stage = (Stage) chiudiHome.getScene().getWindow();
            stage.close();
    }

    public void riduciHomeOnAction(ActionEvent event) {//metodo per il bottone riduci
        Stage stage = (Stage) riduciHome.getScene().getWindow();
        stage.setIconified(true);
    }

    public void CambiaVisione(ActionEvent event) {//Metodo per poter cambiare "pagina"
        if(event.getSource() == GoProfilo){
            patternHome.setVisible(false);
            patternProfilo.setVisible(true);
            patternCreaSpedizione.setVisible(false);
            patternVisualizzaOrdini.setVisible(false);
            patternVisualizzaReport.setVisible(false);
        }else if(event.getSource() == GoVisualizzaOrdini){
            patternHome.setVisible(false);
            patternProfilo.setVisible(false);
            patternCreaSpedizione.setVisible(false);
            patternVisualizzaOrdini.setVisible(true);
            patternVisualizzaReport.setVisible(false);
        }else if(event.getSource() == GoCreaSpedizione){
            patternHome.setVisible(false);
            patternProfilo.setVisible(false);
            patternCreaSpedizione.setVisible(true);
            patternVisualizzaOrdini.setVisible(false);
            patternVisualizzaReport.setVisible(false);
    }   else if(event.getSource() == GoVisualizzaReport){
            patternHome.setVisible(false);
            patternProfilo.setVisible(false);
            patternCreaSpedizione.setVisible(false);
            patternVisualizzaOrdini.setVisible(false);
            patternVisualizzaReport.setVisible(true);
        }else if(event.getSource() == goHome){
            patternHome.setVisible(true);
            patternProfilo.setVisible(false);
            patternCreaSpedizione.setVisible(false);
            patternVisualizzaOrdini.setVisible(false);
            patternVisualizzaReport.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ordiniDaGestire.setText("" + ordiniDao.ordiniDaGestire());
        OrdiniGestiti.setText("" + ordiniDao.ordiniGestiti());
    }
}
