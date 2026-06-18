package bankmanagementapp;

import bankmanagementapp.DBConnection1;
import java.sql.*;
import java.util.ArrayList;

public class TransactionManager {

    private long accountNumber;

    public TransactionManager(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String addTransaction(String type, double amount, String date, String extra) {
        // Check balance first for non-deposit transactions
        if (!type.equals("Deposit") && amount > getBalance()) {
            return "Insufficient balance. Current balance: PHP " + String.format("%.2f", getBalance());
        }

        try {
            Connection con = DBConnection1.getConnection();

            // Insert into transaction_history
            String insertTxn = "INSERT INTO transaction_history (account_number, transaction_type, amount, date, description, status) VALUES (?, ?, ?, ?, ?, 'Successful')";
            PreparedStatement ps = con.prepareStatement(insertTxn);
            ps.setLong(1, accountNumber);
            ps.setString(2, type);
            ps.setDouble(3, amount);
            ps.setString(4, date);
            ps.setString(5, extra.isEmpty() ? null : extra);
            ps.executeUpdate();

            // Update balance in accounts table
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

            // Insert into specific transaction table
            switch (type) {
                case "Deposit":
                    insertSpecific("INSERT INTO deposit (account_number, amount, date, status) VALUES (?, ?, ?, 'Successful')", amount, date, extra, con);
                    break;
                case "Withdrawal":
                    insertSpecific("INSERT INTO withdrawal (account_number, amount, date, status) VALUES (?, ?, ?, 'Successful')", amount, date, extra, con);
                    break;
                case "Bills Payment":
                    insertSpecific("INSERT INTO bills_payment (account_number, biller_name, amount, date, status) VALUES (?, ?, ?, ?, 'Successful')", amount, date, extra, con);
                    break;
                case "Buy Load":
                    insertSpecific("INSERT INTO buy_load (account_number, load_name, amount, date, status) VALUES (?, ?, ?, ?, 'Successful')", amount, date, extra, con);
                    break;
                // NOTE: "Transfer" case removed from here on purpose.
                // Transfers now go through transferMoney() below, which
                // properly credits the receiver too. Do NOT route transfers
                // through this method anymore.
            }

            con.close();
            return "SUCCESS";

        } catch (SQLException ex) {
            return "Database error: " + ex.getMessage();
        }
    }

    // Properly transfers money between two EXISTING accounts.
    // Deducts from sender, credits receiver, all inside one DB transaction.
    public String transferMoney(double amount, String date, String recipientAccountStr) {

        if (amount <= 0) {
            return "Invalid transfer amount.";
        }

        String recipientTrimmed = recipientAccountStr == null ? "" : recipientAccountStr.trim();

        if (recipientTrimmed.isEmpty()) {
            return "Recipient account number is required.";
        }

        long recipientAccountNumber;
        try {
            recipientAccountNumber = Long.parseLong(recipientTrimmed);
        } catch (NumberFormatException ex) {
            return "Recipient account number must be numeric.";
        }

        if (recipientAccountNumber == accountNumber) {
            return "You cannot transfer to your own account.";
        }

        // Check sender balance first
        if (amount > getBalance()) {
            return "Insufficient balance. Current balance: PHP " + String.format("%.2f", getBalance());
        }

        Connection con = null;

        try {
            con = DBConnection1.getConnection();
            con.setAutoCommit(false); // start manual transaction control

            // 1. Check that the recipient account actually exists
            String checkRecipient = "SELECT account_number FROM accounts WHERE account_number = ?";
            PreparedStatement checkPs = con.prepareStatement(checkRecipient);
            checkPs.setLong(1, recipientAccountNumber);
            ResultSet rs = checkPs.executeQuery();

            boolean recipientExists = rs.next();
            rs.close();
            checkPs.close();

            if (!recipientExists) {
                con.rollback();
                con.close();
                return "Recipient account does not exist.";
            }

            // 2. Deduct from sender
            String deductSql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            PreparedStatement deductPs = con.prepareStatement(deductSql);
            deductPs.setDouble(1, amount);
            deductPs.setLong(2, accountNumber);
            deductPs.executeUpdate();
            deductPs.close();

            // 3. Credit the receiver
            String creditSql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            PreparedStatement creditPs = con.prepareStatement(creditSql);
            creditPs.setDouble(1, amount);
            creditPs.setLong(2, recipientAccountNumber);
            creditPs.executeUpdate();
            creditPs.close();

            // 4. Log transaction for the sender
            String insertTxnSender = "INSERT INTO transaction_history (account_number, transaction_type, amount, date, description, status) VALUES (?, 'Transfer', ?, ?, ?, 'Successful')";
            PreparedStatement txnSenderPs = con.prepareStatement(insertTxnSender);
            txnSenderPs.setLong(1, accountNumber);
            txnSenderPs.setDouble(2, amount);
            txnSenderPs.setString(3, date);
            txnSenderPs.setString(4, "Transfer to " + recipientAccountNumber);
            txnSenderPs.executeUpdate();
            txnSenderPs.close();

            // 5. Log transaction for the receiver too
            String insertTxnReceiver = "INSERT INTO transaction_history (account_number, transaction_type, amount, date, description, status) VALUES (?, 'Transfer Received', ?, ?, ?, 'Successful')";
            PreparedStatement txnReceiverPs = con.prepareStatement(insertTxnReceiver);
            txnReceiverPs.setLong(1, recipientAccountNumber);
            txnReceiverPs.setDouble(2, amount);
            txnReceiverPs.setString(3, date);
            txnReceiverPs.setString(4, "Transfer from " + accountNumber);
            txnReceiverPs.executeUpdate();
            txnReceiverPs.close();

            // 6. Log into the transfer table (sender side record)
            String insertTransfer = "INSERT INTO transfer (account_number, recipient_account, amount, date, status) VALUES (?, ?, ?, ?, 'Successful')";
            PreparedStatement transferPs = con.prepareStatement(insertTransfer);
            transferPs.setLong(1, accountNumber);
            transferPs.setLong(2, recipientAccountNumber);
            transferPs.setDouble(3, amount);
            transferPs.setString(4, date);
            transferPs.executeUpdate();
            transferPs.close();

            // Everything succeeded -> commit all changes together
            con.commit();
            con.close();
            return "SUCCESS";

        } catch (SQLException ex) {
            try {
                if (con != null) {
                    con.rollback();
                    con.close();
                }
            } catch (SQLException ignored) {}
            return "Database error: " + ex.getMessage();
        }
    }

    public String addAutoPayment(String biller, double amount, String frequency, String description) {
        if (amount > getBalance()) {
            return "Insufficient balance. Current balance: PHP " + String.format("%.2f", getBalance());
        }
        try {
            Connection con = DBConnection1.getConnection();
            String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                              .format(new java.util.Date());

            // Insert into transaction_history
            String insertTxn = "INSERT INTO transaction_history (account_number, transaction_type, amount, date, description, status) "
                             + "VALUES (?, 'Auto Payment', ?, ?, ?, 'Successful')";
            PreparedStatement ps = con.prepareStatement(insertTxn);
            ps.setLong(1, accountNumber);
            ps.setDouble(2, amount);
            ps.setString(3, date);
            ps.setString(4, description.isEmpty() ? biller : description);
            ps.executeUpdate();

            // Insert into auto_payment table
            String insertAuto = "INSERT INTO auto_payment (account_number, biller, amount, frequency, description, status) "
                              + "VALUES (?, ?, ?, ?, ?, 'Successful')";
            PreparedStatement ps2 = con.prepareStatement(insertAuto);
            ps2.setLong(1, accountNumber);
            ps2.setString(2, biller);
            ps2.setDouble(3, amount);
            ps2.setString(4, frequency);
            ps2.setString(5, description.isEmpty() ? null : description);
            ps2.executeUpdate();

            // Deduct balance
            String updateBal = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            PreparedStatement ps3 = con.prepareStatement(updateBal);
            ps3.setDouble(1, amount);
            ps3.setLong(2, accountNumber);
            ps3.executeUpdate();

            con.close();
            return "SUCCESS";
        } catch (SQLException ex) {
            return "Database error: " + ex.getMessage();
        }
    }

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

    // Required by AutoPaymentGUI
    public long getAccountNumber() {
        return accountNumber;
    }

    // Required by AutoPaymentGUI
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