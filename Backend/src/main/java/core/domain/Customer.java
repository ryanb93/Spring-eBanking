package core.domain;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.util.HtmlUtils;

/**
 * A class which encapsulates data for a Customer.
 */
@Document(collection = "customers")
public class Customer {

    /** The database ID of the customer */
    @Id
    @JsonIgnore
    private String customerId;

    /** The first name of the customer */
    @NotNull
    private String firstName;

    /** The last name of the customer */
    @NotNull
    private String lastName;

    /** The PostalAddress of the customer */
    @NotNull
    private PostalAddress address;
    
    /** The API User ID linked to the customer */
    @Indexed(unique = true)
    @JsonIgnore
    private String apiUserId;

    /**
     * Empty default constructor needed by Spring to create a Customer
     * object from the JSON request body. 
     * 
     * It creates an empty object and then goes through all the setters and
     * sets the values based on the JSON key/value pairs.
     */
    public Customer() {
    }

    /**
     * Creates a new customer object.
     *
     * @param firstName - The first name of the customer.
     * @param lastName - The last name of the customer.
     * @param address - The address of the customer.
     * @param apiUserId - The ID of the API User object in the database. (Optional)
     */
    public Customer(String firstName, String lastName, PostalAddress address, String apiUserId) {
        super();
        //Set values using setters.
        this.setFirstName(firstName);
        this.setLastName(lastName);
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
        this.firstName = HtmlUtils.htmlEscape(firstName);
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
        this.lastName = HtmlUtils.htmlEscape(lastName);
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

    /**
     * Sets the ID of the API user.
     * 
     * @param apiUserId - The ID of the API user.
     */
    public final void setApiUserId(String apiUserId) {
        this.apiUserId = HtmlUtils.htmlEscape(apiUserId);
    }

    /**
     * Returns the ID of the Customer.
     *
     * @return the customer's ID
     */
    @JsonIgnore
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
     * Returns the address of the Customer.
     *
     * @return the customer's address
     */
    public PostalAddress getAddress() {
        return this.address;
    }

    /**
     * Returns the API User ID of the Customer.
     *
     * @return the customer's API User ID
     */
    @JsonIgnore
    public String getApiUserId() {
        return this.apiUserId;
    }

}
