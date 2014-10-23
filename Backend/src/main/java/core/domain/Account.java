package core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class which encapsulates data for an Account.
 */

public class Account {
    
    private String accountNumber;         //A unique account number
    private String sortCode;              // An account sort code
    private AccountType accountType;      // An account type
    private List<Transaction> transactions;     // A list of an account's transactions
    private Double balance;                     // An account balance
    
    /**
     * Creates a new Account object.
     * 
     * @param accountNumber - The first name of the customer.
     * @param sortCode - The last name of the customer.
     * @param accountType - The customer's data of birth.
     */
    public Account(String accountNumber, String sortCode, AccountType accountType){
        super();
        //set values using setters.
        this.setAccountNumber(accountNumber);
        this.setSortCode(sortCode);
        this.setAccountType(accountType);
        //initialise the list of an accounts transactions.
        this.transactions = new ArrayList();
        //initialise the account balance when creating a new account.
        this.balance = 0.00;
    }
    
    /**
     * Returns the account number.
     * @return  the unique account number.
     */
    public String getAccountNumber(){
        return this.accountNumber;
    }
    
    /**
     * Returns the sort code.
     * @return the sort code.
     */
    public String getSortCode(){
        return this.sortCode;
    }
    
    /**
     * Returns the account type.
     * @return the account type.
     */
    public AccountType getAccountType(){
        return this.accountType;
    }
    
    /**
     * Returns all the transactions associated with this account.
     * @return a list of an accounts transactions.
     */
    public List<Transaction> getTransactions(){
        return this.transactions;
    }
    
    /**
     * Adds a single transaction to the list of transactions associated with an account.
     * @param transaction - a single transaction we wish to add.
     */
    public void addSingleTransaction(Transaction transaction){
        if(transaction == null){throw new IllegalArgumentException("This transaction is invalid.");}
        transactions.add(transaction);
    }

    /**
     * Sets the account number of the account.
     * @param accountNumber - The account number.
     */
    public final void setAccountNumber(String accountNumber) {
        String pattern = "^[0-9]{8}$";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(accountNumber);
        if(!matcher.matches()){throw new IllegalArgumentException("This account number does not match the expected input of 8 digits.");}
        this.accountNumber = accountNumber;
    }

    /**
     * Sets the sort code of the account.
     * @param sortCode - The sort code.
     */
    public final void setSortCode(String sortCode) {
        String pattern = "^[0-9]{6}$";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(sortCode);
        if(!matcher.matches()){throw new IllegalArgumentException("This sort code does not match the expected input of 6 digits.");}
        this.sortCode = sortCode;
    }
    
    /**
     * Sets the AccountType of the Account.
     * @param accountType - The Account Type. Accepted types are CURRENT, ISA and SAVINGS.
     */
    public final void setAccountType(AccountType accountType) {
        if(accountType != AccountType.CURRENT || accountType != AccountType.ISA || accountType != AccountType.SAVINGS){throw new IllegalArgumentException("This account is not of the accepted specified account types");}
        this.accountType = accountType;
    }

    /**
     * Sets the balance of the Account.
     * @param balance - The Account Balance.
     */
    public void setBalance(Double balance) {
        if(balance == null){throw new IllegalArgumentException("This balance is invalid as it is null.");}
        this.balance = balance;
    }

    
}
