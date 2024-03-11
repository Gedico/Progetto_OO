package com.example.progetto_oo.Database;

import com.example.progetto_oo.Alerts.Alerts;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                "where elaborato = true\n" +
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
    public List<Ordine> cercaInOrdiniPerDate(String minDate, String maxDate) {
        List<Ordine> ordini = new ArrayList<>();

        if (connection == null) {
            Alerts.mostraMessaggioErrore("Errore", "Errore", "La connessione al database non è stata stabilita correttamente.");
            return ordini;
        }

        String query = "SELECT o.id_ordine, o.cf, dataordinato, tipologia, m.id_merce, quantità, descrizione " +
                "FROM ordine o " +
                "JOIN specifiche s ON o.id_ordine = s.id_ordine " +
                "JOIN merce m ON m.id_merce = s.id_merce " +
                "WHERE dataordinato BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, minDate);
            preparedStatement.setString(2, maxDate);

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
    public List<Ordine> OrdiniDaGestire() {
        List<Ordine> ordini = new ArrayList<>();

        if (connection == null) {
            Alerts.mostraMessaggioErrore("Errore", "Errore", "La connessione al database non è stata stabilita correttamente.");
            return ordini;
        }

        String query = "SELECT o.id_ordine, o.cf, dataordinato, tipologia, m.id_merce, quantità, descrizione\n" +
                "FROM (ordine AS o\n" +
                "JOIN specifiche AS s ON o.id_ordine = s.id_ordine\n" +
                "JOIN merce AS m ON m.id_merce = s.id_merce)\n" +
                "WHERE elaborato = false";
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
    public boolean verificaEsistenzaOrdine(int ordine) {
        boolean clienteEsiste = false;

        if (connection == null) {
            Alerts.mostraMessaggioErrore("Errore", "Errore", "La connessione al database non è stata stabilita correttamente.");
            return clienteEsiste;
        }

        String query = "SELECT COUNT(*) AS count FROM ordine WHERE id_ordine = ? and elaborato=false";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ordine);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    clienteEsiste = count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienteEsiste;
    }
    public boolean creaSpedizione( int idOrdine, String targa, String data) {

        if (connection == null) {
            Alerts.mostraMessaggioErrore("Errore", "Errore", "La connessione al database non è stata stabilita correttamente.");
            return false;
        }

        String sql = "INSERT INTO spedizione(targa, id_operatore, peso_totale, data_spedizione, id_ordine) VALUES (?, '4001', 15, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, targa);

            // Converti la stringa della data in un oggetto java.sql.Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(data);
            preparedStatement.setDate(2, new Date(parsedDate.getTime()));

            preparedStatement.setInt(3, idOrdine);

            int rowsAffected = preparedStatement.executeUpdate();

            // Controlla se la query ha avuto successo
            return rowsAffected > 0;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            // La query ha fallito
            return false;
        }
    }

}