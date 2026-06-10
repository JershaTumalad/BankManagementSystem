package dashboard_transactions;

public class AutoPayment extends Transaction {
    
    private String biller;
    private String frequency;
    private String description;

    public AutoPayment(int transactionID, double amount, String date, String biller, String frequency) {
        super(transactionID, "Auto Payment", amount, date, "Successful");
        this.biller      = biller;
        this.frequency   = frequency;
        this.description = "To: " + biller + " (" + frequency + ")";
    }

    public String getBiller()      { return biller; }
    public String getFrequency()   { return frequency; }
    public String getDescription() { return description; }
}