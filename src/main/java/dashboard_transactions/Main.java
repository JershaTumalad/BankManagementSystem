package dashboard_transactions;

import java.util.*;

public class Main{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        TransactionManager manager = new TransactionManager();
TransactionManager manager = new TransactionManager(0);
        int choice = 1;

        while (choice == 1) {
            System.out.println("\n===== NEW TRANSACTION =====");

            System.out.println("Select Transaction Type:");
            System.out.println("[1] Deposit");
            System.out.println("[2] Withdrawal");
            System.out.println("[3] Transfer");
            System.out.println("[4] Bill Payment");
            System.out.println("[5] Buy Load");
            System.out.print("Enter choice: ");
            int typeChoice = sc.nextInt();
            sc.nextLine();
            
            String transactionType;
            switch (typeChoice) {
                case 1: 
                    transactionType = "Deposit"; 
                    break;
                case 2: 
                    transactionType = "Withdrawal"; 
                    break;
                case 3: 
                    transactionType = "Transfer"; 
                    break;
                case 4: 
                    transactionType = "Bills Payment"; 
                    break;
                case 5:
                    transactionType = "Buy Load";
                    break;
                default: transactionType = "Unknown"; 
                break;
            }
            
            String description = "";
            switch(transactionType){
                case "Bills Payment":
                    System.out.println("Select Biller:");
                    System.out.println("[1] Meralco");
                    System.out.println("[2] PLDT");
                    System.out.println("[3] Globe");
                    System.out.println("[4] Maynilad");
                    System.out.println("[5] Sky Cable");
                    System.out.println("Enter choice:");
                    int billerChoice = sc.nextInt();
                    sc.nextLine();
                    switch(billerChoice){
                        case 1:
                            description = "Meralco";
                            break;
                        case 2:
                            description = "PLDT";
                            break;
                        case 3:
                            description = "Globe";
                            break;
                        case 4:
                            description = "Maynilad";
                            break;
                        case 5:
                            description = "Sky Cable";
                            break;
                        default:
                            description = "Other";
                            break;
                    }
                    break;
            case "Transfer":
                System.out.println("Enter Recipient Name: ");
                description = sc.nextLine();
                break;
                
            case "Buy Load":
                System.out.println("Select Telco:");
                System.out.println("[1] Globe");
                System.out.println("[2] Smart");
                System.out.println("[3] DITO");
                System.out.println("[4] TM");
                System.out.println("[5] TNT");
                System.out.println("Enter choice:");
                int telcoChoice = sc.nextInt();
                sc.nextLine();
                switch(telcoChoice){
                    case 1: description = "Globe"; break;
                    case 2: description = "Smart"; break;
                    case 3: description = "DITO"; break;
                    case 4: description = "TM"; break;
                    case 5: description = "TNT"; break;
                    default: description = "Other"; break;
                }
                break;
            }
            
            double amount = 0;
            boolean validAmount = false;
            while (!validAmount) {
                System.out.print("Enter Amount: PHP ");
                amount = sc.nextDouble();
                sc.nextLine();
                if (amount > 0) {
                    validAmount = true;
                } else {
                    System.out.println("❌ Amount must be greater than 0. Try again.");
                }
            }
            
            String date = "";
            boolean validDate = false;
            while(!validDate){
                System.out.print("Enter Date (YYYY-MM-DD): ");
                date = sc.nextLine();
                if(Transaction.isValidDate(date)){
                    validDate = true;
                }else{
                    System.out.println("❌ Invalid date format. Try again.");
                }
            }


            manager.addTransaction(transactionType, amount, date, description);
            System.out.print("\nDo you want to add another transaction? [1] Yes  [0] No: ");
            choice = sc.nextInt();
            sc.nextLine();

         if (choice == 0) {
    System.out.println("\nExiting program. Goodbye!");
}
        }

    }
}