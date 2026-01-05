package banking.test;

import banking.model.CheckingAccount;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CheckingAccountTest {

    @Test
    void withdraw_shouldIncludeFee() {
        CheckingAccount acc = new CheckingAccount("CA-1", 10);
        acc.deposit(500);

        acc.withdraw(100);

        assertEquals(390, acc.getBalance());
    }

    @Test
    void withdraw_notEnoughForFee_shouldFail() {
        CheckingAccount acc = new CheckingAccount("CA-2", 20);
        acc.deposit(100);

        acc.withdraw(90);

        assertEquals(100, acc.getBalance());
    }
}


