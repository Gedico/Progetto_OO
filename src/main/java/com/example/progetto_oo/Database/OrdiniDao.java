package com.example.progetto_oo.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdiniDao {
    private Connection connection;

    // Costruttore per la connessione al database
    public OrdiniDao(Connessione connessione) {
        try {
            this.connection = connessione.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int ordiniDaGestire() {
        int count = 0;

        if (connection == null) {
            System.err.println("La connessione al database non è stata stabilita correttamente.");
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
    }

}