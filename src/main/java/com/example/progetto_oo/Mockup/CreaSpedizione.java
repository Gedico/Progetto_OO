package com.example.progetto_oo.Mockup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreaSpedizione extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        creaFinestraCreaSpedizione();

    }

    public void creaFinestraCreaSpedizione() throws IOException {
        // Creazione della finestra CreaSpedizione
        Stage homeStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.progetto_oo.Mockup.Home.class.getResource("/FXML/CreaSpedizione.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 630);
        homeStage.setTitle("Creazione Spedizione");
        homeStage.setScene(scene);
        homeStage.show();
    }
}