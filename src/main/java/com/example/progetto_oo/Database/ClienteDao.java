package com.example.progetto_oo.Database;

import com.example.progetto_oo.Alerts.Alerts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDao {

    private java.sql.Connection connessione;  // Cambiato il tipo da Connessione a java.sql.Connection

    public ClienteDao(Connessione connessione) {
        try {
            this.connessione = connessione.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); // Puoi gestire l'eccezione in base alle tue esigenze
        }
    }

    public boolean verificaEsistenzaCF(String cf) {
        boolean cfEsistente = false;

        if (connessione == null) {
            Alerts.mostraMessaggioErrore("Errore", "Errore", "La connessione al database non è stata stabilita correttamente.");
            return cfEsistente;
        }

        String query = "SELECT COUNT(*) AS count FROM cliente WHERE cf = ?";

        try (PreparedStatement preparedStatement = connessione.prepareStatement(query)) {
            preparedStatement.setString(1, cf);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    cfEsistente = count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cfEsistente;
    }
}

