/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.domain;

import core.domain.Account;
import core.domain.PostalAddress;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * A class which encapsulates data for a Customer.
 */
public class Customer implements Serializable {
    
    private String customerId;          //A unique customer ID
    private String firstName;               //Customer First name
    private String lastName;                //Customer Last name
    private Date dateOfBirth;               //Customer Date of Birth
    private PostalAddress address;          //Customer Address
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
        //this.customerId = UUID.randomUUID();
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
     */
    public final void setFirstName(String firstName) {
        if(firstName == null || firstName.equals("")) { throw new IllegalArgumentException("First name can not be empty."); }
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the customer.
     * @param lastName - The new last name of the customer.
     */
    public final void setLastName(String lastName) {
        if(lastName == null || lastName.equals("")) { throw new IllegalArgumentException("Last name can not be empty."); }
        this.lastName = lastName;
    }

     /**
     * Sets the last name of the customer.
     * @param dateOfBirth - The new date of birth of the customer.
     */
    public final void setDateOfBirth(Date dateOfBirth) {
        if(dateOfBirth == null) { throw new IllegalArgumentException("Date of birth can not be null."); }
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Sets the address of the customer.
     * @param address - The new address of the customer.
     */
    public final void setAddress(PostalAddress address) {
        if(address == null) { throw new IllegalArgumentException("Address can not be null."); }
        this.address = address;
    }
    
    /**
     * Returns the UUID of the Customer.
     * @return the customer's UUID
     */
    public String getCustomerId() {
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
