package dashboard_transactions;

import java.sql.*;
import java.util.*;

public class TransactionManager {
    private ArrayList<Transaction> transactionList = new ArrayList<>();
    private int nextID = 0;
    private double balance = 0.0;

    public TransactionManager() {
        loadFromDatabase();
    }

    // LOAD ALL TRANSACTIONS FROM DATABASE ON STARTUP 
    private void loadFromDatabase() {
        String sql = "SELECT * FROM transaction_history ORDER BY transaction_id ASC";
        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id        = rs.getInt("transaction_id");
                String type   = rs.getString("transaction_type");
                double amount = rs.getDouble("amount");
                String date   = rs.getString("date");
                String desc   = rs.getString("description");

                Transaction t;
                switch (type) {
                    case "Deposit":
                        t = new Deposit(id, amount, date);
                        balance += amount;
                        break;
                    case "Withdrawal":
                        t = new Withdrawal(id, amount, date);
                        balance -= amount;
                        break;
                    case "Transfer":
                        t = new Transfer(id, amount, date, desc != null ? desc : "");
                        balance -= amount;
                        break;
                    case "Bills Payment":
                        t = new BillsPayment(id, amount, date, desc != null ? desc : "");
                        balance -= amount;
                        break;
                    case "Buy Load":
                        t = new BuyLoad(id, amount, date, desc != null ? desc : "");
                        balance -= amount;
                        break;
                    case "Auto Payment":
                        String[] parts   = desc != null
                            ? desc.replace("To: ", "").replace(")", "").split(" \\(")
                            : new String[]{"Unknown", "Monthly"};
                        String biller    = parts[0].trim();
                        String frequency = parts.length > 1 ? parts[1].trim() : "Monthly";
                        t = new AutoPayment(id, amount, date, biller, frequency);
                        balance -= amount;
                        break;
                    default:
                        continue;
                }

                transactionList.add(t);
                if (id >= nextID) nextID = id + 1;
            }

        } catch (SQLException e) {
            System.out.println("Failed to load transactions: " + e.getMessage());
        }
    }

    // SAVE TO transaction_history 
    private void saveToHistory(Transaction t, String description) {
        String sql = "INSERT INTO transaction_history " +
                     "(transaction_id, transaction_type, amount, date, description, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,    t.getTransactionID());
            ps.setString(2, t.getTransactionType());
            ps.setDouble(3, t.getAmount());
            ps.setString(4, t.getDate());
            ps.setString(5, description);
            ps.setString(6, t.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to save to history: " + e.getMessage());
        }
    }

    // SAVE TO deposit table 
    private void saveDeposit(Deposit t) {
        String sql = "INSERT INTO deposit (transaction_id, amount, date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,    t.getTransactionID());
            ps.setDouble(2, t.getAmount());
            ps.setString(3, t.getDate());
            ps.setString(4, t.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to save deposit: " + e.getMessage());
        }
    }

    // SAVE TO withdrawal table 
    private void saveWithdrawal(Withdrawal t) {
        String sql = "INSERT INTO withdrawal (transaction_id, amount, date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,    t.getTransactionID());
            ps.setDouble(2, t.getAmount());
            ps.setString(3, t.getDate());
            ps.setString(4, t.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to save withdrawal: " + e.getMessage());
        }
    }

    //  SAVE TO transfer table 
    private void saveTransfer(Transfer t) {
        String sql = "INSERT INTO transfer (transaction_id, amount, date, recipient, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,    t.getTransactionID());
            ps.setDouble(2, t.getAmount());
            ps.setString(3, t.getDate());
            ps.setString(4, t.getRecipient());
            ps.setString(5, t.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to save transfer: " + e.getMessage());
        }
    }

    //  SAVE TO bills_payment table 
    private void saveBillsPayment(BillsPayment t) {
        String sql = "INSERT INTO bills_payment (transaction_id, amount, date, biller_name, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,    t.getTransactionID());
            ps.setDouble(2, t.getAmount());
            ps.setString(3, t.getDate());
            ps.setString(4, t.getBillerName());
            ps.setString(5, t.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to save bills payment: " + e.getMessage());
        }
    }

    // SAVE TO buy_load table 
    private void saveBuyLoad(BuyLoad t) {
        String sql = "INSERT INTO buy_load (transaction_id, amount, date, load_name, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,    t.getTransactionID());
            ps.setDouble(2, t.getAmount());
            ps.setString(3, t.getDate());
            ps.setString(4, t.getLoadName());
            ps.setString(5, t.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to save buy load: " + e.getMessage());
        }
    }

    // SAVE TO auto_payment table 
    private void saveAutoPayment(AutoPayment t) {
        String sql = "INSERT INTO auto_payment (transaction_id, amount, date, biller, frequency, description, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,    t.getTransactionID());
            ps.setDouble(2, t.getAmount());
            ps.setString(3, t.getDate());
            ps.setString(4, t.getBiller());
            ps.setString(5, t.getFrequency());
            ps.setString(6, t.getDescription());
            ps.setString(7, t.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to save auto payment: " + e.getMessage());
        }
    }

    //  ADD TRANSACTION 
    public String addTransaction(String type, double amount, String date, String extra) {
        int id = nextID++;

        if (!type.equals("Deposit") && amount > balance) {
            return "❌ Insufficient balance. Current balance: PHP " + String.format("%.2f", balance);
        }

        switch (type) {
            case "Deposit": {
                Deposit t = new Deposit(id, amount, date);
                balance += amount;
                transactionList.add(t);
                saveDeposit(t);
                saveToHistory(t, "");
                break;
            }
            case "Withdrawal": {
                Withdrawal t = new Withdrawal(id, amount, date);
                balance -= amount;
                transactionList.add(t);
                saveWithdrawal(t);
                saveToHistory(t, "");
                break;
            }
            case "Transfer": {
                Transfer t = new Transfer(id, amount, date, extra);
                balance -= amount;
                transactionList.add(t);
                saveTransfer(t);
                saveToHistory(t, extra);
                break;
            }
            case "Bills Payment": {
                BillsPayment t = new BillsPayment(id, amount, date, extra);
                balance -= amount;
                transactionList.add(t);
                saveBillsPayment(t);
                saveToHistory(t, extra);
                break;
            }
            case "Buy Load": {
                BuyLoad t = new BuyLoad(id, amount, date, extra);
                balance -= amount;
                transactionList.add(t);
                saveBuyLoad(t);
                saveToHistory(t, extra);
                break;
            }
            case "Auto Payment": {
                String[] parts   = extra.replace("To: ", "")
                                        .replace(")", "")
                                        .split(" \\(");
                String biller    = parts[0].trim();
                String frequency = parts.length > 1 ? parts[1].trim() : "Monthly";
                AutoPayment t    = new AutoPayment(id, amount, date, biller, frequency);
                balance -= amount;
                transactionList.add(t);
                saveAutoPayment(t);
                saveToHistory(t, extra);
                break;
            }
            default:
                return "Invalid transaction type.";
        }

        return "SUCCESS";
    }

    public double getBalance() { return balance; }

    public ArrayList<Transaction> getTransactionList() { return transactionList; }

    public void displayAllTransaction() {
        if (transactionList.isEmpty()) {
            System.out.println("No transaction found.");
        } else {
            System.out.println("ALL TRANSACTIONS");
            for (Transaction t : transactionList) {
                t.displayTransaction();
            }
        }
    }
}