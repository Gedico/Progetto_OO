package com.example.progetto_oo.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VisualizzaOrdini extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        creaFinestraVisualizzaOrdini();

    }

    public void creaFinestraVisualizzaOrdini() throws IOException {
        // Creazione della finestra "Home"
        Stage VisOrdiniStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("/FXML/VisualizzaOrdini.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 630);
        VisOrdiniStage.setTitle("Visualizza Orini");
        VisOrdiniStage.setScene(scene);
        VisOrdiniStage.show();
    }
}