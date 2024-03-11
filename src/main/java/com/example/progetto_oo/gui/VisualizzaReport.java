package com.example.progetto_oo.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VisualizzaReport extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        creaFinestraVisualizzaReport();

    }

    public void creaFinestraVisualizzaReport() throws IOException {
        // Creazione della finestra VisualizzaReport
        Stage homeStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("/FXML/VisualizzaReport.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 630);
        homeStage.setTitle("Visualizza Report");
        homeStage.setScene(scene);
        homeStage.show();
    }
}