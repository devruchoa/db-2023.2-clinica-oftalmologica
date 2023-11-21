package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String dbName = "oftalmodb";
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/";
    private static final String username = "postgres";
    private static final String password = "postgres";

    public static Connection connectionDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connectionDB = DriverManager.getConnection(dbUrl.concat(dbName), username, password);

        if (connectionDB != null) {
            System.out.println("Connection with database was successful!");
            return connectionDB;
        } else {
            throw new RuntimeException("Ops! Error to connect with database. :(");
        }
    }

    public static PreparedStatement prepareSQL(String sql) throws SQLException, ClassNotFoundException {
        return connectionDB().prepareStatement(sql);
    }

    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQL State: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
