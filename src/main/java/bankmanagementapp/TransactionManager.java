package bankmanagementapp;

import bankmanagementapp.DBConnection1;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransactionManager {

    private long accountNumber;

    public TransactionManager(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String addTransaction(String type, double amount, String date, String extra) {
        if (!type.equals("Deposit") && amount > getBalance()) {
            return "Insufficient balance. Current balance: PHP " + String.format("%.2f", getBalance());
        }

        try {
            Connection con = DBConnection1.getConnection();

            String insertTxn = "INSERT INTO transaction_history (account_number, transaction_type, amount, date, description, status) VALUES (?, ?, ?, ?, ?, 'Successful')";
            PreparedStatement ps = con.prepareStatement(insertTxn);
            ps.setLong(1, accountNumber);
            ps.setString(2, type);
            ps.setDouble(3, amount);
            ps.setString(4, date);
            ps.setString(5, extra.isEmpty() ? null : extra);
            ps.executeUpdate();

            String updateBalance;
            if (type.equals("Deposit")) {
                updateBalance = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            } else {
                updateBalance = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            }
            PreparedStatement ps2 = con.prepareStatement(updateBalance);
            ps2.setDouble(1, amount);
            ps2.setLong(2, accountNumber);
            ps2.executeUpdate();

            switch (type) {
                case "Deposit":
                    insertSpecific("INSERT INTO deposit (account_number, amount, date, status) VALUES (?, ?, ?, 'Successful')", amount, date, extra, con);
                    break;
                case "Withdrawal":
                    insertSpecific("INSERT INTO withdrawal (account_number, amount, date, status) VALUES (?, ?, ?, 'Successful')", amount, date, extra, con);
                    break;
                // NOTE: "Transfer" case removed on purpose.
                // Transfers now go through transferMoney() below, which
                // properly credits the receiver too. Do NOT route transfers
                // through this method anymore.
                case "Bills Payment":
                    insertSpecific("INSERT INTO bills_payment (account_number, biller_name, amount, date, status) VALUES (?, ?, ?, ?, 'Successful')", amount, date, extra, con);
                    break;
                case "Buy Load":
                    insertSpecific("INSERT INTO buy_load (account_number, load_name, amount, date, status) VALUES (?, ?, ?, ?, 'Successful')", amount, date, extra, con);
                    break;
            }

            con.close();
            return "SUCCESS";

        } catch (SQLException ex) {
            return "Database error: " + ex.getMessage();
        }
    }

    // ── TRANSFER: properly deducts sender AND credits receiver ───────────────
    public String transferMoney(double amount, String date, String recipientAccountStr) {
        if (amount <= 0) return "Invalid transfer amount.";

        String recipientTrimmed = recipientAccountStr == null ? "" : recipientAccountStr.trim();
        if (recipientTrimmed.isEmpty()) return "Recipient account number is required.";

        long recipientAccountNumber;
        try {
            recipientAccountNumber = Long.parseLong(recipientTrimmed);
        } catch (NumberFormatException ex) {
            return "Recipient account number must be numeric.";
        }

        if (recipientAccountNumber == accountNumber)
            return "You cannot transfer to your own account.";

        if (amount > getBalance())
            return "Insufficient balance. Current balance: PHP " + String.format("%.2f", getBalance());

        Connection con = null;
        try {
            con = DBConnection1.getConnection();
            con.setAutoCommit(false); // all steps succeed or all roll back

            // 1. Check recipient exists
            PreparedStatement checkPs = con.prepareStatement(
                "SELECT account_number FROM accounts WHERE account_number = ?");
            checkPs.setLong(1, recipientAccountNumber);
            ResultSet rs = checkPs.executeQuery();
            if (!rs.next()) { con.rollback(); con.close(); return "Recipient account does not exist."; }
            rs.close(); checkPs.close();

            // 2. Deduct from sender
            PreparedStatement deductPs = con.prepareStatement(
                "UPDATE accounts SET balance = balance - ? WHERE account_number = ?");
            deductPs.setDouble(1, amount);
            deductPs.setLong(2, accountNumber);
            deductPs.executeUpdate(); deductPs.close();

            // 3. Credit receiver
            PreparedStatement creditPs = con.prepareStatement(
                "UPDATE accounts SET balance = balance + ? WHERE account_number = ?");
            creditPs.setDouble(1, amount);
            creditPs.setLong(2, recipientAccountNumber);
            creditPs.executeUpdate(); creditPs.close();

            // 4. Log for sender
            PreparedStatement txnSenderPs = con.prepareStatement(
                "INSERT INTO transaction_history (account_number, transaction_type, amount, date, description, status) "
              + "VALUES (?, 'Transfer', ?, ?, ?, 'Successful')");
            txnSenderPs.setLong(1, accountNumber);
            txnSenderPs.setDouble(2, amount);
            txnSenderPs.setString(3, date);
            txnSenderPs.setString(4, "Transfer to " + recipientAccountNumber);
            txnSenderPs.executeUpdate(); txnSenderPs.close();

            // 5. Log for receiver
            PreparedStatement txnReceiverPs = con.prepareStatement(
                "INSERT INTO transaction_history (account_number, transaction_type, amount, date, description, status) "
              + "VALUES (?, 'Transfer Received', ?, ?, ?, 'Successful')");
            txnReceiverPs.setLong(1, recipientAccountNumber);
            txnReceiverPs.setDouble(2, amount);
            txnReceiverPs.setString(3, date);
            txnReceiverPs.setString(4, "Transfer from " + accountNumber);
            txnReceiverPs.executeUpdate(); txnReceiverPs.close();

            // 6. Log into transfer table
            PreparedStatement transferPs = con.prepareStatement(
                "INSERT INTO transfer (account_number, recipient_account, amount, date, status) "
              + "VALUES (?, ?, ?, ?, 'Successful')");
            transferPs.setLong(1, accountNumber);
            transferPs.setLong(2, recipientAccountNumber);
            transferPs.setDouble(3, amount);
            transferPs.setString(4, date);
            transferPs.executeUpdate(); transferPs.close();

            con.commit();
            con.close();
            return "SUCCESS";

        } catch (SQLException ex) {
            try { if (con != null) { con.rollback(); con.close(); } } catch (SQLException ignored) {}
            return "Database error: " + ex.getMessage();
        }
    }

    // ── Schedule auto payment (NO immediate deduction) ────────────────────────
    public String scheduleAutoPayment(String biller, double amount, String frequency, String description) {
        try {
            Connection con = DBConnection1.getConnection();

            LocalDate firstRun = calculateNextRun(AppClock.today(), frequency);

            String sql = "INSERT INTO auto_payment "
                       + "(account_number, biller, amount, frequency, description, status, next_run_date, is_active) "
                       + "VALUES (?, ?, ?, ?, ?, 'Scheduled', ?, 1)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, accountNumber);
            ps.setString(2, biller);
            ps.setDouble(3, amount);
            ps.setString(4, frequency);
            ps.setString(5, description.isEmpty() ? null : description);
            ps.setDate(6, java.sql.Date.valueOf(firstRun));
            ps.executeUpdate();

            con.close();
            return "SUCCESS:" + firstRun.toString();
        } catch (SQLException ex) {
            return "Database error: " + ex.getMessage();
        }
    }

    // Kept for backward compatibility — redirects to scheduleAutoPayment
    public String addAutoPayment(String biller, double amount, String frequency, String description) {
        return scheduleAutoPayment(biller, amount, frequency, description);
    }

    // ── Cancel a scheduled auto payment ──────────────────────────────────────
    public String cancelAutoPayment(int transactionId) {
        try {
            Connection con = DBConnection1.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "UPDATE auto_payment SET is_active = 0 WHERE transaction_id = ? AND account_number = ?");
            ps.setInt(1, transactionId);
            ps.setLong(2, accountNumber);
            ps.executeUpdate();
            con.close();
            return "SUCCESS";
        } catch (SQLException ex) {
            return "Database error: " + ex.getMessage();
        }
    }

    // ── Helper: compute first due date ───────────────────────────────────────
    public static LocalDate calculateNextRun(LocalDate from, String frequency) {
        switch (frequency) {
            case "Daily":   return from.plusDays(1);
            case "Weekly":  return from.plusWeeks(1);
            case "Monthly": return from.plusMonths(1);
            case "Yearly":  return from.plusYears(1);
            default:        return from.plusMonths(1);
        }
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    private void insertSpecific(String sql, double amount, String date, String extra, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, accountNumber);
        if (sql.contains("biller_name") || sql.contains("load_name")) {
            ps.setString(2, extra.isEmpty() ? "N/A" : extra);
            ps.setDouble(3, amount);
            ps.setString(4, date);
        } else {
            ps.setDouble(2, amount);
            ps.setString(3, date);
        }
        ps.executeUpdate();
    }

    public double getBalance() {
        try {
            Connection con = DBConnection1.getConnection();
            String sql = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            double balance = 0.0;
            if (rs.next()) balance = rs.getDouble("balance");
            con.close();
            return balance;
        } catch (SQLException ex) {
            System.out.println("Error getting balance: " + ex.getMessage());
        }
        return 0.0;
    }

    public ArrayList<Transaction> getTransactionList() {
        ArrayList<Transaction> list = new ArrayList<>();
        try {
            Connection con = DBConnection1.getConnection();
            String sql = "SELECT * FROM transaction_history WHERE account_number = ? ORDER BY date DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            int id = 0;
            while (rs.next()) {
                list.add(new Transaction(
                    id++,
                    rs.getString("transaction_type"),
                    rs.getDouble("amount"),
                    rs.getString("date"),
                    rs.getString("status")
                ));
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error loading transactions: " + ex.getMessage());
        }
        return list;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void deductBalance(double amount) {
        try {
            Connection con = DBConnection1.getConnection();
            String sql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setLong(2, accountNumber);
            ps.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error deducting balance: " + ex.getMessage());
        }
    }
}