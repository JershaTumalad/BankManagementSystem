package BAMS;

public class Account {
    String accountNo;
    String name;
    String accountType;
    double balance;

    public Account(String name, String accountType, String accountNo, double balance) {
        this.accountNo = accountNo.trim();
        this.name = name.trim();
        this.accountType = accountType.trim();
        setBalance(balance); 
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

    public void setAccountNo(String accountNo) {
        if (accountNo != null && !accountNo.trim().isEmpty()) {
            this.accountNo = accountNo.trim();
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void setAccountType(String accountType) {
        if (accountType != null && !accountType.trim().isEmpty()) {
            this.accountType = accountType.trim();
        }
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
    }

    public static boolean validateUniqueAccount(String accNo, java.util.ArrayList<Account> accounts) {
        for (Account acc : accounts) {
            if (acc.getAccountNo().equals(accNo.trim())) {
                return false; 
            }
        }
        return true; 
    }

    @Override
    public String toString() {
        return "Account No.: " + accountNo + ", Name: " + name + ", Type: " + accountType + ", Balance: " + balance;
    }
}

