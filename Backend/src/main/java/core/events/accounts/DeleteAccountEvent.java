package core.events.accounts;

public class DeleteAccountEvent {

    private final String accountId;

    public DeleteAccountEvent(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return this.accountId;
    }

}
