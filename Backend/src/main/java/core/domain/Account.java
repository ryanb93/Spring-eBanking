package core.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A class which encapsulates data for an Account.
 */
@Document(collection = "accounts")
public class Account {

    @Id
    private String accountId;

    @NotNull
    private String customerId;                  //A customer ID

    @Indexed(unique = true)
    private String accountNumber;               //A unique account number

    @NotNull
    private String sortCode;                    // An account sort code

    @NotNull
    private AccountType accountType;            // An account type

    @NotNull
    private Double balance;                     // An account balance

    /**
     * Constructor used to bind a JSON request into an Account object. We still
     * need to call newAccount else variables wont get set.
     */
    public Account() {
        this.newAccount();
    }

    /**
     * Creates a new Account object.
     *
     * @param accountNumber - The first name of the customer.
     * @param sortCode - The last name of the customer.
     * @param accountType - The customer's data of birth.
     */
    public Account(String accountNumber, String sortCode, AccountType accountType) {
        super();
        //set values using setters.
        this.setAccountNumber(accountNumber);
        this.setSortCode(sortCode);
        this.setAccountType(accountType);
        this.newAccount();
    }

    private void newAccount() {
        //initialise the account balance when creating a new account.
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
     * @return the customer who owns the account.
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

    public Double getBalance() {
        return this.balance;
    }

    /**
     * Sets the account number of the account.
     *
     * @param accountNumber - The account number.
     */
    public final void setAccountNumber(String accountNumber) {
        String pattern = "^[0-9]{8}$";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(accountNumber);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("This account number does not match the expected input of 8 digits.");
        }
        this.accountNumber = accountNumber;
    }

    /**
     * Sets the customer who owns the account.
     *
     * @param customerId - The customer who owns the account.
     */
    public final void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Sets the sort code of the account.
     *
     * @param sortCode - The sort code.
     */
    public final void setSortCode(String sortCode) {
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
     * @param accountType - The Account Type. Accepted types are CURRENT, ISA
     * and SAVINGS.
     */
    public final void setAccountType(AccountType accountType) {
        if (accountType != AccountType.CURRENT || accountType != AccountType.ISA
                || accountType != AccountType.SAVINGS) {

        } else {
            throw new IllegalArgumentException("This account is not of the accepted specified account types");
        }
        this.accountType = accountType;
    }

    /**
     * Sets the balance of the Account.
     *
     * @param balance - The Account Balance.
     */
    public void setBalance(Double balance) {
        if (balance == null) {
            throw new IllegalArgumentException("This balance is invalid as it is null.");
        }
        this.balance = balance;
    }

}
