package core.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit testing for the Account domain.
 */
public class AccountTest {
    
    /** The Account object to test against. */
    private Account account;
    
    /** Constant values to use during testing. */
    private static final String ACCOUNT_NUMBER = "12345678";
    private static final String NEW_ACCOUNT_NUMBER = "87654321";
    private static final String INVALID_ACCOUNT_NUMBER = "KASJHDFKJA";
    private static final String CUSTOMER_ID = "CUSTOMER";
    private static final String NEW_CUSTOMER_ID = "NEWCUSTOMER";
    private static final String SORT_CODE = "112233";
    private static final String NEW_SORT_CODE = "332211";
    private static final String INVALID_SORT_CODE ="FDADFAF";
    private static final AccountType ACCOUNT_TYPE = AccountType.CURRENT;
    private static final AccountType NEW_ACCOUNT_TYPE = AccountType.SAVINGS;
    private static final double TEST_BALANCE = 44.50;
    /**
     * Setup method which is called before every test. Creates a new account object.
     */
    @Before
    public void setUp() {
        this.account = new Account(ACCOUNT_NUMBER, CUSTOMER_ID, SORT_CODE, ACCOUNT_TYPE);
    }
    
    /**
     * Teardown method which makes sure the Account is destroyed after each test.
     */    
    @After
    public void tearDown() {
        this.account = null;
    }

    /**
     * Tests that we can get the customer ID from the Account.
     */
    @Test
    public void testGetCustomerId() {
        assertEquals(account.getCustomerId(), CUSTOMER_ID);
    }
    
    /**
     * Tests that we can get the account number from the Account.
     */
    @Test
    public void testGetAccountNumber() {
        assertEquals(account.getAccountNumber(), ACCOUNT_NUMBER);
    }

    /**
     * Tests that we can get the sort code from the Account.
     */
    @Test
    public void testGetSortCode() {
        assertEquals(account.getSortCode(), SORT_CODE);
    }

    /**
     * Tests that we can get the account type from the Account.
     */
    @Test
    public void testGetAccountType() {
        assertEquals(account.getAccountType(), ACCOUNT_TYPE);
    }
    
    /**
     * Tests that we can get the account balance from the Account.
     */
    @Test
    public void testGetBalance(){
        assertEquals(account.getBalance(), 0, 0);
    }

    /**
     * Tests that we can set the account number.
     */
    @Test
    public void testSetAccountNumber() {
        account.setAccountNumber(NEW_ACCOUNT_NUMBER);
        assertEquals(account.getAccountNumber(), NEW_ACCOUNT_NUMBER);
    }   

    /**
     * Tests the sort code validation.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetAccountNumberValidation() {
        account.setAccountNumber(INVALID_ACCOUNT_NUMBER);
    }       
    
    /**
     * Tests that we can set the account customer ID.
     */
    @Test
    public void testSetCustomerId() {
        account.setCustomerId(NEW_CUSTOMER_ID);
        assertEquals(account.getCustomerId(), NEW_CUSTOMER_ID);
    }       
    
    /**
     * Tests that we can set the account sort code.
     */
    @Test
    public void testSetSortCode() {
        account.setSortCode(NEW_SORT_CODE);
        assertEquals(account.getSortCode(), NEW_SORT_CODE);
    }  

    /**
     * Tests the sort code validation.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetSortCodeValidation() {
        account.setSortCode(INVALID_SORT_CODE);
    }     
    
    /**
     * Tests that we can set the account type.
     */
    @Test
    public void testSetAccountType() {
        account.setAccountType(NEW_ACCOUNT_TYPE);
        assertEquals(account.getAccountType(), NEW_ACCOUNT_TYPE);
    }      
    
    /**
     * Tests that we can set the account balance.
     */
    @Test
    public void testSetBalance() {
        account.setBalance(TEST_BALANCE);
        assertEquals(account.getBalance(), TEST_BALANCE, 0);
    }
    
}
