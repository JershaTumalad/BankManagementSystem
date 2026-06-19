package bankmanagementapp;

import java.sql.*;
import java.time.LocalDate;

/**
 * AutoPayScheduler — runs every time the user logs in (called from BankAppGUI).
 *
 * It checks the auto_payment table for any scheduled payments where
 * next_run_date <= today (AppClock.today()), then:
 *   1. Deducts the amount from the account balance
 *   2. Records the transaction in transaction_history
 *   3. Updates next_run_date to the next cycle
 *   4. Shows a summary dialog so the user sees what was processed
 */
public class AutoPayScheduler {

    /**
     * Call this once on login / BankAppGUI open.
     * It silently processes any due payments and shows a summary if anything ran.
     */
    public static void processDuePayments(int accountNumber, BankAppGUI gui) {
        StringBuilder summary = new StringBuilder();
        int processed = 0;

        try {
            Connection con = DBConnection1.getConnection();

            // Find all ACTIVE scheduled payments due today or earlier
            String sql = "SELECT * FROM auto_payment "
                       + "WHERE account_number = ? "
                       + "AND is_active = 1 "
                       + "AND next_run_date <= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNumber);
            ps.setDate(2, java.sql.Date.valueOf(AppClock.today()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int txnId        = rs.getInt("transaction_id");
                String biller    = rs.getString("biller");
                double amount    = rs.getDouble("amount");
                String frequency = rs.getString("frequency");
                String desc      = rs.getString("description");
                LocalDate nextRun = rs.getDate("next_run_date").toLocalDate();

                // Check if there's enough balance
                double balance = getBalance(accountNumber, con);
                if (balance < amount) {
                    // Mark this payment as FAILED this cycle but still advance next_run_date
                    logTransaction(con, accountNumber, biller, amount, desc, "Failed");
                    advanceNextRunDate(con, txnId, nextRun, frequency);
                    summary.append("⚠ ").append(biller)
                           .append(" — FAILED (insufficient balance)\n");
                    continue;
                }

                // Deduct balance
                deductBalance(con, accountNumber, amount);

                // Log to transaction_history
                logTransaction(con, accountNumber, biller, amount, desc, "Successful");

                // Advance next_run_date
                advanceNextRunDate(con, txnId, nextRun, frequency);

                summary.append("✔ ").append(biller)
                       .append(" — PHP ").append(String.format("%,.2f", amount))
                       .append(" (").append(frequency).append(")\n");
                processed++;
            }

            con.close();

        } catch (SQLException ex) {
            System.out.println("[AutoPayScheduler] Error: " + ex.getMessage());
        }

        // Show summary popup if anything was processed
        if (processed > 0 || summary.length() > 0) {
            String msg = "Auto Payment(s) Processed:\n\n" + summary.toString();
            javax.swing.JOptionPane.showMessageDialog(
                gui, msg, "Auto Payment Summary",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
            gui.updateBalance("None");
        }
    }

    // ── Helpers ─────────────────────────────────────────────────────────────

    private static double getBalance(int accountNumber, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
            "SELECT balance FROM accounts WHERE account_number = ?");
        ps.setInt(1, accountNumber);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getDouble("balance") : 0.0;
    }

    private static void deductBalance(Connection con, int accountNumber, double amount)
            throws SQLException {
        PreparedStatement ps = con.prepareStatement(
            "UPDATE accounts SET balance = balance - ? WHERE account_number = ?");
        ps.setDouble(1, amount);
        ps.setInt(2, accountNumber);
        ps.executeUpdate();
    }

    private static void logTransaction(Connection con, int accountNumber,
            String biller, double amount, String desc, String status) throws SQLException {
        String sql = "INSERT INTO transaction_history "
                   + "(account_number, transaction_type, amount, date, description, status) "
                   + "VALUES (?, 'Auto Payment', ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, accountNumber);
        ps.setDouble(2, amount);
        ps.setString(3, AppClock.nowTimestamp());
        ps.setString(4, desc != null && !desc.isEmpty() ? desc : biller);
        ps.setString(5, status);
        ps.executeUpdate();
    }

    private static void advanceNextRunDate(Connection con, int txnId,
            LocalDate current, String frequency) throws SQLException {
        LocalDate next;
        switch (frequency) {
            case "Daily":   next = current.plusDays(1);   break;
            case "Weekly":  next = current.plusWeeks(1);  break;
            case "Monthly": next = current.plusMonths(1); break;
            case "Yearly":  next = current.plusYears(1);  break;
            default:        next = current.plusMonths(1); break;
        }

        PreparedStatement ps = con.prepareStatement(
            "UPDATE auto_payment SET next_run_date = ? WHERE transaction_id = ?");
        ps.setDate(1, java.sql.Date.valueOf(next));
        ps.setInt(2, txnId);
        ps.executeUpdate();
    }
}