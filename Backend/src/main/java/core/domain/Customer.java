package core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * A class which encapsulates data for a Customer.
 */
public class Customer {
    
    private final UUID customerId;          //A unique customer ID
    private String firstName;               //Customer First name
    private String lastName;                //Customer Last name
    private Date dateOfBirth;               //Customer Date of Birth
    private PostalAddress address;                //Customer Address
    private final List<Account> accounts;   //List of accounts owned by Customer
    
    /**
     * Creates a new customer object.
     * 
     * @param firstName - The first name of the customer.
     * @param lastName - The last name of the customer.
     * @param dateOfBirth - The customer's data of birth.
     * @param address - The address of the customer.
     */
    public Customer(String firstName, String lastName, Date dateOfBirth, PostalAddress address) {
        super();
        //Generate a random UUID for the customer.
        this.customerId = UUID.randomUUID();
        //Initalise the array list.
        this.accounts = new ArrayList();
        //Set values using setters.
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDateOfBirth(dateOfBirth);
        this.setAddress(address);
    }
    
    /**
     * Sets the first name of the customer.
     * @param firstName - The new first name of the customer.
     * @throws IllegalArgumentException - If the first name is null or empty.
     */
    public final void setFirstName(String firstName) throws IllegalArgumentException {
        if(firstName == null || firstName.equals("")) { throw new IllegalArgumentException("First name can not be empty."); }
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the customer.
     * @param lastName - The new last name of the customer.
     * @throws IllegalArgumentException - If the first name is null or empty.
     */
    public final void setLastName(String lastName) throws IllegalArgumentException {
        if(lastName == null || lastName.equals("")) { throw new IllegalArgumentException("Last name can not be empty."); }
        this.lastName = lastName;
    }

     /**
     * Sets the last name of the customer.
     * @param dateOfBirth - The new date of birth of the customer.
     * @throws IllegalArgumentException - If the date of birth is null or in the future.
     */
    public final void setDateOfBirth(Date dateOfBirth) throws IllegalArgumentException {
        if(dateOfBirth == null) { throw new IllegalArgumentException("Date of birth can not be null."); }
        if(dateOfBirth.after(new Date())) { throw new IllegalArgumentException("Date of birth can not be in the future."); }
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Sets the address of the customer.
     * @param address - The new address of the customer.
     * @throws IllegalArgumentException - If the address is null.
     */
    public final void setAddress(PostalAddress address) throws IllegalArgumentException {
        if(address == null) { throw new IllegalArgumentException("Address can not be null."); }
        this.address = address;
    }
    
    /**
     * Returns the UUID of the Customer.
     * @return the customer's UUID
     */
    public UUID getCustomerId() {
        return this.customerId;
    }
        
    /**
     * Returns the first name of the Customer.
     * @return the customer's first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Returns the last name of the Customer.
     * @return the customer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the date of birth of the Customer.
     * @return the customer's date of birth
     */
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * Returns the address of the Customer.
     * @return the customer's address
     */    
    public PostalAddress getAddress() {
        return this.address;
    }

    /**
     * Returns a list of all of the Accounts the Customer owns.
     * @return a list of accounts
     */
    public List<Account> getAccounts() {
        return this.accounts;
    }
    
    /**
     * Adds an Account to the Customer. 
     * @param account - The new account to add to the Customer.
     * @return If account was added successfully.
     */
    public boolean addAccount(Account account) {
        return this.accounts.add(account);
    }
    
    /**
     * Removes an Account from the Customer.
     * @param account - The Account to remove from the Customer.
     * @return If account was removed successfully.
     */
    public boolean removeAccount(Account account) {
        return this.accounts.remove(account);
    }
}
