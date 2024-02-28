package com.example.progetto_oo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdineDAO {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "inazumajapan";

    public void stampaDatiTabellaOrdine() {
        String query = "SELECT ID_ordine, CF, dataOrdinato, elaborato, Codice_Indirizzo, tipologia FROM ordine";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Itera sui risultati della query
            while (resultSet.next()) {
                int idOrdine = resultSet.getInt("ID_ordine");
                String cf = resultSet.getString("CF");
                String dataOrdinato = resultSet.getString("dataOrdinato");
                boolean elaborato = resultSet.getBoolean("elaborato");
                int codiceIndirizzo = resultSet.getInt("Codice_Indirizzo");
                String tipologia = resultSet.getString("tipologia");

                System.out.println("ID_ordine: " + idOrdine +
                        ", CF: " + cf +
                        ", Data Ordinato: " + dataOrdinato +
                        ", Elaborato: " + elaborato +
                        ", Codice Indirizzo: " + codiceIndirizzo +
                        ", Tipologia: " + tipologia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

