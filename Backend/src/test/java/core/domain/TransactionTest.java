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
        this.transaction = new Transaction(true, "12345678", "123456", "87654321", 450.50, new Date(16523), TransactionType.OTHER);
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
    public void testSetOtherAccountNumber() {
        String accountNumber = "11223344";
        transaction.setOtherAccountNumber(accountNumber);
        assertEquals(transaction.getOtherAccountNumber(), accountNumber);
    }

    /**
     * Test of setSenderAccountNumber method, of class Transaction.
     */
    @Test
    public void testSetSenderSortCode() {
        String sortCode = "112233";
        transaction.setOtherSortCode(sortCode);
        assertEquals(transaction.getOtherSortCode(), sortCode);
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
