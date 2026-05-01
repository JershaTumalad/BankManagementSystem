package project;

import java.util.ArrayList;

public class AccountFiles {

    private ArrayList<Account> accounts = new ArrayList<>();

    public boolean addAccount(String name, String type, String accNo, double bal) {
        if (!Account.isAccountUnique(accNo, accounts)) return false;
        accounts.add(new Account(name, type, accNo, bal));
        return true;
    }

    public Account searchAccount(String accNo) {
        for (Account a : accounts) {
            if (a.getAccountNo().equals(accNo)) return a;
        }
        return null;
    }

    public boolean removeAccount(String accNo) {
        return accounts.removeIf(a -> a.getAccountNo().equals(accNo));
    }

    public Object[][] getAccountsData() {
        Object[][] data = new Object[accounts.size()][4];
        for (int i = 0; i < accounts.size(); i++) {
            Account a = accounts.get(i);
            data[i][0] = a.getAccountNo();
            data[i][1] = a.getName();
            data[i][2] = a.getAccountType();
            data[i][3] = String.format("P %,.2f", a.getBalance());
        }
        return data;
    }

    public double getTotalBalance() {
        double total = 0;
        for (Account a : accounts) total += a.getBalance();
        return total;
    }
}