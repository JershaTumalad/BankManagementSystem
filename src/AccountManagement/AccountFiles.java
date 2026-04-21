package project;

import java.util.ArrayList;

public class AccountFiles {
    private ArrayList<Account> accounts;

    public AccountFiles() {
        accounts = new ArrayList<>();
    }

    public boolean addAccount(String name, String acctype, String accNo, double balance) {
        if(!Account.validateUniqueAccount(accNo, accounts)) 
            return false;
        accounts.add(new Account(name, acctype, accNo, balance));
        return true;
    }

    public Account searchAccount(String accNo) {
        String target = accNo.trim();
        for(Account acc : accounts){
            if(acc.getAccountNo().equals(target)) 
                return acc;
        }
        return null;
    }

    public boolean removeAccount(String accNo) {
        String target = accNo.trim();
        for(int i = accounts.size() - 1; i >= 0; i--) {
            if(accounts.get(i).getAccountNo().equals(target)) {
                accounts.remove(i);
                return true;
            }
        }
        return false;
    }

    public Object[][] getAccountsData() {
        Object[][] data = new Object[accounts.size()][4];
        for(int i = 0; i < accounts.size(); i++){
            Account acc = accounts.get(i);
            data[i][0] = acc.getAccountNo();
            data[i][1] = acc.getName();
            data[i][2] = acc.getAccountType();
            data[i][3] = String.format("P %,.2f", acc.getBalance());
        }
        return data;
    }
}