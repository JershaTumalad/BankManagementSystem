package BAMS;


import java.util.ArrayList;


public class AccountFiles {
    
    ArrayList <Account> accounts;
    
    public AccountFiles(){
        accounts = new ArrayList <>();
    }
    public boolean addAccount(String name, String acctype, String accNo, double balance){
        
        if(!Account.validateUniqueAccount(accNo, accounts)){
        return false;
        }

        accounts.add(new Account(name, acctype, accNo, balance));
        return true;
    }
    
    Account searchAccount(String accNo){
        
        String cleanAccNo = accNo.trim();

        for (Account acc : accounts){
            if (acc.getAccountNo().equals(cleanAccNo)){
                return acc;
            }
        }
        return null;
    }
    
    boolean removeAccount(String accNo){
        
        String cleanAccNo = accNo.trim();

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNo().equals(cleanAccNo)) {
                accounts.remove(i);
                return true;
            }
        }
        
        return false;
    }
    
    
    String showExistingAccounts (){
        
        if (accounts.isEmpty()){
            return "There are no accounts.";
        }
            
        String output = "";
        
        for (Account acc : accounts){
            output += "Account Number: " + acc.getAccountNo() + "           |      Account Type     : " + acc.getAccountType() +" \n";
            output += "Name                 : " + acc.getName() +      "        |        Balance           : " + acc.getBalance() + "\n \n";
        }
        
        return output;
    }

    public Object[][] getAccountsData() {
        Object[][] data = new Object[accounts.size()][4];

        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            data[i][0] = acc.getAccountNo();
            data[i][1] = acc.getName();
            data[i][2] = acc.getAccountType();
            data[i][3] = acc.getBalance();
    }

    return data;
}
    
}
