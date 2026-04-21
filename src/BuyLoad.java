package BAMS;

public class BuyLoad extends Transaction{
    private String loadName;
    
    public BuyLoad(int transactionID, double amount, String date, String loadName) {
        super(transactionID, "Buy Load", amount, date, "Successful");
        this.loadName = loadName;
    }
    public String getLoadName(){
        return loadName;
    }
    
    public void setLaodName(String loadName){
        this.loadName = loadName;
    }
    
    @Override
    public void displayTransaction(){
        super.displayTransaction();
        System.out.println("Telco            : " + loadName);
        System.out.println("-----------------------------");
    }
}
