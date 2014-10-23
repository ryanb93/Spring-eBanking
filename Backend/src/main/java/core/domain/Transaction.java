package core.domain;

import java.util.Date;
import java.util.UUID;

/**
 * A class which encapsulates data for a Transaction.
 */

public class Transaction {
    
    private UUID transactionId;   // A unique transaction ID
    private UUID accountId;       // The unique ID of either the sender or recipient account
    private String sender;        // The UUID sender in the transaction
    private String recipient;     // The recipient of the transaction
    private Double value;         // The value of the transaction
    private Date date;            // The date the transaction was commited
    
     /**
     * Creates a new Transaction object.
     * 
     * @param sender - The UUID of the sender.
     * @param recipient - The last name of the customer.
     * @param value - The customer's data of birth.
     * @param date - The address of the customer.
     * @param accountId - The unique ID of the sender or recipient account ID
     */
    public Transaction(String sender, String recipient, Double value, Date date, UUID accountId){
        super();
        // Set values using setters.
        this.setTransactionId();
        this.setAccountId(accountId);
        this.setSender(sender);
        this.setRecipient(recipient);
        this.setValue(value);
        this.setDate(date);
    }

    Transaction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     /**
     * Sets the transactionId.
     */
    public final void setTransactionId() {
        this.transactionId = UUID.randomUUID();
    }
    
    /**
     * Sets the account id of the recipient or sender account id.
     * @param accountId - The accountId of the sender or recipient account.
     */
    public final void setAccountId(UUID accountId) {
        if(accountId == null){throw new IllegalArgumentException("This Unique ID is invalid.");}
        this.accountId = accountId;
    }

    /**
     * Sets the name of the sender.
     * @param sender - The name of the sender.
     */
    public final void setSender(String sender) {
        if(sender == null || sender.equals("")){throw new IllegalArgumentException("This sender is either null or invalid.");}
        this.sender = sender;
    }

    /**
     * Sets the first name of the customer.
     * @param recipient - The first name of the recipient.
     */
    public final void setRecipient(String recipient) {
        if(recipient == null || recipient.equals("")){throw new IllegalArgumentException("This recipient is either null or invalid.");}
        this.recipient = recipient;
    }

     /**
     * Sets the value of the transaction.
     * @param value - The value of the transaction.
     */
    public final void setValue(Double value) {
        if(value == null){throw new IllegalArgumentException("This value is invalid.");}
        this.value = value;
    }

    /**
     * Sets the date of the transaction.
     * @param date - The date of the transaction.
     */
    public final void setDate(Date date) {
        if(date == null){throw new IllegalArgumentException("This date is invalid.");}
        this.date = date;
    }

    /**
     * Gets the transactionId.
     * @return transactionId - The ID of the transaction.
     */
    public UUID getTransactionId() {
        return transactionId;
    }

    /**
     * Gets the date of the transaction.
     * @return accountId - The id of the recipient or sender account.
     */
    public UUID getAccountId() {
        return accountId;
    }

    /**
     * Gets the sender in the transaction.
     * @return sender - The sender in the transaction.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Gets the date of the transaction.
     * @return recipient - the recipient in the transaction.
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Gets the value of the transaction.
     * @return value - The value of the transaction.
     */
    public Double getValue() {
        return value;
    }

    /**
     * Sets the date of the transaction.
     * @return date - The date of the transaction.
     */
    public Date getDate() {
        return date;
    }
    
    
}
