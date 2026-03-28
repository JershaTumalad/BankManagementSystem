import java.util.*;

public class TransactionManager {
    private ArrayList<Transaction> transactionList = new ArrayList<>();
    private int nextID = 1001;
    
    
    public void addTransaction( String type, double amount, String date, String extra) {
        Transaction newTransaction;
        int id = nextID++;
        
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
                System.out.println("Invalid transaction type.");
                return;
        }
        
        transactionList.add(newTransaction);
        System.out.println("\n✔ Transaction successfully added!\n");
        newTransaction.displayTransaction();
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
