package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.gui.Home;
import com.example.progetto_oo.gui.Login;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfiloController implements Initializable {
    public AnchorPane patternProfilo;
    public Text usernameInProfilo;
    public Text nomeInProfilo;
    public Text cognomeInProfilo;
    public Button cambiaPassword;;
    public String operatore;

    public void cambiaPasswordOnAction(ActionEvent event) {
        System.out.println(operatore);
    }

    public void setOperatore(String operatore) {
        this.operatore = operatore;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Puoi inizializzare eventuali componenti della GUI qui
    }
}

