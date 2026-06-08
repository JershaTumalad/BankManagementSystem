package bankmanagementapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class accDatabase {

    // Keep your existing ArrayList
    public static ArrayList<accCreationPage> acc = new ArrayList<>();

    // Database connection settings
    private static final String URL = "jdbc:mysql://localhost:3306/bankmanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Method to get a connection
    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
            return con;
        } catch (SQLException e) {
            System.out.println("Database connection FAILED: " + e.getMessage());
            return null;
        }
    }
}