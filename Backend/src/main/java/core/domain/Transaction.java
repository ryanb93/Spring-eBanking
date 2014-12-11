package core.domain;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    /** The sort code of the sender in the transaction */
    @NotNull
    private String senderSortCode;

    /** The account number of the sender in the transaction. */
    @NotNull
    private String senderAccountNumber;

    /** The sort code of the recipient in the transaction. */
    @NotNull
    private String recipientSortCode;

    /** The account number of the recipient in the transaction. */
    @NotNull
    private String recipientAccountNumber;

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
     * @param senderSortCode - The sort code of the sender.
     * @param senderAccountNumber - The account number of the sender.
     * @param recipientSortCode - The sort code of the recipient.
     * @param recipientAccountNumber - The account number of the recipient.
     * @param value - The value of the transaction. 
     * @param date - The date the transaction was created.
     * @param type - The type of the transaction.
     */
    public Transaction(String senderSortCode, String senderAccountNumber, String recipientSortCode, String recipientAccountNumber, double value, Date date, TransactionType type) {
        super();
        // Set values using setters.
        this.setSenderSortCode(senderSortCode);
        this.setSenderAccountNumber(senderAccountNumber);
        this.setRecipientSortCode(recipientSortCode);
        this.setRecipientAccountNumber(recipientAccountNumber);
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
        this.accountNumber = accountNumber;
    }

    /**
     * Sets the value of the transaction.
     *
     * @param value - The value of the transaction.
     */
    public void setValue(double value) {
        this.value = value;
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
     * Sets the sender sort code. 
     * 
     * @param senderSortCode - The sender sort code.
     */
    public void setSenderSortCode(String senderSortCode) {
        this.senderSortCode = senderSortCode;
    }

    /**
     * Sets the sender account number.
     * 
     * @param senderAccountNumber - the sender account number.
     */
    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    /**
     * Sets the recipient sort code.
     * 
     * @param recipientSortCode - the recipient sort code.
     */
    public void setRecipientSortCode(String recipientSortCode) {
        this.recipientSortCode = recipientSortCode;
    }

    /**
     * Sets the recipient account number.
     * 
     * @param recipientAccountNumber - the recipient account number.
     */
    public void setRecipientAccountNumber(String recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
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
     * Gets the Account Number of the account which owns this transaction.
     *
     * @return accountId - The id of the recipient or sender account.
     */
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Gets the sender account number of the transaction.
     *
     * @return sender - The sender account number.
     */
    public String getSenderAccountNumber() {
        return this.senderAccountNumber;
    }

    /**
     * Gets the sender sort code of the transaction.
     *
     * @return sender sort code.
     */
    public String getSenderSortCode() {
        return this.senderSortCode;
    }

    /**
     * Gets the account number of the recipient.
     *
     * @return recipient account number
     */
    public String getRecipientAccountNumber() {
        return this.recipientAccountNumber;
    }

    /**
     * Gets the sort code of the recipient.
     *
     * @return recipient sort code
     */
    public String getRecipientSortCode() {
        return this.recipientSortCode;
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
