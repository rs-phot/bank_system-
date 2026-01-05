package banking.test;

import banking.model.Transaction;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void transaction_shouldStoreCorrectValues() {
        Date date = new Date();

        Transaction transaction =
                new Transaction(1, "DEPOSIT", 500, date);

        assertEquals(1, transaction.getId());
        assertEquals("DEPOSIT", transaction.getType());
        assertEquals(500, transaction.getAmount());
        assertEquals(date, transaction.getDate());
    }
}
