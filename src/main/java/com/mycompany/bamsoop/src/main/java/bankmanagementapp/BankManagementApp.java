
package bankmanagementapp;


public class BankManagementApp {

    public static void main(String[] args) {
        
       
java.sql.Connection testCon = accDatabase.getConnection();
if (testCon != null) {
    System.out.println("Connection works!");
} else {
    System.out.println("Connection failed!");
}
       
        frontPage page = new frontPage ();
        page.setVisible(true);
        
        
    }
    
}
