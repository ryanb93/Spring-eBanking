package core.domain;

import java.text.DecimalFormat;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.util.HtmlUtils;

/**
 * A class which encapsulates data for a Transaction.
 */
@Document(collection = "transactions")
public final class Transaction {

    /** The transaction database ID */
    @Id
    private String transactionId;

    /** The account number linked to the transaction. */
    @NotNull
    private String accountNumber;
    
    /** If the transaction is sending out of the account */
    @NotNull
    private boolean sending;

    /** If the transaction is receiving into the account */
    @NotNull
    private boolean receiving = !sending;

    /** The sort code of the other account in the transaction. */
    @NotNull
    private String otherSortCode;

    /** The account number of the other account in the transaction. */
    @NotNull
    private String otherAccountNumber;

    /** The value of the transaction. */
    @NotNull
    private double value;

    /** The date the transaction was created. */
    @NotNull
    private Date date;

    /** The type of the transactions. */
    @NotNull
    private TransactionType transactionType;

    /**
     * Empty default constructor needed by Spring to create a Transaction
     * object from the JSON request body. 
     * 
     * It creates an empty object and then goes through all the setters and
     * sets the values based on the JSON key/value pairs.
     */
    public Transaction() {
    }

    /**
     * Creates a new Transaction object.
     *
     * @param sending - If the transaction is sending out of the account.
     * @param otherAccountNumber - The account number of the other account.
     * @param otherSortCode - The sort code of the other account.
     * @param value - The value of the transaction. 
     * @param date - The date the transaction was created.
     * @param type - The type of the transaction.
     */
    public Transaction(boolean sending, String otherAccountNumber, String otherSortCode, double value, Date date, TransactionType type) {
        super();
        // Set values using setters.
        this.setSending(sending);
        this.setOtherAccountNumber(otherAccountNumber);
        this.setOtherSortCode(otherSortCode);
        this.setValue(value);
        this.setDate(date);
        this.setTransactionType(type);
    }

    /**
     * Sets the account number of the transaction.
     *
     * @param accountNumber - The account number of the transaction.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = HtmlUtils.htmlEscape(accountNumber);
    }
    
    /**
     * Sets the value if the transaction is sending or not.
     * 
     * @param sending - If the transaction is going in or out the account.
     */
    public void setSending(boolean sending) {
        this.sending = sending;
    }

    /**
     * Sets the value of the transaction.
     *
     * @param value - The value of the transaction.
     */
    public void setValue(double value) {
        //Limit the value to two decimal places.
        DecimalFormat df = new DecimalFormat("#.00");
        String dfValue = df.format(value);
        double dfDouble = Double.parseDouble(dfValue);
        this.value = dfDouble;
    }

    /**
     * Sets the date of the transaction.
     *
     * @param date - The date of the transaction.
     */
    public void setDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("This date is invalid.");
        }
        this.date = date;
    }
    
    /**
     * Sets the type of the transaction.
     *
     * @param type - The type of the transaction.
     */
    public void setTransactionType(TransactionType type) {
        this.transactionType = type;
    }
    
    /**
     * Sets the other sort code.
     * 
     * @param otherSortCode - the other sort code.
     */
    public void setOtherSortCode(String otherSortCode) {
        this.otherSortCode = HtmlUtils.htmlEscape(otherSortCode);
    }

    /**
     * Sets the other account number.
     * 
     * @param otherAccountNumber - the other account number.
     */
    public void setOtherAccountNumber(String otherAccountNumber) {
        this.otherAccountNumber = HtmlUtils.htmlEscape(otherAccountNumber);
    }

    /**
     * Gets the transaction database Id.
     *
     * @return transactionId - The ID of the transaction.
     */
    public String getTransactionId() {
        return this.transactionId;
    }
    
    /**
     * Gets if the transaction is sending.
     * 
     * @return sending - If the transaction is sending.
     */
    public boolean getSending() {
        return this.sending;
    }
    
    /**
     * Gets if the transaction is receiving.
     * 
     * @return receiving - If the transaction is receiving.
     */
    public boolean getReceiving() {
        return this.receiving;
    }

    /**
     * Gets the Account Number of the account which owns this transaction.
     *
     * @return accountId - The id of the recipient or sender account.
     */
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Gets the account number of the other account.
     *
     * @return other account number
     */
    public String getOtherAccountNumber() {
        return this.otherAccountNumber;
    }

    /**
     * Gets the sort code of the other account.
     *
     * @return other sort code
     */
    public String getOtherSortCode() {
        return this.otherSortCode;
    }

    /**
     * Gets the value of the transaction.
     *
     * @return value - The value of the transaction.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Sets the date of the transaction.
     *
     * @return date - The date of the transaction.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets the type of the transaction.
     *
     * @return date - The type of the transaction.
     */
    public TransactionType getTransactionType() {
        return this.transactionType;
    }
    
    /**
     * Clears the Transaction ID so a new one can be created..
     *
     */
    public void clearTransactionId() {
        this.transactionId = null;
    }

}
