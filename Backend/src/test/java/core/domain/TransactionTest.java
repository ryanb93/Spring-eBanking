/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.domain;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Ben
 */
public class TransactionTest {

    private Transaction transaction;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.transaction = new Transaction("Tesco", "AccountHolder", 
                                            450.50, new Date(16523));
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of setTransactionId method, of class Transaction.
     */
    /*
    @Test
    public void testSetTransactionId() {
        System.out.println("setTransactionId");
        assertNotNull(transaction.getTransactionId());
    }
*/
    /**
     * Test of setAccountId method, of class Transaction.
     */
    /*
    @Test
    public void testSetAccountId() {
        System.out.println("setAccountId");
        assertNotNull(transaction.getTransactionId());
    }
*/
    /**
     * Test of setSender method, of class Transaction.
     */
    @Test
    public void testSetSender() {
        System.out.println("setSender");
        String sender = "Morrisons";
        transaction.setSender(sender);
        assertEquals(transaction.getSender(),sender);
    }

    /**
     * Test of setRecipient method, of class Transaction.
     */
    @Test
    public void testSetRecipient() {
        System.out.println("setRecipient");
        String recipient = "Customer";
        transaction.setRecipient(recipient);
        assertEquals(transaction.getRecipient(), recipient);
    }

    /**
     * Test of setValue method, of class Transaction.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        Double value = 999.99;
        transaction.setValue(value);
        assertEquals(transaction.getValue(), value);
    }

    /**
     * Test of setDate method, of class Transaction.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = new Date(123456);
        transaction.setDate(date);
        assertEquals(transaction.getDate(), date);
    }
    
}
