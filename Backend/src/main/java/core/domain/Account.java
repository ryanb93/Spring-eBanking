package core.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import static org.springframework.util.Assert.notNull;

/**
 * A class which encapsulates data for a bank account.
 * 
 * The collection annotation saves these classes in an 'accounts'
 * table on the mongo database.
 */
@Document(collection = "accounts")
public class Account {

    /** The database ID of the account. */
    @Id
    private String accountId;                

    /** The database ID of the customer who owns the account */
    @NotNull
    private String customerId;
    
    /** The unique bank account number. */
    @Indexed(unique = true)
    private String accountNumber;

    /** The sort code of the account. */ 
    @NotNull
    private String sortCode;

    /** The type of the account. */
    @NotNull
    private AccountType accountType;

    /** The balance of the account. */
    @NotNull
    private double balance;

    /**
     * Empty default constructor needed by Spring to create an Account
     * object from the JSON request body. 
     * 
     * It creates an empty object and then goes through all the setters and
     * sets the values based on the JSON key/value pairs.
     */
    public Account() {
        super();
        this.balance = 0.00;
    }

    /**
     * Returns the account Id.
     *
     * @return the unique account Id.
     */
    public String getAccountId() {
        return this.accountId;
    }

    /**
     * Returns the customer Id.
     *
     * @return the customer id who owns the account.
     */
    public String getCustomerId() {
        return this.customerId;
    }

    /**
     * Returns the account number.
     *
     * @return the unique account number.
     */
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Returns the sort code.
     *
     * @return the sort code.
     */
    public String getSortCode() {
        return this.sortCode;
    }

    /**
     * Returns the account type.
     *
     * @return the account type.
     */
    public AccountType getAccountType() {
        return this.accountType;
    }

    /**
     * Returns the balance of the account.
     * 
     * @return the account balance.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Sets the account number of the account.
     *
     * @param accountNumber - The account number.
     */
    public final void setAccountNumber(String accountNumber) {
        notNull(accountNumber, "Mandatory argument 'accountNumber missing.'");
        String pattern = "^[0-9]{8}$";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(accountNumber);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("This account number does not match the expected input of 8 digits.");
        }
        this.accountNumber = accountNumber;
    }

    /**
     * Sets the id of the customer who owns the account.
     *
     * @param customerId - The customer who owns the account.
     */
    public final void setCustomerId(String customerId) {
        notNull(customerId, "Mandatory argument 'customerId missing.'");
        this.customerId = customerId;
    }

    /**
     * Sets the sort code of the account.
     *
     * @param sortCode - The sort code.
     */
    public final void setSortCode(String sortCode) {
        notNull(sortCode, "Mandatory argument 'sortCode missing.'");
        String pattern = "^[0-9]{6}$";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(sortCode);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("This sort code does not match the expected input of 6 digits.");
        }
        this.sortCode = sortCode;
    }

    /**
     * Sets the AccountType of the Account.
     *
     * @param accountType - The Account Type.
     */
    public final void setAccountType(AccountType accountType) {
        notNull(accountType, "Mandatory argument 'accountType missing.'");
        this.accountType = accountType;
    }

    /**
     * Sets the balance of the Account.
     *
     * @param balance - The Account Balance.
     */
    public void setBalance(double balance) {
        notNull(balance, "Mandatory argument 'balance missing.'");
        this.balance = balance;
    }

}
