package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.Alerts.Alerts;
import com.example.progetto_oo.Database.Connessione;
import com.example.progetto_oo.Database.OperatoreDao;
import com.example.progetto_oo.Database.Ordine;
import com.example.progetto_oo.Database.OrdiniDao;
import com.example.progetto_oo.gui.Login;
import com.example.progetto_oo.gui.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    public AnchorPane patternCreaSpedizione;
    public TableView<Ordine> TabellaOrdiniHome;
    public TableColumn IDOrdineHome;
    public TableColumn CFhome;
    public TableColumn DATAHome;
    public TableColumn TIPOHome;
    public TableColumn IDMERHome;
    public TableColumn QUANTHome;
    public TableColumn DESCHome;
    public TableView tabellaRicercaOrdini;


    Login login = new Login();
    Connessione connessione = new Connessione();
    OrdiniDao ordiniDao = new OrdiniDao(connessione);
    Alerts Alerts = new Alerts();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ordiniDaGestire.setText("" + ordiniDao.ordiniDaGestire());
        OrdiniGestiti.setText("" + ordiniDao.ordiniGestiti());
        riempiTabellaUltimiDieciOrdini();
    }

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
     riempiTabellaUltimiDieciOrdini();
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
         if(event.getSource() == GoVisualizzaOrdini){
            patternHome.setVisible(false);
            patternCreaSpedizione.setVisible(false);
            patternVisualizzaOrdini.setVisible(true);
            patternVisualizzaReport.setVisible(false);
        }else if(event.getSource() == GoCreaSpedizione){
            patternHome.setVisible(false);
            patternCreaSpedizione.setVisible(true);
            patternVisualizzaOrdini.setVisible(false);
            patternVisualizzaReport.setVisible(false);
    }   else if(event.getSource() == GoVisualizzaReport){
            patternHome.setVisible(false);
            patternCreaSpedizione.setVisible(false);
            patternVisualizzaOrdini.setVisible(false);
            patternVisualizzaReport.setVisible(true);
        }else if(event.getSource() == goHome){
            patternHome.setVisible(true);
            patternCreaSpedizione.setVisible(false);
            patternVisualizzaOrdini.setVisible(false);
            patternVisualizzaReport.setVisible(false);
        }
    }

    public void riempiTabellaUltimiDieciOrdini() {
        // Ottieni la lista degli ultimi dieci ordini
        List<Ordine> ultimiDieciOrdini = ordiniDao.getUltimiDieciOrdini();

        // Stampa di debug
        System.out.println("Numero di ordini ottenuti: " + ultimiDieciOrdini.size());

        // Crea le colonne della tabella
        IDOrdineHome.setCellValueFactory(new PropertyValueFactory<>("codOrdine"));
        CFhome.setCellValueFactory(new PropertyValueFactory<>("cf"));
        TIPOHome.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
        DATAHome.setCellValueFactory(new PropertyValueFactory<>("dataordinato"));
        IDMERHome.setCellValueFactory(new PropertyValueFactory<>("idMerce")); // Utilizza un nome diverso da IDMERHome
        QUANTHome.setCellValueFactory(new PropertyValueFactory<>("quantità"));
        DESCHome.setCellValueFactory(new PropertyValueFactory<>("descrizione"));

        // Aggiungi gli ordini alla tabella
        TabellaOrdiniHome.getItems().setAll(ultimiDieciOrdini);
    }





}
