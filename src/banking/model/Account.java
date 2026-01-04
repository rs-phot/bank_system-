package banking.model;
import java.util.ArrayList;
import java.util.List;


public abstract class Account {

    protected String accountNumber;
    protected double balance;
    protected List<Transaction> transactions;


    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();

    }

    public void addTransaction(Transaction transaction) {
    transactions.add(transaction);
}

public List<Transaction> getTransactions() {
    return transactions;
}


    public String getAccountNumber() {
    return accountNumber;
}

    public void deposit(double amount) {
        balance += amount;
    }

    public abstract void withdraw(double amount);

    public double getBalance() {
        return balance;
    }
}
