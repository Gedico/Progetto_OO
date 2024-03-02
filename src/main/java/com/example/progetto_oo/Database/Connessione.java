package com.example.progetto_oo.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Connessione {

    // Metodo per ottenere una connessione al database
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "inazumajapan");

        } catch (ClassNotFoundException e) {
            showErrorDialog("Errore", "Driver JDBC non trovato");
            throw new SQLException("Driver JDBC non trovato", e);

        } catch (SQLException e) {
            showErrorDialog("Errore", "Connessione al database non riuscita");
            throw e;
        }
    }

    // Metodo per lanciare un pop up di errore defineno titolo e corpo dell'errore
    private static void showErrorDialog(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Metodo per chiudere la connessione
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            showErrorDialog("Errore", "Errore durante la chiusura della connessione");
        }
    }
}

