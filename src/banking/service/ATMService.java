package banking.service;
import banking.model.Account;
import banking.model.Transaction;
import java.util.Date;


public class ATMService implements Transferable {
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

    @Override
public void transfer(Account from, Account to, double amount) {

    if (amount <= 0) return;

    if (from.getBalance() >= amount) {

        from.withdraw(amount);
        to.deposit(amount);

        Transaction outTx = new Transaction(
            3,
            "TRANSFER_OUT",
            amount,
            new Date()
        );

        Transaction inTx = new Transaction(
            4,
            "TRANSFER_IN",
            amount,
            new Date()
        );

        from.addTransaction(outTx);
        to.addTransaction(inTx);
    }
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
