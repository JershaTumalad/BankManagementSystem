package dashboard_transactions;

public class Deposit extends Transaction{

    public Deposit(int transactionID, double amount, String date) {
        super(transactionID, "Deposit", amount, date, "Successful");
        
    }
}