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
    public Button cambiaPassword;
    LoginController loginController = new LoginController();
    String operatore = loginController.getOperatore();

    public void cambiaPasswordOnAction(ActionEvent event) {
        System.out.println(""+operatore);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




}
