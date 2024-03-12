package com.example.progetto_oo.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Dashboard extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws IOException {
        creaFinestraHome(primaryStage);
    }

    public void creaFinestraHome(Stage homeStage) throws IOException {
        // Creazione della finestra "Home"
        homeStage.initStyle(StageStyle.UNDECORATED); // creo la finestra senza bordi e bottoni di chiusura e riduzione
        FXMLLoader fxmlLoader = new FXMLLoader(Dashboard.class.getResource("/FXML/Homepage.fxml"));
        BorderPane root = fxmlLoader.load();

        // Gestione degli eventi del mouse per rendere la finestra trascinabile
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            homeStage.setX(event.getScreenX() - xOffset);
            homeStage.setY(event.getScreenY() - yOffset);
        });

        Scene scene = new Scene(root, 900, 630);
        homeStage.setTitle("Home");
        homeStage.setScene(scene);
        homeStage.show();
    }

}
