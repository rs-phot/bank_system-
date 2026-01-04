package banking.service;
import banking.model.Account;
import banking.model.Transaction;
import java.util.Date;

public class ATMService {

    public void deposit(Account account, double amount) {
        if (amount <= 0) return;

        account.deposit(amount);

        Transaction transaction = new Transaction(
                1,
                "DEPOSIT",
                amount,
                new Date()
        );

        account.addTransaction(transaction);
    }

    public void withdraw(Account account, double amount) {
        if (amount <= 0) return;

        account.withdraw(amount);

        Transaction transaction = new Transaction(
                2,
                "WITHDRAW",
                amount,
                new Date()
        );

        account.addTransaction(transaction);
    }

    public double checkBalance(Account account) {
        return account.getBalance();
    }

    public void viewTransactions(Account account) {
        for (Transaction t : account.getTransactions()) {
            System.out.println(
                    t.getType() + " | " +
                    t.getAmount() + " | " +
                    t.getDate()
            );
        }
    }
}
