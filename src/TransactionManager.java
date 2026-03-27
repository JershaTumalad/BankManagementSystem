import java.util.*;

public class TransactionManager {
    private ArrayList<Transaction> transactionList = new ArrayList<>();
    
    public void addTransaction(int id, String type, double amount, String date, String description) {
        Transaction newTransaction = new Transaction(id, type, amount, date, "Successful", description);
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
