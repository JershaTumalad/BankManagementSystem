
package AccountManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This file Manages the MySQL database connection for the application.
public class DBConnection {
 
    // Database connection settings
    private static final String URL      = "jdbc:mysql://localhost:3306/bank_management";
    private static final String DB_USER  = "root";
    private static final String DB_PASS  = "";          
 
    // Loads the MySQL JDBC driver when the class is first used
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("[DBConnection] MySQL driver not found. " +
                    "Make sure mysql-connector-j-8.0.33.jar is in Libraries.");
            e.printStackTrace();
        }
    }
 
    // Opens and returns a new database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASS);
    }
 
    // Private constructor prevents instantiation; this is a utility class
    private DBConnection() {}
}
