package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class manages database connections for SQLite.
 */
public class ConnectionManager {

    private static final String CLASSNAME = "org.sqlite.JDBC";
    private static final String CONNECTIONSTRING = "jdbc:sqlite:database.db"; // includes file path from project destination

    private Connection existingConnection;
    private boolean classLoaded = false;

    /**
     * Get a new database connection.
     *
     * @return A new database connection.
     */
    public Connection getNewConnection() {

        if(!classLoaded) {

            try {
                // Load the SQLite JDBC driver class.
                Class.forName(CLASSNAME);
                classLoaded = true;

                try {
                    // Establish a new database connection.
                    return existingConnection = DriverManager.getConnection(CONNECTIONSTRING);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                // Close the existing connection and return a new one.
                existingConnection.close();
                return existingConnection = DriverManager.getConnection(CONNECTIONSTRING);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Get the existing database connection.
     *
     * @return The existing database connection.
     */
    public Connection getExistingConnection() {
        return existingConnection;
    }

    /**
     * Close the result set, statement, and connection.
     *
     * @param resultSet  The result set to close.
     * @param statement  The statement to close.
     * @param connection The connection to close.
     * @throws SQLException If an SQL error occurs during the close operation.
     */
    public void close(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        if (resultSet != null) {
            try {
                // Close the result set.
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (statement != null) {
            try {
                // Close the statement.
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (connection != null) {
            try {
                // Close the connection.
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    

    public void checkTables() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS ware(id LONG, bezeichnung VARCHAR(100), beschreibung TEXT(1000), preis VARCHAR(50), besonderheiten TEXT(1000), maengel TEXT(1000), PRIMARY KEY (id));" +
            "CREATE TABLE IF NOT EXISTS vertragspartner(id VARCHAR(100), vorname VARCHAR(100), nachname VARCHAR(100), strasse VARCHAR(100), hausnr VARCHAR(100), plz VARCHAR(15), ort VARCHAR(100), PRIMARY KEY (id));";
            
            if(existingConnection == null) existingConnection = getNewConnection();
            existingConnection.createStatement().executeUpdate(sql);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
