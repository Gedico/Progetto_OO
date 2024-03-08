package com.example.progetto_oo.Database;

import com.example.progetto_oo.Alerts.Alerts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdiniDao {
    private Connection connection;
    public OrdiniDao(Connessione connessione) {
        try {
            this.connection = connessione.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//Costruttore della classe OrdiniDao
    public int ordiniDaGestire() {
        int count = 0;

        if (connection == null) {
            Alerts.mostraMessaggioErrore("Errore", "Errore", "La connessione al database non è stata stabilita correttamente.");
            return count; // Ritorna 0 se la connessione non è stata stabilita
        }

        String query = "SELECT COUNT(*) AS count FROM ordine WHERE elaborato = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, false);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt("count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }//Metodo per contare gli ordini da gestire
    public int ordiniGestiti() {
        int count = 0;

        if (connection == null) {
            Alerts.mostraMessaggioErrore("Errore", "Errore", "La connessione al database non è stata stabilita correttamente.");
            return count; // Ritorna 0 se la connessione non è stata stabilita
        }

        String query = "SELECT COUNT(*) AS count FROM ordine WHERE elaborato = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, true);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt("count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }//Metodo per contare gli ordini gestiti
    public List<Ordine> getUltimiDieciOrdini() {
        List<Ordine> ordini = new ArrayList<>();

        if (connection == null) {
            Alerts.mostraMessaggioErrore("Errore", "Errore", "La connessione al database non è stata stabilita correttamente.");
            return ordini;
        }

        String query = "SELECT id_ordine, cf, tipologia FROM ordine ORDER BY dataordinato DESC LIMIT 10";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("ciao");
                    Ordine ordine = new Ordine(
                            resultSet.getString("id_ordine"),
                            resultSet.getString("cf"),
                            resultSet.getString("tipologia")
                    );
                    ordini.add(ordine);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordini;
    }

}