/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.domain;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Ryan
 */
public class CustomerTest {

    private Customer customer;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.customer = new Customer("Test", "Testington", new Date(16523),
                                new PostalAddress(
                                        "15",
                                        "Test Road",
                                        "Testingville",
                                        "Testers",
                                        "England",
                                        "TE5 T1N"));
    }

    @After
    public void tearDown() throws Exception {
        this.customer = null;
    }

    /**
     * Test of setFirstName method, of class Customer.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "New";
        this.customer.setFirstName(firstName);
        assertEquals(this.customer.getFirstName(), firstName);
    }

    /**
     * Test of setLastName method, of class Customer.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "New";
        this.customer.setLastName(lastName);
        assertEquals(this.customer.getLastName(), lastName);
    }

    /**
     * Test of setDateOfBirth method, of class Customer.
     */
    @Test
    public void testSetDateOfBirth() {
        System.out.println("setDateOfBirth");
        Date now = new Date();
        this.customer.setDateOfBirth(now);
        assertEquals(this.customer.getDateOfBirth(), now);
    }

    /**
     * Test of setAddress method, of class Customer.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setDateOfBirth");
        PostalAddress work = new PostalAddress(
                                        "90",
                                        "Work Road",
                                        "Workland",
                                        "Testers",
                                        "England",
                                        "T44 T1N");
        this.customer.setAddress(work);
        assertEquals(this.customer.getAddress(), work);
    }

    /**
     * Test of getCustomerId method, of class Customer.
     */
    @Ignore
    public void testGetCustomerId() {
        System.out.println("getCustomerId");
        assertNotNull(this.customer.getCustomerId());
    }

    /**
     * Test of getFirstName method, of class Customer.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        assertNotNull(this.customer.getFirstName());
    }

    /**
     * Test of getLastName method, of class Customer.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        assertNotNull(this.customer.getLastName());
    }

    /**
     * Test of getDateOfBirth method, of class Customer.
     */
    @Test
    public void testGetDateOfBirth() {
        System.out.println("getDateOfBirth");
        assertNotNull(this.customer.getDateOfBirth());
    }

    /**
     * Test of getAddress method, of class Customer.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        assertNotNull(this.customer.getAddress());
    }

    /**
     * Test of getAccounts method, of class Customer.
     */
    @Test
    public void testGetAccounts() {
        System.out.println("getAccounts");
        assertNotNull(this.customer.getAccounts());
    }

    /**
     * Test of addAccount method, of class Customer.
     */
    @Test
    public void testAddAccount() {
        System.out.println("addAccount");
        Account a1 = new Account("12345678", "123456", AccountType.CURRENT);
        this.customer.addAccount(a1);
        assert(this.customer.getAccounts().contains(a1));        
    }

    /**
     * Test of removeAccount method, of class Customer.
     */
    @Test
    public void testRemoveAccount() {
        System.out.println("removeAccount");
        Account a1 = new Account("12345678", "123456", AccountType.CURRENT);
        this.customer.addAccount(a1);
        assert(this.customer.getAccounts().contains(a1));
        this.customer.removeAccount(a1);
        assert(!this.customer.getAccounts().contains(a1));
    }
    
}
