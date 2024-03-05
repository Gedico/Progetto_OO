package com.example.progetto_oo.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Login extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;  // Inizializza la variabile stage
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }


}

