import java.util.*;

public class Transaction {

    private int transactionID;
    private String transactionType; // "Deposit", "Withdrawal", "Transfer"
    private double amount;
    private String date; // Format: YYYY-MM-DD
    private String status;
    private String description;

    public Transaction(int transactionID, String transactionType, double amount, String date, String status, String description) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
        this.status = status;
        this.description = description;
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
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        if(status.equals("Successful") || status.equals("Pending") || status.equals("Failed")){
            this.status = status;
        }else{
            System.out.print("Invalid status.");
        }
    }
    
    public String getDescriptoin(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }

    public void displayTransaction() {
        System.out.println("-----------------------------");
        System.out.println("Transaction ID   : " + transactionID);
        System.out.println("Transaction Type : " + transactionType);
        if(transactionType.equals("Deposit")){
            System.out.println("Amount       : + PHP " + String.format("%.2f", amount));
        }else{
            System.out.println("Amount           : - PHP " + String.format("%.2f", amount));
        }
        System.out.println("Date             : " + date);
        System.out.println("Status           : " + status);
        System.out.println("Description      : " + description);
        System.out.println("-----------------------------");
    }
}