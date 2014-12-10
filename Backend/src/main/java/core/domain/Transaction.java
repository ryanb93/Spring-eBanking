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

    @Id
    private String transactionId;   // A unique transaction ID

    @NotNull
    private String accountNumber;       // The account number linked to the transaction.

    @NotNull
    private String senderSortCode;          // The sort code of the sender in the transaction

    @NotNull
    private String senderAccountNumber;          // The account number of the sender in the transaction

    @NotNull
    private String recipientSortCode;       // The recipient sort code of the transaction

    @NotNull
    private String recipientAccountNumber;       // The recipient account number of the transaction

    @NotNull
    private Double value;           // The value of the transaction.

    @NotNull
    private Date date;              // The date the transaction was commited

    @NotNull
    private TransactionType transactionType;

    public Transaction() {
    }

    /**
     * Creates a new Transaction object.
     *
     * @param senderSortCode
     * @param senderAccountNumber
     * @param recipientSortCode
     * @param recipientAccountNumber
     * @param value - The customer's data of birth.
     * @param date - The address of the customer.
     * @param type - The type of the transaction.
     */
    public Transaction(String senderSortCode, String senderAccountNumber, String recipientSortCode, String recipientAccountNumber, Double value, Date date, TransactionType type) {
        super();
        // Set values using setters.
        this.setSenderSortCode(senderSortCode);
        this.setSenderAccountNumber(senderAccountNumber);
        this.setRecipientSortCode(recipientSortCode);
        this.setRecipientAccountNumber(recipientAccountNumber);
        this.setValue(value);
        this.setDate(date);
        this.setTransactionType(type);
        this.senderSortCode = senderSortCode;
    }

    /**
     * Sets the account ID of the transaction.
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
    public void setValue(Double value) {
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
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Gets the sender in the transaction.
     *
     * @return sender - The sender in the transaction.
     */
    public String getSenderAccountNumber() {
        return this.senderAccountNumber;
    }

    /**
     * Gets the sender in the transaction.
     *
     * @return sender - The sender in the transaction.
     */
    public String getSenderSortCode() {
        return this.senderSortCode;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return recipient - the recipient in the transaction.
     */
    public String getRecipientAccountNumber() {
        return this.recipientAccountNumber;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return recipient - the recipient in the transaction.
     */
    public String getRecipientSortCode() {
        return this.recipientSortCode;
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

    public void setSenderSortCode(String senderSortCode) {
        this.senderSortCode = senderSortCode;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public void setRecipientSortCode(String recipientSortCode) {
        this.recipientSortCode = recipientSortCode;
    }

    public void setRecipientAccountNumber(String recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
    }

}
