package banking.test;

import banking.service.ATMService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import banking.model.*;

class ATMServiceTest {

    @Test
    void deposit_shouldIncreaseBalance() {
        ATMService atm = new ATMService();
        Account acc = new SavingsAccount("SA-1");

        atm.deposit(acc, 500);

        assertEquals(500, acc.getBalance());
    }

    @Test
    void transfer_shouldMoveMoneyBetweenAccounts() {
        ATMService atm = new ATMService();
        Account from = new SavingsAccount("SA-1");
        Account to = new SavingsAccount("SA-2");

        atm.deposit(from, 1000);
        atm.transfer(from, to, 300);

        assertEquals(700, from.getBalance());
        assertEquals(300, to.getBalance());
    }

    @Test
    void transfer_notEnoughBalance_shouldFail() {
        ATMService atm = new ATMService();
        Account from = new SavingsAccount("SA-1");
        Account to = new SavingsAccount("SA-2");

        atm.deposit(from, 100);

        atm.transfer(from, to, 300);

        assertEquals(100, from.getBalance());
        assertEquals(0, to.getBalance());
    }
}
