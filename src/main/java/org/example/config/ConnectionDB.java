package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {

    private static final Logger LOGGER = Logger.getLogger(ConnectionDB.class.getName());

    private static final String dbName = "oftalmodb";
    private static final String dbUrl = "jdbc:postgresql://localhost:5433/";
    private static final String username = "postgres";
    private static final String password = "postgres";

    public static Connection connectionDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connectionDB = DriverManager.getConnection(dbUrl.concat(dbName), username, password);

        if (connectionDB == null) {
            throw new RuntimeException("Ops! Error to connect with database. :(");
        }

        LOGGER.info("Connection with database was successful!");
        return connectionDB;
    }

    public static PreparedStatement prepareSQL(String sql) throws SQLException, ClassNotFoundException {
        return connectionDB().prepareStatement(sql);
    }

    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                LOGGER.log(Level.SEVERE, "SQL State: " + ((SQLException) e).getSQLState(), e);
                LOGGER.log(Level.SEVERE, "Error Code: " + ((SQLException) e).getErrorCode(), e);
                LOGGER.log(Level.SEVERE, "Message: " + e.getMessage(), e);
                Throwable t = ex.getCause();
                while (t != null) {
                    LOGGER.log(Level.SEVERE, "Cause: " + t, t);
                    t = t.getCause();
                }
            }
        }
    }
}
