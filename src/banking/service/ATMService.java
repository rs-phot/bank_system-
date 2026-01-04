package banking.service;

import banking.model.Account;

public class ATMService {

    public void deposit(Account account, double amount) {
        if (amount > 0) {
            account.deposit(amount);
        }
    }

    public void withdraw(Account account, double amount) {
        if (amount > 0) {
            account.withdraw(amount);
        }
    }

    public double checkBalance(Account account) {
        return account.getBalance();
    }
}
