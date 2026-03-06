import java.util.*;

public class Transaction{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 1;
        
        while (choice == 1) {
          
            System.out.print("Enter Transaction ID (e.g., 1001, 1002): ");
            int transactionID = sc.nextInt();
            sc.nextLine();
            
            System.out.println("Select Transaction Type:");
            System.out.println("[1] Deposit");
            System.out.println("[2] Withdrawal");
            System.out.println("[3] Transfer");
            System.out.print("Enter choice: ");
            int typeChoice = sc.nextInt();
            sc.nextLine();
            
            String transactionType;
            if (typeChoice == 1) {
                transactionType = "Deposit";
            } else if (typeChoice == 2) {
                transactionType = "Withdrawal";
            } else if (typeChoice == 3) {
                transactionType = "Transfer";
            } else {
                transactionType = "Unknown";
            }
            
            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            
            System.out.print("Enter Date (YYYY-MM-DD): ");
            String date = sc.nextLine();
            
            sc.nextLine();
            System.out.println("Transaction Details");
            System.out.println("ID: " + transactionID);
            System.out.println("Type: " + transactionType);
            System.out.println("Amount: " + amount);
            System.out.println("Date: " + date);
            
            sc.nextLine();
            System.out.print("Do you want to add another transaction? [1] Yes  [0] No: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            if (choice == 0) {
                sc.nextLine();
                System.out.println("Exiting program. Goodbye!");
            } else {
                sc.nextLine();
                System.out.println("New Transaction");
            }
        }
       
        
        
    }
}