package com.example.progetto_oo.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperatoreDao {
    private Connection connection;

    // Costruttore per la connessione al database
    public OperatoreDao(Connessione connessione) {
        try {
            this.connection = connessione.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); // Puoi gestire l'eccezione in base alle tue esigenze
        }
    }

    // Metodo per la verifica del login
    public boolean verificaLogin(String username, String password) {
        if (connection == null) {
            // La connessione non è stata stabilita correttamente
            System.err.println("La connessione al database non è stata stabilita correttamente.");
            return false;
        }

        String query = "SELECT * FROM operatore WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Se il resultSet ha almeno una riga, significa che le credenziali sono corrette
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Puoi gestire l'eccezione in base alle tue esigenze
            return false;
        }
    }
}

