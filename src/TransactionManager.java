import java.util.*;

public class TransactionManager {
    private ArrayList<Transaction> transactionList = new ArrayList<>();
    private int nextID = 0;
    private double balance = 0.0;
    
    public String addTransaction( String type, double amount, String date, String extra) {
        Transaction newTransaction;
        int id = nextID++;
        
        if (!type.equals("Deposit") && amount > balance) {
            return "❌ Insufficient balance. Current balance: PHP " + String.format("%.2f", balance);
            
        }
        
        
        
        switch(type){
            case "Deposit":
                newTransaction = new Deposit(id, amount, date);
                break;
            case "Withdrawal":
                newTransaction = new Withdrawal(id, amount, date);
                break;
            case "Transfer":
                newTransaction = new Transfer(id, amount, date, extra);
                break;
            case "Bills Payment":
                newTransaction = new BillsPayment(id, amount, date, extra);
                break;
            case "Buy Load":
                newTransaction = new BuyLoad(id, amount, date, extra);
                break;
            default:
                return "Invalid transaction type.";
                
        }
        
        if(type.equals("Deposit")){
            balance += amount;
        }else{
            balance -= amount;
        }
        
        transactionList.add(newTransaction);
        return "SUCCESS";
    }
    
    public double getBalance(){
        return balance;
    }
    
    public ArrayList<Transaction> getTransactionList(){
        return transactionList;
    }
    
    public void displayAllTransaction(){
        if(transactionList.isEmpty()){
            System.out.println("No transaction found.");
        }else{
            System.out.println("ALL TRANSACTIONS");
            for(Transaction t : transactionList){
                t.displayTransaction();
            }
            
            
        }
    }
}
