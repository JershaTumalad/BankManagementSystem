import java.util.*;

public class Main{

    private ArrayList<Transaction> TransactionList = new ArrayList<>();

    public void addTransaction(int id, String type, double amount, String date) {
        Transaction newTransaction = new Transaction(id, type, amount, date);
        TransactionList.add(newTransaction);
        System.out.println("\n✔ Transaction successfully added!\n");
        newTransaction.displayTransaction();
    }

    public void displayAllTransactions() {
        if (TransactionList.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("\n===== ALL TRANSACTIONS =====");
            for (Transaction t : TransactionList) {
                t.displayTransaction();
            }
        }
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main manager = new Main();
        int choice = 1;

        while (choice == 1) {
            System.out.println("\n===== NEW TRANSACTION =====");

            System.out.print("Enter Transaction ID (e.g., 1001): ");
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
            switch (typeChoice) {
                case 1: transactionType = "Deposit"; break;
                case 2: transactionType = "Withdrawal"; break;
                case 3: transactionType = "Transfer"; break;
                default: transactionType = "Unknown"; break;
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

            System.out.print("Enter Date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            manager.addTransaction(transactionID, transactionType, amount, date);

            System.out.print("\nDo you want to add another transaction? [1] Yes  [0] No: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) {
                // Display all transactions before exiting
                manager.displayAllTransactions();
                System.out.println("\nExiting program. Goodbye!");
            }
        }

    }
}