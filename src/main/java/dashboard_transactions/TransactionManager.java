//package dashboard_transactions;
//
//import java.util.*;
//
//public class TransactionManager {
//    private ArrayList<Transaction> transactionList = new ArrayList<>();
//    private int nextID = 0;
//    private double balance = 0.0;
//    
//    public String addTransaction( String type, double amount, String date, String extra) {
//        Transaction newTransaction;
//        int id = nextID++;
//        
//        if (!type.equals("Deposit") && amount > balance) {
//            return "❌ Insufficient balance. Current balance: PHP " + String.format("%.2f", balance);
//            
//        }
//        
//        
//        
//        switch(type){
//            case "Deposit":
//                newTransaction = new Deposit(id, amount, date);
//                break;
//            case "Withdrawal":
//                newTransaction = new Withdrawal(id, amount, date);
//                break;
//            case "Transfer":
//                newTransaction = new Transfer(id, amount, date, extra);
//                break;
//            case "Bills Payment":
//                newTransaction = new BillsPayment(id, amount, date, extra);
//                break;
//            case "Buy Load":
//                newTransaction = new BuyLoad(id, amount, date, extra);
//                break;
//            default:
//                return "Invalid transaction type.";
//                
//        }
//        
//        if(type.equals("Deposit")){
//            balance += amount;
//        }else{
//            balance -= amount;
//        }
//        
//        transactionList.add(newTransaction);
//        return "SUCCESS";
//    }
//    
//    public double getBalance(){
//        return balance;
//    }
//    
//    public ArrayList<Transaction> getTransactionList(){
//        return transactionList;
//    }
//    
//    public void displayAllTransaction(){
//        if(transactionList.isEmpty()){
//            System.out.println("No transaction found.");
//        }else{
//            System.out.println("ALL TRANSACTIONS");
//            for(Transaction t : transactionList){
//                t.displayTransaction();
//            }
//            
//            
//        }
//    }
//}


package dashboard_transactions;

import bankmanagementapp.DBConnection;
import java.sql.*;
import java.util.ArrayList;

public class TransactionManager {

    private int accountNumber;

    public TransactionManager(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String addTransaction(String type, double amount, String date, String extra) {
        // Check balance first for non-deposit transactions
        if (!type.equals("Deposit") && amount > getBalance()) {
            return "Insufficient balance. Current balance: PHP " + String.format("%.2f", getBalance());
        }

        try {
            Connection con = DBConnection.getConnection();

            // Insert into transaction_history
            String insertTxn = "INSERT INTO transaction_history (account_number, transaction_type, amount, date, description, status) VALUES (?, ?, ?, ?, ?, 'Successful')";
            PreparedStatement ps = con.prepareStatement(insertTxn);
            ps.setInt(1, accountNumber);
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
            ps2.setInt(2, accountNumber);
            ps2.executeUpdate();

            // Insert into specific transaction table
            switch (type) {
                case "Deposit":
                    insertSpecific("INSERT INTO deposit (account_number, amount, date, status) VALUES (?, ?, ?, 'Successful')", amount, date, extra, con);
                    break;
                case "Withdrawal":
                    insertSpecific("INSERT INTO withdrawal (account_number, amount, date, status) VALUES (?, ?, ?, 'Successful')", amount, date, extra, con);
                    break;
                case "Transfer":
                    insertSpecific("INSERT INTO transfer (account_number, recipient_account, amount, date, status) VALUES (?, ?, ?, ?, 'Successful')", amount, date, extra, con);
                    break;
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

    private void insertSpecific(String sql, double amount, String date, String extra, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, accountNumber);
        if (sql.contains("recipient_account") || sql.contains("biller_name") || sql.contains("load_name")) {
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
            Connection con = DBConnection.getConnection();
            String sql = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("balance");
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error getting balance: " + ex.getMessage());
        }
        return 0.0;
    }

    public ArrayList<Transaction> getTransactionList() {
        ArrayList<Transaction> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM transaction_history WHERE account_number = ? ORDER BY date DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accountNumber);
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
}