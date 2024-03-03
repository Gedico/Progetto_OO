package com.example.progetto_oo.Mockup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Profilo extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        creaFinestraProfilo();

    }

    public void creaFinestraProfilo() throws IOException {
        // Creazione della finestra "Home"
        Stage ProfiloStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("/FXML/Profilo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 630);
        ProfiloStage.setTitle("Profilo utente");
        ProfiloStage.setScene(scene);
        ProfiloStage.show();
    }
}
