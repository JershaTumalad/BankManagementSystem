
public class Transfer extends Transaction{
    private String recipient;
        
    public Transfer(int transactionID, double amount, String date, String recipient) {
        super(transactionID, "Transfer", amount, date, "Successful");
        this.recipient = recipient;
    }
     
    public String getRecipient(){
        return recipient;
    }
    
    public void setRecipient(String recipient){
        this.recipient = recipient;
    }
    
    @Override
    public void displayTransaction(){
        super.displayTransaction();
        System.out.println("Recipient        : " + recipient);
        System.out.println("-----------------------------");
    }
}
