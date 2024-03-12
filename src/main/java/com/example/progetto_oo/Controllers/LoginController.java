package com.example.progetto_oo.Controllers;

import com.example.progetto_oo.Database.Connessione;
import com.example.progetto_oo.Database.OperatoreDao;
import com.example.progetto_oo.gui.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ImageView Logo;
    public TextField Username;
    public PasswordField Password;
    public Button Login;
    public Text avvisoErrore;
    public Button Chiudi;
    Connessione connessione = new Connessione();
    OperatoreDao operatoreDao = new OperatoreDao(connessione);
    Dashboard home = new Dashboard();
    ProfiloController profiloController = new ProfiloController();


    public void LoginOnAction(ActionEvent event) throws IOException {
        if (Username.getText().isBlank()||Password.getText().isBlank()) {
            avvisoErrore.setText("Riempire i campi vuoti");
        }
        else if (operatoreDao.verificaLogin(Username.getText(), Password.getText())) {
            Stage stage = (Stage) Login.getScene().getWindow();
            stage.close();
            home.creaFinestraHome(new Stage());
            profiloController.setOperatore(Username.getText());
        }
        else {
            avvisoErrore.setText("Login fallito");
        }
    }

    public void ChiudiFinestraLogin(ActionEvent event) {
        Stage stage = (Stage) Chiudi.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            try {
                // Imposta il percorso dell'immagine
                String imagePath = "/Immagini/Logo.png";

                // Utilizza ClassLoader per ottenere l'input stream
                InputStream inputStream = getClass().getResourceAsStream(imagePath);

                // Verifica se l'input stream è nullo
                if (inputStream != null) {
                    // Crea un oggetto Image
                    Image image = new Image(inputStream);

                    // Imposta l'immagine nell'ImageView
                    Logo.setImage(image);
                } else {
                    System.out.println("Input stream dell'immagine non trovato.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


    }
}

