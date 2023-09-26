package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.Formatter;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.presentationLayer.exceptions.DaoException;

public class VertragsPartnerDaoSqlite implements IDao<IVertragspartner, String> {

    @Override
    public IVertragspartner create() {
        return new Vertragspartner(null, null);
    }

    @Override
    public void create(IVertragspartner objectToInsert) throws DaoException {
        try {
            // Establish a database connection.
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getNewConnection();

            // Define the SQL query to insert a new 'vertragspartner' record.
            String sql = "INSERT INTO vertragspartner (id, vorname, nachname, strasse, hausnr, plz, ort) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, objectToInsert.getAusweisNr());
            statement.setString(2, objectToInsert.getVorname());
            statement.setString(3, objectToInsert.getNachname());
            statement.setString(4, objectToInsert.getAdresse().getStrasse());
            statement.setString(5, objectToInsert.getAdresse().getHausNr());
            statement.setString(6, objectToInsert.getAdresse().getPlz());
            statement.setString(7, objectToInsert.getAdresse().getOrt());

            // Execute the insert query.
            statement.executeUpdate();

            // Close the connection.
            connectionManager.close(null, statement, connection);

        } catch (SQLException e) {
            throw new DaoException("Error in VertragspartnerDaoSqlite#create(IVertragspartner objectToInsert).");
        }
    }

    @Override
    public IVertragspartner read(String id) throws DaoException {

        Vertragspartner vertragspartner = null;

        try {
            // Establish a database connection.
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getNewConnection();

            // Define the SQL query to select a 'vertragspartner' record by its ID.
            String sql = "SELECT * FROM vertragspartner WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);

            // Execute the select query.
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                // Retrieve data from the result set.
                String ausweisnr = resultSet.getString("id");
                String vorname = resultSet.getString("vorname");
                String nachname = resultSet.getString("nachname");
                String strasse = resultSet.getString("strasse");
                String hausnr = resultSet.getString("hausnr");
                String plz = resultSet.getString("plz");
                String ort = resultSet.getString("ort");

                vertragspartner = new Vertragspartner(vorname, nachname);

                vertragspartner.setAusweisNr(ausweisnr);

                Adresse adresse = new Adresse(strasse, hausnr, plz, ort);

                vertragspartner.setAdresse(adresse);
            }

            // Close the connection.
            connectionManager.close(resultSet, statement, connection);

        } catch (SQLException e) {
            throw new DaoException("Error in VertragspartnerDaoSqlite#read().");
        }

        return vertragspartner;
    }

    @Override
    public List<IVertragspartner> readAll() throws DaoException {

        List<IVertragspartner> vertragspartnerList = new ArrayList<>();

        try {
            // Establish a database connection.
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getNewConnection();

            // Define the SQL query to select all 'vertragspartner' records.
            String sql = "SELECT * FROM vertragspartner";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the select query.
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                // Retrieve data from the result set.
                String ausweisnr = resultSet.getString("id");
                String vorname = resultSet.getString("vorname");
                String nachname = resultSet.getString("nachname");
                String strasse = resultSet.getString("strasse");
                String hausnr = resultSet.getString("hausnr");
                String plz = resultSet.getString("plz");
                String ort = resultSet.getString("ort");

                Vertragspartner vertragspartner = new Vertragspartner(vorname, nachname);

                vertragspartner.setAusweisNr(ausweisnr);

                Adresse adresse = new Adresse(strasse, hausnr, plz, ort);

                vertragspartner.setAdresse(adresse);

                vertragspartnerList.add(vertragspartner);
            }

            // Close the connection.
            connectionManager.close(resultSet, statement, connection);

        } catch (SQLException e) {
            throw new DaoException("Error in VertragspartnerDaoSqlite#readAll().");
        }

        return vertragspartnerList;
    }

    @Override
    public void update(IVertragspartner objectToUpdate) throws DaoException {
        try {
            // Establish a database connection.
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getNewConnection();

            // Define the SQL query to update a 'vertragspartner' record by its ID.
            String sql = "UPDATE ware SET vorname = ?, nachname = ?, strasse = ?, hausnr = ?, plz = ?, ort = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, objectToUpdate.getVorname());
            statement.setString(2, objectToUpdate.getNachname());
            statement.setString(3, objectToUpdate.getAdresse().getStrasse());
            statement.setString(4, objectToUpdate.getAdresse().getPlz());
            statement.setString(5, objectToUpdate.getAdresse().getOrt());
            statement.setString(6, objectToUpdate.getAusweisNr());

            // Execute the update query.
            statement.executeUpdate();

            // Close the connection.
            connectionManager.close(null, statement, connection);

        } catch (SQLException e) {
            throw new DaoException("Error in VertragspartnerDaoSqlite#update(IVertragspartner objectToUpdate).");
        }
    }

    @Override
    public void delete(String id) throws DaoException {
        try {
            // Establish a database connection.
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getNewConnection();

            // Define the SQL query to delete a 'vertragspartner' record by its ID.
            String sql = "DELETE FROM vertragspartner WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            // Execute the delete query.
            statement.executeUpdate();

            // Close the connection.
            connectionManager.close(null, statement, connection);

        } catch (SQLException e) {
            throw new DaoException("Error in VertragspartnerDaoSqlite#delete(String id).");
        }
    }
    
}
