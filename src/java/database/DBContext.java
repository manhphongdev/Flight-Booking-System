package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

  private Connection conn;

    public DBContext() {
    }

    public Connection getConn() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=FlightBooking;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "sa";

            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
             Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return conn;
    }
}
