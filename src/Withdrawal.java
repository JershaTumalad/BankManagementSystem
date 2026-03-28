
public class Withdrawal extends Transaction{

    public Withdrawal(int transactionID, double amount, String date) {
        super(transactionID, "Withdrawal", amount, date, "Successful");
    }
}
