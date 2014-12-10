package core.events.accounts;

public class RequestAccountDetailsFromNumberEvent {

    private final String accountNumber;

    public RequestAccountDetailsFromNumberEvent(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

}
