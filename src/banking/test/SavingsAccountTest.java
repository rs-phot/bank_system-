package banking.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import banking.model.SavingsAccount;

class SavingsAccountTest {

    @Test
    void withdraw_shouldReduceBalance() {
        SavingsAccount acc = new SavingsAccount("SA-1");
        acc.deposit(1000);

        acc.withdraw(200);

        assertEquals(800, acc.getBalance());
    }

    @Test
    void withdraw_moreThanBalance_shouldNotChangeBalance() {
        SavingsAccount acc = new SavingsAccount("SA-2");
        acc.deposit(300);

        acc.withdraw(500);

        assertEquals(300, acc.getBalance());
    }
}
