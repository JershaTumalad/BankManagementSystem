import java.util.*;

public class Transaction {

    private int transactionID;
    private String transactionType; // "Deposit", "Withdrawal", "Transfer"
    private double amount;
    private String date; // Format: YYYY-MM-DD

    public Transaction(int transactionID, String transactionType, double amount, String date) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(double amount) {
        if (amount > 0) { // Validation: hindi pwedeng negative o zero ang amount
            this.amount = amount;
        } else {
            System.out.println("Invalid amount. Must be greater than 0.");
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void displayTransaction() {
        System.out.println("-----------------------------");
        System.out.println("Transaction ID   : " + transactionID);
        System.out.println("Transaction Type : " + transactionType);
        System.out.println("Amount           : PHP " + String.format("%.2f", amount));
        System.out.println("Date             : " + date);
        System.out.println("-----------------------------");
    }
}