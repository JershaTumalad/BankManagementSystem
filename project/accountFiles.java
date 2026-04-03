package project;
import java.util.ArrayList;

public class accountFiles {
    
    ArrayList <Account> accounts;
    
    public accountFiles(){
        accounts = new ArrayList <>();
    }
    void addAccount(String name, String acctype, String accNo, double balance){
        
        Account newAccount = new Account (name, acctype, accNo, balance);
        
        accounts.add(newAccount);
    }
    
    Account searchAccount(String accNo){
        
        
        for (Account acc: accounts){
            if (acc.accountNo.trim().equals(accNo)){
                return acc;
            }
        }
        return null;
    }
    
    boolean removeAccount(String accNo){
        
        for (Account acc: accounts){
            if (acc.accountNo.trim().equals(accNo)){
                accounts.remove(acc);
                return true;
            }
        }
        
        return false;
    }
    
    
    String showExistingAccounts (){
        
        if (accounts.isEmpty()){
            return "There are no accounts.";
        }
            
        String output = " ";
        
        for (Account acc : accounts){
            output += "Account Number: " + acc.accountNo + "           |      Account Type     : " + acc.accountType +" \n";
            output += "Name                 : " + acc.name +      "        |        Balance           : " + acc.balance + "\n \n";
        }
        
        return output;
    }
    


}
