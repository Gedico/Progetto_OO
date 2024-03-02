package com.example.progetto_oo.Controllers;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class Controller {
    public Button Login;
    public PasswordField Password;
    public TextField Username;
    public Text avvisoErrore;

    public void LoginOnAction(ActionEvent event) throws IOException {
        if(Username.getText().isBlank()){
            avvisoErrore.setText("inserire l'Username");
        }else if(Password.getText().isBlank()) avvisoErrore.setText("inserire la Password");

    }
}

