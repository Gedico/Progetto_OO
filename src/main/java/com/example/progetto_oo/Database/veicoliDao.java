package com.example.progetto_oo.Database;

import com.example.progetto_oo.Alerts.Alerts;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class veicoliDao {

    private Connection connection;
    public veicoliDao(Connessione connessione) {
        try {
            this.connection = connessione.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

}
    public List<Veicolo> getVeicoliPerOrdine(String data) {
        List<Veicolo> veicoli = new ArrayList<>();

        if (connection == null) {
            Alerts.mostraMessaggioErrore("Errore", "Errore", "La connessione al database non è stata stabilita correttamente.");
            return veicoli; // Ritorna 0 se la connessione non è stata stabilita
        }

        String query = "SELECT targa,max_trasportabile,descrizione,matricola,id_magazzino FROM (veicoli v natural join reperibilità r) where disponibilità = true and data = TO_DATE(?, 'YYYY-MM-DD');";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Parametri di input
            preparedStatement.setString(1, data);

            // Esegui la query
            ResultSet resultSet = preparedStatement.executeQuery();



            while (resultSet.next()) {
                Veicolo veicolo = new Veicolo(
                        resultSet.getString("targa"),
                        resultSet.getInt("max_trasportabile"),
                        resultSet.getString("descrizione"),
                        resultSet.getString("matricola"),
                        resultSet.getInt("id_magazzino")
                );
                veicoli.add(veicolo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veicoli;
    }
    public boolean verificaEsistenzaVeicolo(String targa) {
        boolean VeicoloEsistente = false;

        if (connection == null) {
            Alerts.mostraMessaggioErrore("Errore", "Errore", "La connessione al database non è stata stabilita correttamente.");
            return VeicoloEsistente;
        }

        String query = "SELECT COUNT(*) AS count FROM veicoli WHERE targa = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, targa);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    VeicoloEsistente = count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return VeicoloEsistente;
    }
    public boolean verificaDisponibilitàVeicolo(String targa , String data) {
        boolean veicoloDisponibile = false;

        if (connection == null) {
            Alerts.mostraMessaggioErrore("Errore", "Errore", "La connessione al database non è stata stabilita correttamente.");
            return veicoloDisponibile;
        }

        String query = "SELECT COUNT(*) AS count FROM veicoli natural join reperibilità where targa = ? and data = TO_DATE(?, 'YYYY-MM-DD') and disponibilità = true;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, targa);
            preparedStatement.setString(2, data);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    veicoloDisponibile = count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veicoloDisponibile;
    }
    // Metodo per verificare la disponibilità di un veicolo
}
