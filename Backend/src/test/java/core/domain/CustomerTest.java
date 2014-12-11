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

public class CustomerTest {

    private Customer customer;
    
    private static final String FIRST_NAME = "Test";
    private static final String LAST_NAME = "Test";
    private static final String API_USER_ID = "ID001";
    private static final PostalAddress ADDRESS = new PostalAddress("15",
                                                                   "Test Road",
                                                                   "Testingville",
                                                                   "Testers",
                                                                   "England",
                                                                   "TE5 T1N");

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.customer = new Customer(FIRST_NAME, LAST_NAME, ADDRESS,API_USER_ID);
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
        assertEquals(this.customer.getFirstName(), FIRST_NAME);
    }

    /**
     * Test of getLastName method, of class Customer.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        assertEquals(this.customer.getLastName(), LAST_NAME);
    }

    /**
     * Test of getAddress method, of class Customer.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        assertEquals(this.customer.getAddress(), ADDRESS);
    }
    
}
