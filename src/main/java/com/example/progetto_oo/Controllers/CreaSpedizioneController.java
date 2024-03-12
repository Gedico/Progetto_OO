package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.Alerts.Alerts;
import com.example.progetto_oo.Database.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CreaSpedizioneController {

    public TableView TabellaOrdiniSpedizione;
    public TableColumn IDOrdineSpedizione;
    public TableColumn CFOrdineSpedizione;
    public TableColumn DataOrdineSpedizione;
    public TableColumn TipoOrdineSpedizione;
    public TableColumn IDMerOrdineSpedizione;
    public TableColumn QuantitaOrdineSpedizione;
    public TableColumn DescOrdineSpedizione;
    public TableColumn TargaVeicolo;
    public TableColumn MaxTrasportabileVeicolo;
    public TableColumn DescrizioneVeicolo;
    public TableColumn MatricolaCorriereVeicolo;
    public Button CreaSpedizione;
    public TextField ID_OrdineField;
    public TextField TargaField;
    public TextField DataSpedizione;
    public Button CercaVeicoliSpedizioe;
    public Text erroreSceltaVeicolo;
    public TableColumn ID_MagazzinoVeicoli;
    public TableView TabellaVeicoliSpedizione;
    public Button resetSpedizione;
    public DatePicker SelezionaDataSpedizione;
    Connessione connessione = new Connessione();
    OrdiniDao ordiniDao = new OrdiniDao(connessione);
    veicoliDao veicolidao = new veicoliDao(connessione);

    public void StampaOrdiniDaGestireOnAction(ActionEvent event) {
        riempiTabellaOrdiniDaGestire();
    }
    public void CreaSpedizoneOnAction(ActionEvent event) {
        erroreSceltaVeicolo.setText("");
        String dataInseritaString = SelezionaDataSpedizione.getValue().toString();
        try {
            //converto la stringa in data
            LocalDate dataInserita = LocalDate.parse(dataInseritaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

             if (dataInserita.isBefore(LocalDate.now()))
            {Alerts.mostraMessaggioAvvertimento("Errore", "Errore", "La data inserita Deve essere maggiore o uguale a quella odierna");
            } else if (SelezionaDataSpedizione.getValue()==null||ID_OrdineField.getText().isEmpty()||TargaField.getText().isEmpty()) {
                 erroreSceltaVeicolo.setText("Riempire i campi di ID_Ordine, Targa e Data");

             } else if (!veicolidao.verificaEsistenzaVeicolo(TargaField.getText())) {
                    Alerts.mostraMessaggioAvvertimento("Errore", "Errore", "Il veicolo non esiste oppure è gia stato gestito "+TargaField.getText());

             } else if (!ordiniDao.verificaEsistenzaOrdine(Integer.parseInt(ID_OrdineField.getText()))){
                 Alerts.mostraMessaggioAvvertimento("Errore", "Errore", "L'ordine non esiste"+ID_OrdineField.getText());

             } else if (!veicolidao.verificaDisponibilitàVeicolo(TargaField.getText(),SelezionaDataSpedizione.getValue().toString())) {
                    Alerts.mostraMessaggioAvvertimento("Errore", "Errore", "Il veicolo non è disponibile in quella data");

             }else if( ordiniDao.creaSpedizione(Integer.parseInt(ID_OrdineField.getText()),TargaField.getText(),SelezionaDataSpedizione.getValue().toString())){
                 Alerts.mostraMessaggioInformazione("Informazione", "Spedizione Creata", "La spedizione è stata creata con successo");
            }else Alerts.mostraMessaggioErrore("Errore", "Errore", "Errore nella creazione della spedizione");
        }
        catch (java.time.format.DateTimeParseException e)
        {
            Alerts.mostraMessaggioAvvertimento("Errore", "Errore", "La data inserita Deve essere del formato anno-mm-gg");
        }

    }
    public void CercaVeicoliOnAction(ActionEvent event) {
        erroreSceltaVeicolo.setText("");
        String dataInseritaString = SelezionaDataSpedizione.getValue().toString();
        try {
            //converto la stringa in data
            LocalDate dataInserita = LocalDate.parse(dataInseritaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (SelezionaDataSpedizione.getValue().toString().isEmpty()) {
                erroreSceltaVeicolo.setText("Inserire la data Data");
            }
            else if (dataInserita.isBefore(LocalDate.now()))
            {Alerts.mostraMessaggioAvvertimento("Errore", "Errore", "La data inserita Deve essere maggiore o uguale a quella odierna");
            } else {
                riempiTabellaVeicoli();}
        }
        catch (java.time.format.DateTimeParseException e)
        {
            Alerts.mostraMessaggioAvvertimento("Errore", "Errore", "La data inserita Deve essere del formato anno-mm-gg");
        }
    }
    public void riempiTabellaOrdiniDaGestire() {
        // Ottieni la lista degli ultimi dieci ordini
        List<Ordine> ultimiDieciOrdini = ordiniDao.OrdiniDaGestire();

        // Stampa di debug
        System.out.println("Numero di ordini ottenuti: " + ultimiDieciOrdini.size());

        // Crea le colonne della tabella
        IDOrdineSpedizione.setCellValueFactory(new PropertyValueFactory<>("codOrdine"));
        CFOrdineSpedizione.setCellValueFactory(new PropertyValueFactory<>("cf"));
        TipoOrdineSpedizione.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
        DataOrdineSpedizione.setCellValueFactory(new PropertyValueFactory<>("dataordinato"));
        IDMerOrdineSpedizione.setCellValueFactory(new PropertyValueFactory<>("idMerce")); // Utilizza un nome diverso da IDMERHome
        QuantitaOrdineSpedizione.setCellValueFactory(new PropertyValueFactory<>("quantità"));
        DescOrdineSpedizione.setCellValueFactory(new PropertyValueFactory<>("descrizione"));

        // Aggiungi gli ordini alla tabella
        TabellaOrdiniSpedizione.getItems().setAll(ultimiDieciOrdini);
    }
    public void initialize() {
            riempiTabellaOrdiniDaGestire();

            // Aggiungi un listener di evento per il clic sulla riga nella tabella
            TabellaOrdiniSpedizione.setRowFactory(tv -> {
                TableRow<Ordine> row = new TableRow<>();
                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 1 && (!row.isEmpty())) {
                            Ordine selectedOrder = row.getItem();

                            // Ora puoi accedere ai dati dell'ordine e fare qualcosa con essi
                            riempiCampiDatiOrdine(selectedOrder);
                        }
                    }
                });
                return row;
            });

        TabellaVeicoliSpedizione.setRowFactory(tv -> {
            TableRow<Veicolo> row = new TableRow<>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                        Veicolo selectedVeicolo = row.getItem();

                        // Ora puoi accedere ai dati dell'ordine e fare qualcosa con essi
                        riempiCampiVeicoli(selectedVeicolo);
                    }
                }
            });
            return row;
        });
        }
    private void riempiCampiDatiOrdine(Ordine selectedOrder) {
            ID_OrdineField.setText(Integer.toString(selectedOrder.getCodOrdine()));
        }
    private void riempiCampiVeicoli(Veicolo selectedVeicolo) {
        TargaField.setText(selectedVeicolo.getTarga());
    }
    public void riempiTabellaVeicoli() {
        // Ottieni la lista degli ultimi dieci ordini
        List<Veicolo> veicoli = veicolidao.getVeicoliPerOrdine( SelezionaDataSpedizione.getValue().toString());

        if (veicoli.isEmpty()) {
            // Nessun veicolo disponibile per l'ordine e la data specificati
            Alerts.mostraMessaggioAvvertimento("Avviso", "Nessun veicolo disponibile",
                            " Nessun veicolo disponibile nella data " + SelezionaDataSpedizione.getValue().toString());
        } else {

            // Stampa di debug
            System.out.println("Numero di veicoli ottenuti: " + veicoli.size());

            // Crea le colonne della tabella
            TargaVeicolo.setCellValueFactory(new PropertyValueFactory<>("targa"));
            DescrizioneVeicolo.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
            MaxTrasportabileVeicolo.setCellValueFactory(new PropertyValueFactory<>("maxTrasportabile"));
            MatricolaCorriereVeicolo.setCellValueFactory(new PropertyValueFactory<>("matricolaCorriere"));
            ID_MagazzinoVeicoli.setCellValueFactory(new PropertyValueFactory<>("id_magazzino"));

            // Aggiungi gli ordini alla tabella
           TabellaVeicoliSpedizione.getItems().setAll(veicoli);

        }
    }
    public void resetSpedizioneOnAction(ActionEvent event) {
        ID_OrdineField.setText("");
        TargaField.setText("");
        SelezionaDataSpedizione.setValue(null);
        erroreSceltaVeicolo.setText("");
        TabellaOrdiniSpedizione.getItems().clear();
        TabellaVeicoliSpedizione.getItems().clear();
    }
}




