package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FlightBookingDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Connection failed", e);
        }
        return conn;
    }
}