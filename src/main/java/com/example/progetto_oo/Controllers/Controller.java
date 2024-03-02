package com.example.progetto_oo.Controllers;
import com.example.progetto_oo.Database.Connessione;
import com.example.progetto_oo.Database.OperatoreDao;
import com.example.progetto_oo.Login;
import com.example.progetto_oo.Mockup.Home;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    Connessione connessione = new Connessione();
    OperatoreDao operatoreDao = new OperatoreDao(connessione);
    Home home = new Home();
    Login login = new Login();
    public Button Login;
    public PasswordField Password;
    public TextField Username;
    public Text avvisoErrore;

    public void LoginOnAction(ActionEvent event) throws IOException {
        if (Username.getText().isBlank()) {
            avvisoErrore.setText("inserire l'Username");
        } else if (Password.getText().isBlank()) {
            avvisoErrore.setText("inserire la Password");
        } else if (operatoreDao.verificaLogin(Username.getText(),Password.getText())){
            Stage stage = (Stage) Login.getScene().getWindow();
            stage.close();
            home.creaFinestraHome();
        }else avvisoErrore.setText("Login fallito");
        }
    }


