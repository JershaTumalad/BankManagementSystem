package dashboard_transactions;

public class BillsPayment extends Transaction{
    private String billerName;
    
    public BillsPayment(int transactionID, double amount, String date, String billerName) {
        super(transactionID, "Bills Payment", amount, date, "Successful");
        this.billerName = billerName;
    }
    public String getBillerName(){
        return billerName;
    }
    
    public void setBillerName(String billerName){
        this.billerName = billerName;
    }
    
    @Override
    public void displayTransaction(){
        super.displayTransaction();
        System.out.println("Biller           : " + billerName);
        System.out.println("-----------------------------");
    }
}
