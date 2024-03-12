package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.Alerts.Alerts;
import com.example.progetto_oo.Database.ClienteDao;
import com.example.progetto_oo.Database.Connessione;
import com.example.progetto_oo.Database.Ordine;
import com.example.progetto_oo.Database.OrdiniDao;
import com.example.progetto_oo.gui.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.System.out;

public class
VisualizzaOrdiniController {

    public MenuItem sceltoUtente;
    public MenuItem sceltoPeriodo;
    public AnchorPane CercaPerUtente;
    public AnchorPane CercaPerData;
    public AnchorPane patternVisualizzaOrdinal;
    public TableView tabellaRicercaOrdini;
    public TableColumn idOrdineVisOrdine;
    public TableColumn cfVisOrdine;
    public TableColumn dataVisOrdine;
    public TableColumn tipoVisOrdin;
    public TableColumn idMercVisOrdin;
    public TableColumn quantitaVisOrdin;
    public TableColumn descVisOrdin;
    public Button cercaPerData;
    public Button cercaPerUtente;
    public TextField utenteVisualizza;
    public Text erroreRicercaPerData;
    public Text erroreRicercaPerUtente;
    public DatePicker SelezionaDataInizio;
    public DatePicker SelezionaDataFine;
    Connessione connessione = new Connessione();
    OrdiniDao ordiniDao = new OrdiniDao(connessione);
    ClienteDao clienteDao = new ClienteDao(connessione);

    public void sceltaVisualizza(ActionEvent event) {
        if(event.getSource() == sceltoUtente){
            CercaPerData.setVisible(false);
            CercaPerUtente.setVisible(true);
        }else if(event.getSource() == sceltoPeriodo){
            CercaPerUtente.setVisible(false);
            CercaPerData.setVisible(true);
        }
    }
    public void riempiTabellaPerUtente() {
        // Ottieni la lista degli ultimi dieci ordini
        List<Ordine> ultimiDieciOrdini = ordiniDao.cercaInOrdiniPerCliente(utenteVisualizza.getText());

        // Stampa di debug
        out.println("Numero di ordini ottenuti: " + ultimiDieciOrdini.size());

        // Crea le colonne della tabella
       idOrdineVisOrdine.setCellValueFactory(new PropertyValueFactory<>("codOrdine"));
        cfVisOrdine.setCellValueFactory(new PropertyValueFactory<>("cf"));
        dataVisOrdine.setCellValueFactory(new PropertyValueFactory<>("dataordinato"));
        tipoVisOrdin.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
        idMercVisOrdin.setCellValueFactory(new PropertyValueFactory<>("idMerce"));
        quantitaVisOrdin.setCellValueFactory(new PropertyValueFactory<>("quantità"));
        descVisOrdin.setCellValueFactory(new PropertyValueFactory<>("descrizione"));

        // Aggiungi gli ordini alla tabella
        tabellaRicercaOrdini.getItems().setAll(ultimiDieciOrdini);

        // Stampa di debug
        out.println("Numero di righe nella tabella: " + tabellaRicercaOrdini.getItems().size());
    }
    public void riempiTabellaPerData() {
        // Ottieni la lista degli ultimi dieci ordini
        List<Ordine> ultimiDieciOrdini = ordiniDao.cercaInOrdiniPerDate(String.valueOf(SelezionaDataInizio.getValue()), String.valueOf(SelezionaDataFine.getValue()));

        // Stampa di debug
        out.println("Numero di ordini ottenuti: " + ultimiDieciOrdini.size());

        // Crea le colonne della tabella
        idOrdineVisOrdine.setCellValueFactory(new PropertyValueFactory<>("codOrdine"));
        cfVisOrdine.setCellValueFactory(new PropertyValueFactory<>("cf"));
        tipoVisOrdin.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
        dataVisOrdine.setCellValueFactory(new PropertyValueFactory<>("dataordinato"));
        idMercVisOrdin.setCellValueFactory(new PropertyValueFactory<>("idMerce"));
        quantitaVisOrdin.setCellValueFactory(new PropertyValueFactory<>("quantità"));
        descVisOrdin.setCellValueFactory(new PropertyValueFactory<>("descrizione"));

        // Aggiungi gli ordini alla tabella
        tabellaRicercaOrdini.getItems().setAll(ultimiDieciOrdini);

        // Stampa di debug
        out.println("Numero di righe nella tabella: " + tabellaRicercaOrdini.getItems().size());
    }
    public void cercaPerDataOnAction(ActionEvent event) {
        erroreRicercaPerData.setText("");

        LocalDate dataOdierna = LocalDate.now();

        try {
            String dataInizioText = String.valueOf(SelezionaDataInizio.getValue());
            System.out.println(dataInizioText);
            String dataFineText = String.valueOf(SelezionaDataFine.getValue());
            System.out.println(dataFineText);

            if (SelezionaDataInizio.getValue() == null || SelezionaDataFine.getValue() == null) {
                erroreRicercaPerData.setText("Riempire tutti i campi");
            } else {

                LocalDate inizio = LocalDate.parse(dataInizioText, DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate fine = LocalDate.parse(dataFineText, DateTimeFormatter.ISO_LOCAL_DATE);

                if (inizio.isAfter(fine)) {
                    Alerts.mostraMessaggioAvvertimento("Errore", "Errore", "La data di inizio non può essere successiva alla data di fine");
                } else if (fine.isAfter(dataOdierna)) {
                    Alerts.mostraMessaggioAvvertimento("Errore", "Errore", "La data di fine non può essere successiva alla data odierna");
                } else if (inizio.isEqual(fine)) {
                    Alerts.mostraMessaggioAvvertimento("Errore", "Errore", "Le date di inizio e fine non possono essere uguali");
                } else {
                    // Le date sono valide, puoi procedere con la ricerca
                    riempiTabellaPerData();
                }}
            } catch(DateTimeParseException e){
                Alerts.mostraMessaggioAvvertimento("Errore", "Errore", "Formato data non valido.");
            }
        }
    public void CercaPerUtenteOnAction(ActionEvent event) {
        erroreRicercaPerUtente.setText("");
        if (utenteVisualizza.getText().isEmpty()) {
            erroreRicercaPerUtente.setText("Inserire un utente");
        } else if (!clienteDao.verificaEsistenzaCF(utenteVisualizza.getText())) {
            Alerts.mostraMessaggioAvvertimento("Errore", "Errore", "Utente inesistente nel database");
        } else {riempiTabellaPerUtente();

        }
    }
    public void resetUtenteOnAction(ActionEvent event) {
        erroreRicercaPerUtente.setText("");
        utenteVisualizza.clear();
        tabellaRicercaOrdini.getItems().clear();
    }
    public void resetDataOnAction(ActionEvent event) {
        erroreRicercaPerData.setText("");
        SelezionaDataInizio.setValue(null);
        SelezionaDataFine.setValue(null);
        tabellaRicercaOrdini.getItems().clear();
    }
}
