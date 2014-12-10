package core.domain;


public class AccountDetails {

    private String accountNumber;
    private String sortCode;
    
    public AccountDetails() {
    }
    
    public AccountDetails(String accountNumber, String sortCode) {
        super();
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
    }
    
    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public String getSortCode() {
        return this.sortCode;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }
}
