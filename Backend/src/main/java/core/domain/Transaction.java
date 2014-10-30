package core.domain;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A class which encapsulates data for a Transaction.
 */
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String transactionId;   // A unique transaction ID

    @NotNull
    private String accountId;       // The unique ID of either the sender or recipient account

    @NotNull
    private String sender;          // The UUID sender in the transaction

    @NotNull
    private String recipient;       // The recipient of the transaction

    @NotNull
    private Double value;           // The value of the 

    @NotNull
    private Date date;              // The date the transaction was commited
    
    @NotNull
    private TransactionType transactionType;
    
    public Transaction() {
    }
    
    /**
     * Creates a new Transaction object.
     *
     * @param sender - The ID of the sender.
     * @param recipient - The last name of the customer.
     * @param value - The customer's data of birth.
     * @param date - The address of the customer.
     */
    public Transaction(String sender, String recipient, Double value, Date date, TransactionType type) {
        super();
        // Set values using setters.
        this.setSender(sender);
        this.setRecipient(recipient);
        this.setValue(value);
        this.setDate(date);
        this.setTransactionType(type);
    }
    
    /**
     * Sets the account ID of the transaction.
     * 
     * @param accountId - The account ID of the transaction.
     */
    public final void setAccountId(String accountId) {
        if (sender == null || sender.equals("")) {
            throw new IllegalArgumentException("This account ID is either null or invalid.");
        }
        this.accountId = accountId;    
    }

    /**
     * Sets the name of the sender.
     *
     * @param sender - The name of the sender.
     */
    public final void setSender(String sender) {
        if (sender == null || sender.equals("")) {
            throw new IllegalArgumentException("This sender is either null or invalid.");
        }
        
        this.sender = sender;
    }

    /**
     * Sets the first name of the customer.
     *
     * @param recipient - The first name of the recipient.
     */
    public final void setRecipient(String recipient) {
        if (recipient == null || recipient.equals("")) {
            throw new IllegalArgumentException("This recipient is either null or invalid.");
        }
        
        this.recipient = recipient;
    }

    /**
     * Sets the value of the transaction.
     *
     * @param value - The value of the transaction.
     */
    public final void setValue(Double value) {
        if (value == null) {
            throw new IllegalArgumentException("This value is invalid.");
        }
        
        this.value = value;
    }

    /**
     * Sets the date of the transaction.
     *
     * @param date - The date of the transaction.
     */
    public final void setDate(Date date) {
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
    public final void setTransactionType(TransactionType type) {
        if (type == null) {
            throw new IllegalArgumentException("This date is invalid.");
        }
        
        this.transactionType = type;
    }    
    
    /**
     * Gets the transactionId.
     *
     * @return transactionId - The ID of the transaction.
     */
    public String getTransactionId() {
        return this.transactionId;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return accountId - The id of the recipient or sender account.
     */
    public String getAccountId() {
        return this.accountId;
    }

    /**
     * Gets the sender in the transaction.
     *
     * @return sender - The sender in the transaction.
     */
    public String getSender() {
        return this.sender;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return recipient - the recipient in the transaction.
     */
    public String getRecipient() {
        return this.recipient;
    }

    /**
     * Gets the value of the transaction.
     *
     * @return value - The value of the transaction.
     */
    public Double getValue() {
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
     * Sets the date of the transaction.
     *
     * @return date - The date of the transaction.
     */
    public TransactionType getTransactionType() {
        return this.transactionType;
    }
    
}
