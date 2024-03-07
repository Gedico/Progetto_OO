package com.example.progetto_oo.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Home extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {

        creaFinestraHome();
    }

    public void creaFinestraHome() throws IOException {
        // Creazione della finestra "Home"
        Stage homeStage = new Stage();
       homeStage.initStyle(StageStyle.UNDECORATED);//creo la finestra senza bordi e bottoni di chiusura e riduzione
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("/FXML/Homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 630);
        homeStage.setTitle("Home");
        homeStage.setScene(scene);
        homeStage.show();
    }
}