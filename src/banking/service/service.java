package banking.service;

import banking.model.Account;
import java.util.ArrayList;
import java.util.List;

public class service {

    private List<Account> accounts;

    public service() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
