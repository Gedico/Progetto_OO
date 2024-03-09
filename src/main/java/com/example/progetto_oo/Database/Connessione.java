package com.example.progetto_oo.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.example.progetto_oo.Alerts.Alerts;

public class Connessione {
    // Metodo per ottenere una connessione al database
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "inazumajapan");

        } catch (ClassNotFoundException e) {
            Alerts.mostraMessaggioErrore("Errore","Errore", "Driver JDBC non trovato");
            throw new SQLException("Driver JDBC non trovato", e);

        } catch (SQLException e) {
            Alerts.mostraMessaggioErrore("Errore","Errore", "Connessione al database non riuscita");
            throw e;
        }
    }

    // Metodo per chiudere la connessione
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            Alerts.mostraMessaggioErrore("Errore","Errore", "Errore durante la chiusura della connessione");
        }
    }

}

