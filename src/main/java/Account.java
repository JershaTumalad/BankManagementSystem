package AccountManagement;

import java.util.ArrayList;

// This file Represents a single bank account with its core data fields.
public class Account {

    // Core account fields
    private String accountNo;
    private String name;
    private String accountType;
    private double balance;

    // Constructor: stores trimmed values to avoid whitespace issues
    public Account(String name, String accountType, String accountNo, double balance) {
        this.accountNo = accountNo.trim();
        this.name = name.trim();
        this.accountType = accountType.trim();
        this.balance = balance;
    }

    // Getters for account data
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

    // Checks whether an account number is not yet taken in the given list
    public static boolean isAccountUnique(String accNo, ArrayList<Account> accounts) {
        String target = accNo.trim();
        for (Account acc : accounts) {
            if (acc.getAccountNo().equals(target)) {
                return false;
            }
        }
        return true; 
    }
}