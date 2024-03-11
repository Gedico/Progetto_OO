package com.example.progetto_oo.Controllers;

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
    public TextField dataInizio;
    public TextField dataFine;
    public Text erroreRicercaPerData;
    public Text erroreRicercaPerUtente;
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
        System.out.println("Numero di ordini ottenuti: " + ultimiDieciOrdini.size());

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
        System.out.println("Numero di righe nella tabella: " + tabellaRicercaOrdini.getItems().size());
    }

    public void riempiTabellaPerData() {
        // Ottieni la lista degli ultimi dieci ordini
        List<Ordine> ultimiDieciOrdini = ordiniDao.cercaInOrdiniPerDate(dataInizio.getText(), dataFine.getText());

        // Stampa di debug
        System.out.println("Numero di ordini ottenuti: " + ultimiDieciOrdini.size());

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
        System.out.println("Numero di righe nella tabella: " + tabellaRicercaOrdini.getItems().size());
    }

    public void cercaPerDataOnAction(ActionEvent event) {
        erroreRicercaPerData.setText("");
        if (dataInizio.getText().isEmpty() || dataFine.getText().isEmpty()) {
            erroreRicercaPerData.setText("Inserire entrambe le date");
        } else {riempiTabellaPerData();

        }
    }
    public void CercaPerUtenteOnAction(ActionEvent event) {
        erroreRicercaPerUtente.setText("");
        if (utenteVisualizza.getText().isEmpty()) {
            erroreRicercaPerUtente.setText("Inserire un utente");
        } else if (!clienteDao.verificaEsistenzaCF(utenteVisualizza.getText())) {
            erroreRicercaPerUtente.setText("Utente non esistente");
        } else {riempiTabellaPerUtente();

        }
    }
}
