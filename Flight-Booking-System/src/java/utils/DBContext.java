package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    private static Connection conn;

    public DBContext() {}

    public static Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {  
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;databaseName=FlightBookingDB";
                String username = "sa";
                String password = "sa";
                conn = DriverManager.getConnection(url, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return conn;
    }
}