package bankmanagementapp;

import java.sql.*;
import java.util.ArrayList;

public class AccountFiles {

    public boolean addAccount(int userId, String name, String type, String accNo, double bal) {

        // Check if account_number already exists
        String checkSQL = "SELECT COUNT(*) FROM accounts WHERE account_number = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(checkSQL)) {

            ps.setLong(1, Long.parseLong(accNo.trim()));
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return false;
            }

        } catch (SQLException e) {
            dbError("checking uniqueness", e);
            return false;
        }

        // Split name into parts — name format: "LASTNAME, FIRSTNAME MIDDLENAME"
        String lastName = "";
        String firstName = "";
        String middleName = "";
        if (name.contains(",")) {
            String[] parts = name.split(",", 2);
            lastName = parts[0].trim();
            String[] firstMid = parts[1].trim().split(" ", 2);
            firstName = firstMid[0].trim();
            if (firstMid.length > 1) middleName = firstMid[1].trim();
        } else {
            firstName = name.trim();
        }

        String insertSQL =
            "INSERT INTO accounts (account_number, full_name, last_name, first_name, middle_name, account_type, balance) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(insertSQL)) {

            ps.setLong(1, Long.parseLong(accNo.trim()));
            ps.setString(2, name.trim());
            ps.setString(3, lastName);
            ps.setString(4, firstName);
            ps.setString(5, middleName.isEmpty() ? null : middleName);
            ps.setString(6, type.trim());
            ps.setDouble(7, bal);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            dbError("adding account", e);
            return false;
        }
    }


    public Account searchAccount(String accNo, int userId) {

        String sql =
            "SELECT account_number, full_name, account_type, balance "
          + "FROM accounts WHERE account_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, Long.parseLong(accNo.trim()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(
                    rs.getString("full_name"),
                    rs.getString("account_type"),
                    rs.getString("account_number"),
                    rs.getDouble("balance")
                );
            }

        } catch (SQLException e) {
            dbError("searching account", e);
        }

        return null;
    }


    public boolean removeAccount(String accNo, int userId) {

        String sql = "DELETE FROM accounts WHERE account_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(accNo.trim()));
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            dbError("removing account", e);
            return false;
        }
    }


    public Object[][] getAccountsData(int userId) {

//        String sql =
//            "SELECT account_number, full_name, account_type, balance "
//          + "FROM accounts "
//          + "WHERE account_number = ? "
//          + "ORDER BY account_number";

String sql =
    "SELECT account_number, full_name, account_type, balance "
  + "FROM accounts "
  + "ORDER BY account_number";

        ArrayList<Object[]> rows = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

//             ps.setInt(1, userId);

             ResultSet rs = ps.executeQuery();

             while (rs.next()) {
                rows.add(new Object[]{
                    rs.getString("account_number"),
                    rs.getString("full_name"),
                    rs.getString("account_type"),
                    String.format("P %,.2f", rs.getDouble("balance"))
                });
            }

        } catch (SQLException e) {
            dbError("loading account table", e);
        }

        Object[][] data = new Object[rows.size()][4];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }
        return data;
    }


    public double getTotalBalance(int userId) {

        String sql = "SELECT COALESCE(SUM(balance), 0) AS total "
                + "FROM accounts "
                + "WHERE account_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

             ps.setInt(1, userId);

             ResultSet rs = ps.executeQuery();

             if (rs.next())
                 return rs.getDouble("total");

        } catch (SQLException e) {
            dbError("calculating total balance", e);
        }

        return 0.0;
    }


    public int getAccountCount(int userId) {

        String sql = "SELECT COUNT(*) AS cnt "
                + "FROM accounts "
                + "WHERE account_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

             ps.setInt(1, userId);

             ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getInt("cnt");



        } catch (SQLException e) {
            dbError("counting accounts", e);
        }

        return 0;
    }


    private void dbError(String operation, SQLException e) {
        System.err.println("[AccountFiles] DB error while " + operation
                + ": " + e.getMessage());
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(
            null,
            "Database error while " + operation + ".\n"
                + "Check your MySQL connection and try again.\n\n"
                + "Details: " + e.getMessage(),
            "Database Error",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
}