package banking;

public abstract class Account {

    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public abstract void withdraw(double amount);

    public double getBalance() {
        return balance;
    }
}
