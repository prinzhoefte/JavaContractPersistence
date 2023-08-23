package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private static final String CLASSNAME = "org.sqlite.JDBC";
    private static final String CONNECTIONSTRING = "jdbc:sqlite:%FILE%"; // URL*

    private Connection existingConnection;
    private boolean classLoaded = false;

    public Connection getNewConnection() {

        if(!classLoaded) {

            try {
                Class.forName(CLASSNAME);
                classLoaded = true;

                try {
                    return existingConnection = DriverManager.getConnection(CONNECTIONSTRING);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                existingConnection.close();
                return existingConnection = DriverManager.getConnection(CONNECTIONSTRING);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Connection getExistingConnection() {
        return existingConnection;
    }

    public void close(ResultSet resultSet, Statement statement, Connection connection) {

        try {
            statement.executeUpdate(CLASSNAME);
            statement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}