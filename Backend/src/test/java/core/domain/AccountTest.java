package core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
    
    private Account account;
    
    public AccountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.account = new Account("12345678","223344",AccountType.CURRENT);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAccountNumber method, of class Account.
     */
    @Test
    public void testGetAccountNumber() {
        System.out.println("getAccountNumber");
        String result = account.getAccountNumber();
        assertEquals(result, "12345678");
    }

    /**
     * Test of getSortCode method, of class Account.
     */
    @Test
    public void testGetSortCode() {
        System.out.println("getSortCode");
        String result = account.getSortCode();
        assertEquals(result, "223344");
    }

    /**
     * Test of getAccountType method, of class Account.
     */
    @Test
    public void testGetAccountType() {
        System.out.println("getAccountType");
        AccountType result = account.getAccountType();
        assertEquals(result, AccountType.CURRENT);
    }

    /**
     * Test of getTransactions method, of class Account.
     */
    @Test
    public void testGetTransactions() {
        System.out.println("getTransactions");
        List<Transaction> result = account.getTransactions();
        List<Transaction> expected = new ArrayList<>();
        assertEquals(result, expected);
    }
    
    /**
     * Test of getBalance method, of class Account.
     */
    @Test
    public void testGetBalance(){
        System.out.println("getBalance");
        Double balance = 0.00;
        assertEquals(account.getBalance(), balance);
    }

    /**
     * Test of addSingleTransaction method, of class Account.
     */
    @Test
    public void testAddSingleTransaction() {
        System.out.println("addSingleTransaction");
        Transaction transaction = new Transaction("Tesco","Customer",450.00,new Date());
        account.addSingleTransaction(transaction);
        //NEED TO FIGURE OUT HOW TO CHECK FOR RESULTS OR THAT EXCEPTION IS NOT THROWN
    }

    /**
     * Test of setBalance method, of class Account.
     */
    @Test
    public void testSetBalance() {
        System.out.println("setBalance");
        Double balance = 44.50;
        account.setBalance(balance);
        assertEquals(account.getBalance(), balance);
    }
    
}
