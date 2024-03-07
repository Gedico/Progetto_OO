package com.example.progetto_oo.Alerts;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts {

    public static void mostraMessaggioInformazione(String titolo, String header, String messaggio) {
        mostraAlert(AlertType.INFORMATION, titolo, header, messaggio);
    }

    public static void mostraMessaggioErrore(String titolo, String header, String messaggio) {
        mostraAlert(AlertType.ERROR, titolo, header, messaggio);
    }

    public static void mostraMessaggioAvvertimento(String titolo, String header, String messaggio) {
        mostraAlert(AlertType.WARNING, titolo, header, messaggio);
    }

    private static void mostraAlert(AlertType tipo, String titolo, String header, String messaggio) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titolo);
        alert.setHeaderText(header);
        alert.setContentText(messaggio);

        alert.showAndWait();
    }

    public boolean mostraPopupConferma(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);

        // Aggiungi i pulsanti di conferma e annulla
        ButtonType confermaButton = new ButtonType("Conferma");
        ButtonType annullaButton = new ButtonType("Annulla");
        alert.getButtonTypes().setAll(confermaButton, annullaButton);

        // Mostra il popup e ottieni la risposta dell'utente
        Optional<ButtonType> result = alert.showAndWait();

        // Restituisci true se l'utente ha confermato, altrimenti false
        return result.isPresent() && result.get() == confermaButton;
    }
}

