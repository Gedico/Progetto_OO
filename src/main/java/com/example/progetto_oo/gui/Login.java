package com.example.progetto_oo.gui;

import com.example.progetto_oo.Alerts.Alerts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Login extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("/FXML/Login.fxml"));
            BorderPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            primaryStage.initStyle(StageStyle.UNDECORATED);//creo la finestra senza bordi e bottoni di chiusura e riduzione

            // Aggiungo un listener per gestire l'evento di trascinamento della finestra
            root.setOnMousePressed((MouseEvent event) -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            primaryStage.setScene(scene);
            primaryStage.show();} catch (IOException e) {
            e.printStackTrace();
            Alerts.mostraMessaggioErrore("Errore", "Errore", "Errore durante il caricamento della finestra di login");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

