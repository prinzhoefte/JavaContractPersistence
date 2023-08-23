package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private String CLASSNAME, CONNECTIONSTRING;
    private Connection existingConnection;
    private boolean classLoaded;

    public Connection getNewConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            classLoaded = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            classLoaded = false;
            return null;
        }

        String file = "../../db.sql";
        String url = "jdbc:sqlite:" + file;

        try {
            Connection connection = DriverManager.getConnection(url);
            existingConnection = connection;
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Connection getExistingConnection() {
        return existingConnection;
    }

    public void close(ResultSet resultSet, Statement statement, Connection connection) {
    

        PreparedStatement preparedStatement = (PreparedStatement) statement;

        try {
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
