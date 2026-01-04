package banking.service;

import banking.model.Account;

public interface Transferable {

    void transfer(Account from, Account to, double amount);

}
