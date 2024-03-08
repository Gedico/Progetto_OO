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

        String query = "SELECT o.id_ordine, o.cf, dataordinato, tipologia, m.id_merce, quantità, descrizione\n" +
                "FROM ordine AS o\n" +
                "JOIN specifiche AS s ON o.id_ordine = s.id_ordine\n" +
                "JOIN merce AS m ON m.id_merce = s.id_merce\n" +
                "ORDER BY dataordinato DESC\n" +
                "LIMIT 10;\n";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Ordine ordine = new Ordine(
                            resultSet.getInt("id_ordine"),
                            resultSet.getString("cf"),
                            resultSet.getString("dataordinato"),
                            resultSet.getString("tipologia"),
                            resultSet.getString("id_merce"),
                            resultSet.getInt("quantità"),
                            resultSet.getString("descrizione"));
                    ordini.add(ordine);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordini;
    }
    public List<Ordine> cercaInOrdiniPerCliente(String cf) {
        List<Ordine> ordini = new ArrayList<>();

        if (connection == null) {
            Alerts.mostraMessaggioErrore("Errore", "Errore", "La connessione al database non è stata stabilita correttamente.");
            return ordini;
        }

        String query = "select o.id_ordine ,cf,dataordinato,tipologia,m.id_merce,quantità,descrizione \n" +
                "from \n" +
                "(ordine as o join specifiche as s on o.id_ordine = s.id_ordine)\n" +
                "join merce as m on m.id_merce = s.id_merce WHERE cf = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cf);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("ciao");
                    Ordine ordine = new Ordine(
                            resultSet.getInt("id_ordine"),
                            resultSet.getString("cf"),
                            resultSet.getString("dataordinato"),
                            resultSet.getString("tipologia"),
                            resultSet.getString("id_merce"),
                            resultSet.getInt("quantità"),
                            resultSet.getString("descrizione")
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