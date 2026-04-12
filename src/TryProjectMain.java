
package BAMS;

public class TryProjectMain {

    public static void main(String[] args) {
        
        accountFiles files = new accountFiles();
        
        GUI1frame hp = new GUI1frame(files);
        hp.setVisible(true);
        
//        boolean run = true;
//        
//        while (run) {
//            System.out.println("Bank Management Accounts: ");
//            System.out.println("1. Add Account \n2. View Accounts \n3. Search Account \n4. Remove Account \n5. Exit Program");
//            
//            System.out.println("Select from the menu: ");
//            int choice = scan.nextInt();
//            
//            switch (choice) {
//                
//                case 1:
//                    files.addAccount();
//                    break;
//                    
//                case 2:
//                    files.showExistingAccounts();
//                    break;
//                    
//                case 3:
//                    files.searchAccount();
//                    break; 
//                    
//                case 4:
//                    files.removeAccount();
//                    break;
//                    
//                case 5:
//                    System.out.println("You have exited the program.");
//                    run = false;
//                    break;  
//                    
//                default:
//                    System.out.println("Invalid choice. Please select a number from the menu.");
//            }
//        }
    }
    
}
