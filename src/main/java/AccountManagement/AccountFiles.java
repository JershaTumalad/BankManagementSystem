package AccountManagement;
 
import java.sql.*;
import java.util.ArrayList;
 

public class AccountFiles {
 
    
    public boolean addAccount(int userId, String name, String type, String accNo, double bal) {
 
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
            "INSERT INTO accounts (user_id, account_no, name, account_type, balance) "
          + "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(insertSQL)) {
 
            ps.setInt(1, userId);
            ps.setString(2, accNo.trim());
            ps.setString(3, name.trim());
            ps.setString(4, type.trim());
            ps.setDouble(5, bal);
            return ps.executeUpdate() > 0;
 
        } catch (SQLException e) {
            dbError("adding account", e);
            return false;
        }
    }
 
 
    public Account searchAccount(String accNo, int userId) {
 
        String sql =
            "SELECT user_id, account_no, name, account_type, balance "
          + "FROM accounts WHERE account_no = ? AND user_id = ?";
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, accNo.trim());
            ps.setInt(2, userId);
            
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                return new Account(
                    rs.getInt("user_id"), 
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
 
 
    public boolean removeAccount(String accNo, int userId) {
 
        String sql = "DELETE FROM accounts WHERE account_no = ? AND user_id = ?";
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, accNo.trim());
            ps.setInt(2, userId);
            
            return ps.executeUpdate() > 0;
 
        } catch (SQLException e) {
            dbError("removing account", e);
            return false;
        }
    }
 
 
    public Object[][] getAccountsData(int userId) {
 
        String sql =
            "SELECT account_no, name, account_type, balance "
          + "FROM accounts "
          + "WHERE user_id = ? "
          + "ORDER BY account_no";
 
        ArrayList<Object[]> rows = new ArrayList<>();
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

             ps.setInt(1, userId);

             ResultSet rs = ps.executeQuery();

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
 
 
    public double getTotalBalance(int userId) {
 
        String sql = "SELECT COALESCE(SUM(balance), 0) AS total "
                + "FROM accounts " 
                + "WHERE user_id = ?";
 
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
                + "WHERE user_id = ?";
 
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