package AccountManagement;
 
import java.sql.*;
import java.util.ArrayList;
 

public class AccountFiles {
 
    
    public boolean addAccount(String name, String type, String accNo, double bal) {
 
        String checkSQL = "SELECT COUNT(*) FROM accounts WHERE account_no = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(checkSQL)) {
 
            ps.setString(1, accNo.trim());
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return false;  
            }
 
        } catch (SQLException e) {
            dbError("checking uniqueness", e);
            return false;
        }
 
        String insertSQL =
            "INSERT INTO accounts (account_no, name, account_type, balance) "
          + "VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(insertSQL)) {
 
            ps.setString(1, accNo.trim());
            ps.setString(2, name.trim());
            ps.setString(3, type.trim());
            ps.setDouble(4, bal);
            return ps.executeUpdate() > 0;
 
        } catch (SQLException e) {
            dbError("adding account", e);
            return false;
        }
    }
 
 
    public Account searchAccount(String accNo) {
 
        String sql =
            "SELECT account_no, name, account_type, balance "
          + "FROM accounts WHERE account_no = ?";
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, accNo.trim());
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                return new Account(
                    rs.getString("name"),
                    rs.getString("account_type"),
                    rs.getString("account_no"),
                    rs.getDouble("balance")
                );
            }
 
        } catch (SQLException e) {
            dbError("searching account", e);
        }
 
        return null;
    }
 
 
    public boolean removeAccount(String accNo) {
 
        String sql = "DELETE FROM accounts WHERE account_no = ?";
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, accNo.trim());
            return ps.executeUpdate() > 0;
 
        } catch (SQLException e) {
            dbError("removing account", e);
            return false;
        }
    }
 
 
    public Object[][] getAccountsData() {
 
        String sql =
            "SELECT account_no, name, account_type, balance "
          + "FROM accounts ORDER BY id";
 
        ArrayList<Object[]> rows = new ArrayList<>();
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
 
            while (rs.next()) {
                rows.add(new Object[]{
                    rs.getString("account_no"),
                    rs.getString("name"),
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
 
 
    public double getTotalBalance() {
 
        String sql = "SELECT COALESCE(SUM(balance), 0) AS total FROM accounts";
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
 
            if (rs.next()) return rs.getDouble("total");
 
        } catch (SQLException e) {
            dbError("calculating total balance", e);
        }
 
        return 0.0;
    }
 
 
 
    public int getAccountCount() {
 
        String sql = "SELECT COUNT(*) AS cnt FROM accounts";
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
 
            if (rs.next()) return rs.getInt("cnt");
 
        } catch (SQLException e) {
            dbError("counting accounts", e);
        }
 
        return 0;
    }
 

    public boolean updateAccount(String accNo,
                                  String newName,
                                  String newType,
                                  double newBalance) {
 
        String sql =
            "UPDATE accounts "
          + "SET name = ?, account_type = ?, balance = ? "
          + "WHERE account_no = ?";
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, newName.trim());
            ps.setString(2, newType.trim());
            ps.setDouble(3, newBalance);
            ps.setString(4, accNo.trim());
            return ps.executeUpdate() > 0;
 
        } catch (SQLException e) {
            dbError("updating account", e);
            return false;
        }
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