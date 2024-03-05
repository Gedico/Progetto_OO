package com.example.progetto_oo.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {

        creaFinestraHome();
    }

    public void creaFinestraHome() throws IOException {
        // Creazione della finestra "Home"
        Stage homeStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("/FXML/Homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 630);
        homeStage.setTitle("Home");
        homeStage.setScene(scene);
        homeStage.show();
    }
}