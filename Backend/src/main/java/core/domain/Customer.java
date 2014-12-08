package core.domain;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A class which encapsulates data for a Customer.
 */
@Document(collection = "customers")
public class Customer {

    @Id
    private String customerId;              //A unique customer ID

    @NotNull
    private String firstName;               //Customer First name

    @NotNull
    private String lastName;                //Customer Last name

    @NotNull
    private Date dateOfBirth;               //Customer Date of Birth

    @NotNull
    private PostalAddress address;          //Customer Address
    
    @NotNull
    private String apiUserId;                   //API User ID

    public Customer() {
    }

    /**
     * Creates a new customer object.
     *
     * @param firstName - The first name of the customer.
     * @param lastName - The last name of the customer.
     * @param dateOfBirth - The customer's data of birth.
     * @param address - The address of the customer.
     */
    public Customer(String firstName, String lastName, Date dateOfBirth, PostalAddress address, String apiUserId) {
        super();
        //Set values using setters.
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDateOfBirth(dateOfBirth);
        this.setAddress(address);
        this.setApiUserId(apiUserId);
    }

    /**
     * Sets the first name of the customer.
     *
     * @param firstName - The new first name of the customer.
     */
    public final void setFirstName(String firstName) {
        if (firstName == null || firstName.equals("")) {
            throw new IllegalArgumentException("First name can not be empty.");
        }
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the customer.
     *
     * @param lastName - The new last name of the customer.
     */
    public final void setLastName(String lastName) {
        if (lastName == null || lastName.equals("")) {
            throw new IllegalArgumentException("Last name can not be empty.");
        }
        this.lastName = lastName;
    }

    /**
     * Sets the last name of the customer.
     *
     * @param dateOfBirth - The new date of birth of the customer.
     */
    public final void setDateOfBirth(Date dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth can not be null.");
        }
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address - The new address of the customer.
     */
    public final void setAddress(PostalAddress address) {
        if (address == null) {
            throw new IllegalArgumentException("Address can not be null.");
        }
        this.address = address;
    }
    
     public void setApiUserId(String apiUserId) {
        if (apiUserId == null || apiUserId.equals("")) {
            throw new IllegalArgumentException("API User ID can not be empty.");
        }
        this.apiUserId = apiUserId;
    }

    /**
     * Returns the UUID of the Customer.
     *
     * @return the customer's UUID
     */
    public String getCustomerId() {
        return this.customerId;
    }

    /**
     * Returns the first name of the Customer.
     *
     * @return the customer's first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Returns the last name of the Customer.
     *
     * @return the customer's last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Returns the date of birth of the Customer.
     *
     * @return the customer's date of birth
     */
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * Returns the address of the Customer.
     *
     * @return the customer's address
     */
    public PostalAddress getAddress() {
        return this.address;
    }
    
    /**
     * Returns the address of the Customer as a String (For the Admin Panel).
     *
     * @return the customer's Address.
     */
        public String getPostalAddress() {
        return address.getPostalAddress();
    }
        
    /**
     * Returns the API User ID of the Customer.
     *
     * @return the customer's API User ID
     */
        public String getApiUserId() {
        return this.apiUserId;
    }



}
