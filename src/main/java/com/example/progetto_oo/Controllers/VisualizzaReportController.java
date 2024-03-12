package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.Database.Connessione;
import com.example.progetto_oo.Database.Ordine;
import com.example.progetto_oo.Database.OrdiniDao;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class VisualizzaReportController {

    public MenuButton MenuReport;
    public MenuItem GennaioReport;
    public MenuItem FebbraioReport;
    public MenuItem MarzoReport;
    public MenuItem AprileReport;
    public MenuItem MaggioReport;
    public MenuItem GiugnoReport;
    public MenuItem LuglioReport;
    public MenuItem AgostoReport;
    public MenuItem SettembreReport;
    public MenuItem OttobreReport;
    public MenuItem NovembreReport;
    public MenuItem DicembreReport;
    public TableView TabellaOrdineMenoProdotti;
    public TableColumn id_ordineMeno;
    public TableColumn cfMeno;
    public TableColumn dataMeno;
    public TableColumn TipoMeno;
    public TableColumn ID_MerceMeno;
    public TableColumn QuantitàMeno;
    public TableColumn DescrizioneMeno;
    public Text MediaOrdiniMensili;
    public TableView TabellaOrdinepiuProdotti;
    public TableColumn id_ordinePiù;
    public TableColumn DataPiù;
    public TableColumn cfPiù;
    public TableColumn tipologiaPiù;
    public TableColumn ID_MercePiù;
    public TableColumn QuantitàPiù;
    public TableColumn DescrizionePiù;
    Connessione db = new Connessione();
    OrdiniDao ordiniDao = new OrdiniDao(db);


    public void riempipaginareport(int mese) throws SQLException {
        int ordini = ordiniDao.getNumeroOrdiniPerMese(mese);
        stampaordinemenoprodotti(mese);
        stampaordinepiùprodotti(mese);
        System.out.println("Numero di ordini: " + ordini);
        int giorniMese = giorniMese(mese);
        System.out.println("Giorni nel mese: " + giorniMese);

        double media = (double) ordini / giorniMese;
        media = Math.round(media * 100.0) / 100.0; // Arrotonda a due decimali

        System.out.println("Media ordini giornaliera: " + media);
        MediaOrdiniMensili.setText(String.valueOf(media));
    }


    public void stampaordinemenoprodotti(int mese) {
        // Ottieni la lista degli ultimi dieci ordini
        List<Ordine> ordinemeno = ordiniDao.getOrdineConMenoProdottiQuestoMese(mese);

        // Stampa di debug
        System.out.println("Numero di ordini ottenuti: " + ordinemeno.size());

        // Crea le colonne della tabella
        id_ordineMeno.setCellValueFactory(new PropertyValueFactory<>("codOrdine"));
        cfMeno.setCellValueFactory(new PropertyValueFactory<>("cf"));
        dataMeno.setCellValueFactory(new PropertyValueFactory<>("dataordinato"));
        TipoMeno.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
        ID_MerceMeno.setCellValueFactory(new PropertyValueFactory<>("idMerce"));
        QuantitàMeno.setCellValueFactory(new PropertyValueFactory<>("quantità"));
        DescrizioneMeno.setCellValueFactory(new PropertyValueFactory<>("descrizione"));

        // Aggiungi gli ordini alla tabella
        TabellaOrdineMenoProdotti.getItems().setAll(ordinemeno);

        // Stampa di debug
        System.out.println("Numero di righe nella tabella: " + TabellaOrdineMenoProdotti.getItems().size());
    }
    public void stampaordinepiùprodotti(int mese) {
        // Ottieni la lista degli ultimi dieci ordini
        List<Ordine> ordinemeno = ordiniDao.getOrdineConPiùProdottiQuestoMese(mese);

        // Stampa di debug
        System.out.println("Numero di ordini ottenuti: " + ordinemeno.size());

        // Crea le colonne della tabella
        id_ordinePiù.setCellValueFactory(new PropertyValueFactory<>("codOrdine"));
        cfPiù.setCellValueFactory(new PropertyValueFactory<>("cf"));
        DataPiù.setCellValueFactory(new PropertyValueFactory<>("dataordinato"));
        tipologiaPiù.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
        ID_MercePiù.setCellValueFactory(new PropertyValueFactory<>("idMerce"));
        QuantitàPiù.setCellValueFactory(new PropertyValueFactory<>("quantità"));
        DescrizionePiù.setCellValueFactory(new PropertyValueFactory<>("descrizione"));

        // Aggiungi gli ordini alla tabella
        TabellaOrdinepiuProdotti.getItems().setAll(ordinemeno);

        // Stampa di debug
        System.out.println("Numero di righe nella tabella: " + TabellaOrdinepiuProdotti.getItems().size());
    }
    public int giorniMese(int mese){
        if(mese==1 || mese==3 || mese==5 || mese==7 || mese==8 || mese==10 || mese==12){
            return 31;
        }else if(mese==4 || mese==6 || mese==9 || mese==11){
            return 30;
        }else{
            return 28;
        }
    }

    public void GennaioReportOnAction(ActionEvent event) throws
            SQLException {
        riempipaginareport(1);
    }
    public void FebbraioReportOnAction(ActionEvent event) throws SQLException {
        riempipaginareport(2);
    }
    public void MarzoReportOnAction(ActionEvent event) throws SQLException {
        riempipaginareport(3);
    }
    public void AprileReportOnAction(ActionEvent event) throws SQLException {
        riempipaginareport(4);
    }
    public void MaggioReportOnAction(ActionEvent event) throws SQLException {
        riempipaginareport(5);
    }
    public void GiugnoReportOnAction(ActionEvent event) throws SQLException {
        riempipaginareport(6);
    }
    public void LuglioReportOnAction(ActionEvent event) throws SQLException {
        riempipaginareport(7);
    }
    public void AgostoReportOnAction(ActionEvent event) throws SQLException {
        riempipaginareport(8);
    }
    public void SettembreReportOnAction(ActionEvent event) throws SQLException {
        riempipaginareport(9);
    }
    public void OttobreReportOnAction(ActionEvent event) throws SQLException {
        riempipaginareport(10);
    }
    public void NovembreReportOnAction(ActionEvent event) throws SQLException {
        riempipaginareport(11);
    }
    public void DicembreReportOnAction(ActionEvent event) throws SQLException {
        riempipaginareport(12);
    }
}
