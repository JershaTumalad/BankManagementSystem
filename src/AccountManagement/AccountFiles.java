package project;

import java.util.ArrayList;

public class AccountFiles {
    private ArrayList<Account> accounts;

    public AccountFiles() {
        accounts = new ArrayList<>();
    }

    public boolean addAccount(String name, String acctype, String accNo, double balance) {
        if(!Account.validateUniqueAccount(accNo, accounts)) return false;
        Account newAccount = new Account(name, acctype, accNo, balance);
        accounts.add(newAccount);
        return true;
    }

    public Account searchAccount(String accNo) {
        for(Account acc: accounts){
            if(acc.getAccountNo().trim().equals(accNo)) return acc;
        }
        return null;
    }

    public boolean removeAccount(String accNo){
        for(Account acc: accounts){
            if(acc.getAccountNo().trim().equals(accNo)){
                accounts.remove(acc);
                return true;
            }
        }
        return false;
    }

    public Object[][] getAccountsData() {
        Object[][] data = new Object[accounts.size()][4];
        for(int i=0; i<accounts.size(); i++){
            Account acc = accounts.get(i);
            data[i][0] = acc.getAccountNo();
            data[i][1] = acc.getName();
            data[i][2] = acc.getAccountType();
            data[i][3] = acc.getBalance();
        }
        return data;
    }
}