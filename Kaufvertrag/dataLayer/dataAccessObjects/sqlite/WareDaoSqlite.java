package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.presentationLayer.exceptions.DaoException;

/**
 * This class provides data access methods for the 'ware' table in an SQLite database.
 */
public class WareDaoSqlite implements IDao<IWare, Long> {

    @Override
    public void create(IWare objectToInsert) throws DaoException {
        try {
            // Establish a database connection.
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getNewConnection();

            // Define the SQL query to insert a new 'ware' record.
            String sql = "INSERT INTO ware (bezeichnung, preis) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, objectToInsert.getBezeichnung());
            statement.setString(2, objectToInsert.getPreis());

            // Execute the insert query.
            statement.executeUpdate();

            // Close the connection.
            connectionManager.close(null, statement, connection);

        } catch (SQLException e) {
            throw new DaoException("Error in WareDaoSqlite#create(IWare objectToInsert).");
        }
    }

    @Override
    public IWare read(Long id) throws DaoException {

        Ware ware = null;

        try {
            // Establish a database connection.
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getNewConnection();

            // Define the SQL query to select a 'ware' record by its ID.
            String sql = "SELECT * FROM ware WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            // Execute the select query.
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve data from the result set.
                String bezeichnung = resultSet.getString("bezeichnung");
                String preis = resultSet.getString("preis");

                // Close the connection.
                connectionManager.close(resultSet, statement, connection);

                return new Ware(bezeichnung, preis);
            }

            // Close the connection.
            connectionManager.close(resultSet, statement, connection);

        } catch (SQLException e) {
            throw new DaoException("Error in WareDaoSqlite#read().");
        }

        return ware;
    }

    @Override
    public List<IWare> readAll() throws DaoException {

        List<IWare> wareList = new ArrayList<>();

        try {
            // Establish a database connection.
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getNewConnection();

            // Define the SQL query to select all 'ware' records.
            String sql = "SELECT * FROM ware";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the select query.
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Retrieve data from the result set.
                String bezeichnung = resultSet.getString("bezeichnung");
                String preis = resultSet.getString("preis");

                // Create a 'Ware' object and add it to the list.
                Ware ware = new Ware(bezeichnung, preis);
                wareList.add(ware);
            }

            // Close the connection.
            connectionManager.close(resultSet, statement, connection);

        } catch (SQLException e) {
            throw new DaoException("Error in WareDaoSqlite#readAll().");
        }

        return wareList;
    }

    @Override
    public void update(IWare objectToUpdate) throws DaoException {
        try {
            // Establish a database connection.
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getNewConnection();

            // Define the SQL query to update a 'ware' record by its ID.
            String sql = "UPDATE ware SET bezeichnung = ?, preis = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, objectToUpdate.getBezeichnung());
            statement.setString(2, objectToUpdate.getPreis());
            statement.setLong(3, objectToUpdate.getId());

            // Execute the update query.
            statement.executeUpdate();

            // Close the connection.
            connectionManager.close(null, statement, connection);

        } catch (SQLException e) {
            throw new DaoException("Error in WareDaoSqlite#update(IWare objectToUpdate).");
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        try {
            // Establish a database connection.
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getNewConnection();

            // Define the SQL query to delete a 'ware' record by its ID.
            String sql = "DELETE FROM ware WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            // Execute the delete query.
            statement.executeUpdate();

            // Close the connection.
            connectionManager.close(null, statement, connection);

        } catch (SQLException e) {
            throw new DaoException("Error in WareDaoSqlite#delete(Long id).");
        }
    }

    @Override
    public IWare create() {
        return new Ware(null, null);
    }
}
