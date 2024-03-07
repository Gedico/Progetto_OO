package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.Database.Connessione;
import com.example.progetto_oo.Database.OperatoreDao;
import com.example.progetto_oo.Database.OrdiniDao;
import com.example.progetto_oo.gui.Login;
import com.example.progetto_oo.gui.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    String operatore;
    public TextField Username;
    public PasswordField Password;
    public Button Login;
    public Text avvisoErrore;
    public Button Chiudi;
    Connessione connessione = new Connessione();
    OperatoreDao operatoreDao = new OperatoreDao(connessione);
    Home home = new Home();

    public void LoginOnAction(ActionEvent event) throws IOException {
        this.operatore = Username.getText();
        if (Username.getText().isBlank()||Password.getText().isBlank()) {
            avvisoErrore.setText("Riempire i campi vuoti");
        } else if (operatoreDao.verificaLogin(Username.getText(), Password.getText())) {
            Stage stage = (Stage) Login.getScene().getWindow();
            stage.close();
            home.creaFinestraHome();
        } else {
            avvisoErrore.setText("Login fallito");
        }
    }

    public void ChiudiFinestraLogin(ActionEvent event) {
        Stage stage = (Stage) Chiudi.getScene().getWindow();
        stage.close();
    }
    public String getOperatore(){
        return this.operatore;
    }
}

