package core.domain;

import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Unit testing for the transaction domain.
 */
public class TransactionTest {

    /** Transaction object */
    private Transaction transaction;
    
    /**
     * Setup method which is called before every test. Creates a new transaction object.
     */
    @Before
    public void setUp() {
        this.transaction = new Transaction("123456", "12345678", "123456", "87654321", 450.50, new Date(16523), TransactionType.OTHER);
    }

    /**
     * Teardown method which makes sure the transaction is destroyed after each test.
     */
    @After
    public void tearDown() {
        this.transaction = null;
    }

    /**
     * Test of setAccountNumber method, of class Transaction.
     */
    @Test
    public void testSetAccountNumber() {
        String accountNumber = "12345678";
        this.transaction.setAccountNumber(accountNumber);
        assertEquals(transaction.getAccountNumber(), accountNumber);
    }
    
    /**
     * Test of setSenderAccountNumber method, of class Transaction.
     */
    @Test
    public void testSetSenderAccountNumber() {
        String accountNumber = "12345678";
        transaction.setSenderAccountNumber(accountNumber);
        assertEquals(transaction.getSenderAccountNumber(), accountNumber);
    }

    /**
     * Test of setSenderAccountNumber method, of class Transaction.
     */
    @Test
    public void testSetSenderSortCode() {
        String sortCode = "123456";
        transaction.setSenderSortCode(sortCode);
        assertEquals(transaction.getSenderSortCode(), sortCode);
    }

    /**
     * Test of setRecipientAccountNumber method, of class Transaction.
     */
    @Test
    public void testSetRecipientAccountNumber() {
        String accountNumber = "87654321";
        transaction.setRecipientAccountNumber(accountNumber);
        assertEquals(transaction.getRecipientAccountNumber(), accountNumber);
    }

    /**
     * Test of setRecipientAccountNumber method, of class Transaction.
     */
    @Test
    public void testSetRecipientSortCode() {
        String sortCode = "654321";
        transaction.setRecipientSortCode(sortCode);
        assertEquals(transaction.getRecipientSortCode(), sortCode);
    }
    
    /**
     * Test of setValue method, of class Transaction.
     */
    @Test
    public void testSetValue() {
        double value = 999.99;
        transaction.setValue(value);
        assertEquals(transaction.getValue(), value, 0);
    }

    /**
     * Test of setDate method, of class Transaction.
     */
    @Test
    public void testSetDate() {
        Date date = new Date(123456);
        transaction.setDate(date);
        assertEquals(transaction.getDate(), date);
    }
    
}
