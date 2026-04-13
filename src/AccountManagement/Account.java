package project;

import java.util.ArrayList;

public class Account {
    private String accountNo;
    private String name;
    private String accountType;
    private double balance;

    public Account(String name, String accountType, String accountNo, double balance) {
        this.accountNo = accountNo.trim();
        this.name = name.trim();
        this.accountType = accountType.trim();
        this.balance = balance;
    }

    public String getAccountNo() { 
        return accountNo; 
    }
    public String getName() { 
        return name; 
    }
    public String getAccountType() { 
        return accountType; 
    }
    public double getBalance() { 
        return balance; 
    }

    public static boolean validateUniqueAccount(String accNo, ArrayList<Account> accounts){
        for(Account acc : accounts) {
            if(acc.getAccountNo().equals(accNo.trim())) 
                return false;
        }
        return true;
    }
}